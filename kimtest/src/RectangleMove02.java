import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Vector;

public class RectangleMove02 extends JFrame
{
	Vector<Rectangle> box = new Vector<Rectangle>();

	//마우스 드래그 체크
	boolean isDragged;

	//마우스 오프셋좌표
	int offX, offY;

	Point startP=null;
	Point endP=null;
	Point tempP=null;

	public RectangleMove02()
	{
		setContentPane(new MoveSq());
		setSize(300,300);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
	}

	
	public Rectangle pointToRec(int sx, int sy, int ex, int ey)
	//좌표 입력하면 사각형으로 만들어줌
	{
		return new Rectangle(sx,sy,ex-sx,ey-sy)
	} 
	public Point RecToEndPoint(Rectangle a) //사각형 입력하면 끝 좌표 만들어줌
	{
		return new point(a.x+a.width,a.y+a.height)
	} 


	class MoveSq extends JPanel
	{

		
		
		Vector<Point> starV = new Vector<Point>(); // 시작점
		Vector<Point> endV = new Vector<Point>(); // 끝점

		public MoveSq()
		{
			//쨍짰쩍쨘쨀횎쨍짝 째첩횇챘�쨍쨌횓횉횠쩐횩  쨘짱쩌철쨉챕�횑 째첩�짱쨉횊쨈횢.
			MyMouseListener ml = new MyMouseListener();


			box = new Rectangle(0,0,100,80);
			startP.x=0;
			startP.y=0;
			endP.x=100;
			endP.y=80;
			starV.add(startP);
			endV.add(endP);
			box.add(pointToRec(startP.x,startP.y,endP.x,endP.y))


			startP.x=120;
			startP.y=120;
			endP.x=300;
			endP.y=300;
			starV.add(startP);
			endV.add(endP);
			box.add(pointToRec(startP.x,startP.y,endP.x,endP.y))



			//현재 드래그 상태 저장
			isDragged = false;

			
			this.addMouseListener(ml); // 쨍짰쩍쨘쨀횎
			this.addMouseMotionListener(ml);

			
		}
		


		public void paintComponent(Graphics g)
		{
			//사각형 그릴 색상 설정
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

			//사각형 그림

			//사각형을 이동하기 위하여 사각형의 x,y 좌표와 사각형 내 클릭한 마우스의 좌표가 필요하다

		}
		public void mousePressed(MouseEvent e){
		//사각형 안에서만 이벤트 작업 설정
		if(box.contains(new Point(e.getX(),e.getY()))){
			//#1 마우스 버튼 누름
			//사각형내 마우스 클릭 상대 좌표를 구함
			//현재 마우스 스크린 좌표에서 사각형 위치 좌표의 차이를 구함
			
			offX = e.getX() - box.out(0).x;
			offY = e.getY() - box.out(0).y;
			
			//드래그 시작을 표시
			isDragged = true;

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
				
				box.x = e.getX() - offX;
				box.y = e.getY() - offY;
			}
			repaint();
	
		}	
		public void mouseMoved(MouseEvent e){}
		public void mouseClicked(MouseEvent e){}
		public void mouseEntered(MouseEvent e){}
		public void mouseExited(MouseEvent e){}

		//프로그램 실행		
	}
	public static void main(String[] args) 
	{
		new SqDrawEx();
	}
}
