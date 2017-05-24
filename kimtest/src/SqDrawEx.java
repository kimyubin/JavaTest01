import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Vector;

public class SqDrawEx extends JFrame 
{
	public static final int BOXMAX = 100;
	private Rectangle[] box = new Rectangle[100];
	public int k = 0;

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
		
		Vector<Point> sv = new Vector<Point>(); // 쩍횄�횤
		Vector<Point> se = new Vector<Point>(); // 쨀징횁징

		public MyPanel()
		{
			//쨍짰쩍쨘쨀횎쨍짝 째첩횇챘�쨍쨌횓횉횠쩐횩  쨘짱쩌철쨉챕�횑 째첩�짱쨉횊쨈횢.
			MyMouseListener ml = new MyMouseListener();
			
			this.addMouseListener(ml); // 쨍짰쩍쨘쨀횎
			this.addMouseMotionListener(ml);
		}
		
		public void paintComponent(Graphics g){
			super.paintComponent(g); // 쨘횓쨍챨 횈채�횓횈짰횊짙횄창
			
			if(sv.size() != 0)
			{
				for(int i=0;i<se.size();i++)
				{ //쨘짚횇횒횇짤짹창쨍쨍횇짯
					Point sp = sv.get(i); // 쨘짚횇횒째짧�쨩짼짢쨀쨩쨈횢
					Point ep = se.get(i);	
					g.drawRect(sp.x, sp.y, ep.x-sp.x, ep.y-sp.y);//짹횞쨍짰쨈횢
				}
			}
			if(startP != null)
				g.drawRect(startP.x, startP.y, endP.x-startP.x, endP.y-startP.y);				
		}
		
		class MyMouseListener extends MouseAdapter implements MouseMotionListener{
			public void mousePressed(MouseEvent e)
			{
				startP = e.getPoint();
				sv.add(e.getPoint()); // 횇짭쨍짱횉횗쨘횓쨘횖�쨩 쩍횄�횤횁징�쨍쨌횓

				k++;
				box[k] = new Rectangle();
				
			}
			public void mouseReleased(MouseEvent e)
			{
				se.add(e.getPoint()); // 쨉책쨌징짹횞 횉횗쨘횓쨘횖�쨩 횁쩐쨌찼횁징�쨍쨌횓
				endP = e.getPoint();
				repaint(); // 쨈횢쩍횄짹횞쨌횁쨋처
			}
			
			public void mouseDragged(MouseEvent e)
			{
				endP = e.getPoint();
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