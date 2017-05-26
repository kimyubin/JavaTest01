import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Vector;

public class RectangleMove03 extends JFrame 
{
	//마우스 오프셋좌표
	int offX, offY;
	boolean isDragged = false;
	public RectangleMove03()
	{
		setContentPane(new DrawSq());
		setSize(300,300);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
	}

	public static Rectangle pointToRec(int sx, int sy, int ex, int ey){ //좌표 입력하면 사각형으로 만들어줌
		return new Rectangle(sx,sy,ex-sx,ey-sy);
	} 
	public static Rectangle pointToRec(Point start, Point end){ //좌표 입력하면 사각형으로 만들어줌
		return new Rectangle(start.x,start.y,end.x-start.x,end.y-start.y);
	} 

	public Point RecToEndPoint(Rectangle a){ //사각형 입력하면 끝 좌표 만들어줌
		return new Point(a.x+a.width,a.y+a.height);
	} 

	
	class DrawSq extends JPanel
	{
		
		Point startP= null;
		Point endP=null;
		Point tempP=null;
		
		Vector<Point> startV = new Vector<Point>(); // 시작점
		Vector<Point> endV = new Vector<Point>(); // 끝점

	


		public DrawSq()
		{
			MyMouseListener ml = new MyMouseListener();
			
			this.addMouseListener(ml); 
			this.addMouseMotionListener(ml);
		}
		


		public void paintComponent(Graphics g)
		{
			super.paintComponent(g); 	

			//박스 0
			startP.move(0,0);
			endP.move(100,100);
			startV.add(startP);
			endV.add(endP);

			//박스 1
			startP.move(101,101);
			endP.move(201,201);
			startV.add(startP);
			endV.add(endP);

			//박스 2
			startP.move(150,0);
			endP.move(250,100);
			startV.add(startP);
			endV.add(endP);

		



			if(startV.size() != 0)
			{
				for(int i=0;i<endV.size();i++)  // 벡터에 저장된 각 사각형을 매번 그림
				{
					Point sp = startV.get(i);
					Point ep = endV.get(i);	
					g.drawRect(sp.x, sp.y, ep.x-sp.x, ep.y-sp.y);
				}
			}
							
		}
		
		class MyMouseListener extends MouseAdapter implements MouseMotionListener
		{
			Rectangle rec;
			public void mousePressed(MouseEvent e)
			{
				rec = RectangleMove03.pointToRec(startV.get(1),endV.get(1));
				if(rec.contains(new Point(e.getX(),e.getY())))
				{
					//사각형내 마우스 클릭 상대 좌표를 구함
					//현재 마우스 스크린 좌표에서 사각형 위치 좌표의 차이를 구함
					offX = e.getX() - rec.x;
					offY = e.getY() - rec.y;
					
					//드래그 시작을 표시
					isDragged = true;
				}
			}


			}
			public void mouseReleased(MouseEvent e)
			{
				//마우스 버튼이 릴리즈되면 드래그 모드 종료
				isDragged = false;		
			}
			
			public void mouseDragged(MouseEvent e)
			{
				//드래그 모드인 경우에만 사각형 이동시킴
				if(isDragged)
				{
					RectangleMove03.pointToRec(startV.get(1),endV.get(1)).insertElementAt

					startV.add(e.getX() - offX);
					rec.x = e.getX() - offX;
					rec.y = e.getY() - offY;
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