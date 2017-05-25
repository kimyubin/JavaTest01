import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Vector;

public class SqDrawEx extends JFrame 
{
	public boolean moveSq = false;
	public int v=0;//벡터 값 저장

	public SqDrawEx()
	{
		setContentPane(new MyPanel());
		setSize(300,300);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
	}
	
	class MyPanel extends JPanel
	{
		
		Point startP=null;
		Point endP=null;
		Point tempP=null;
		
		Vector<Point> starV = new Vector<Point>(); // 시작점
		Vector<Point> endV = new Vector<Point>(); // 끝점

		public MyPanel()
		{
			//쨍짰쩍쨘쨀횎쨍짝 째첩횇챘�쨍쨌횓횉횠쩐횩  쨘짱쩌철쨉챕�횑 째첩�짱쨉횊쨈횢.
			MyMouseListener ml = new MyMouseListener();
			
			this.addMouseListener(ml); // 쨍짰쩍쨘쨀횎
			this.addMouseMotionListener(ml);
		}
		


		public void paintComponent(Graphics g){
			super.paintComponent(g); 			


			if(starV.size() != 0)
			{
				for(int i=0;i<endV.size();i++)  // 벡터에 저장된 각 사각형을 매번 그림
				{
					Point sp = starV.get(i);
					Point ep = endV.get(i);	
					g.drawRect(sp.x, sp.y, ep.x-sp.x, ep.y-sp.y);
				}
			}
			if(startP != null)
				g.drawRect(startP.x, startP.y, endP.x-startP.x, endP.y-startP.y);	
							
		}
		
		class MyMouseListener extends MouseAdapter implements MouseMotionListener{
			public void mousePressed(MouseEvent e)
			{
				startP = e.getPoint();
				

				for(int i=0;i<endV.size();i++)  // 벡터에 저장된 각 사각형을 비교
				{
					 // 사각형 안쪽에 클릭하면 
					if(startP.x>=starV.get(i).x && endP.x<=endV.get(i).x) // x값 비교
					{
						if(startP.y>=starV.get(i).y && endP.x<=endV.get(i).y) // y값 비교
						{
							moveSq = true;
							v=i;
							break;
						}
						
					}
					else
					{
						starV.add(e.getPoint()); // 쿨릭한 부분을 시작점으로 추가
						break;
					}						
				}
				
				
			}
			public void mouseReleased(MouseEvent e)
			{
				moveSq = false;
				endV.add(e.getPoint()); // 쨉책쨌징짹횞 횉횗쨘횓쨘횖�쨩 횁쩐쨌찼횁징�쨍쨌횓
				endP = e.getPoint();
					 // 쨈횢쩍횄짹횞쨌횁쨋처
				
				repaint();

			}
			
			public void mouseDragged(MouseEvent e)
			{
				if(moveSq)
				{
					tempP = e.getPoint();
					tempP.x = starV.get(v).x + (tempP.x-startP.x); // 현재 백터 x축 + (x축 이동한 거리)
					tempP.y = starV.get(v).y + (tempP.y-startP.y); // y축 이동한 거리
					

					starV.add(v,tempP);
				}
				else
				{
					endP = e.getPoint();
				}
				repaint();

			}
			
			public void mouseMoved(MouseEvent e)
			{
				
			}
		}
	}
	
	public static void main(String[] args) 
	{
		new SqDrawEx();
	}
}