import java.awt.AWTEvent;
import java.awt.MouseInfo;
import java.awt.Toolkit;
import java.awt.Color;
import java.awt.event.AWTEventListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import java.awt.AWTException;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException; 
import javax.imageio.ImageIO;
import javax.swing.JPanel;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.MouseMotionAdapter;



public class makeicon{
	static JFrame frame = new JFrame();
	static int size = 100;
	
	public static void main (String[] args)
	{
		JFrame frame2 = new JFrame();
		
		String s = (String)JOptionPane.showInputDialog(
                frame2,
                "Enter the size (in pixels) of one side of the icons...\nOdd numbers will be made even.",
                "IconMaker",
                JOptionPane.PLAIN_MESSAGE,
                null, null, "100");
		if (s == null)
		{
			System.exit(0);
		}
		
		if (s.matches("-?\\d+")) {
			size = Integer.parseInt(s);
			if (size % 2 == 1){
				size ++;
			}
		}
		
		System.out.println(size);

		//If a string was returned, say so.
//		if ((s != null) && (s.length() > 0)) {
//		setLabel("Green eggs and... " + s + "!");
//		return;
//		}
		
		
		
        Toolkit.getDefaultToolkit().addAWTEventListener(
          new Listener(), AWTEvent.MOUSE_EVENT_MASK | AWTEvent.FOCUS_EVENT_MASK);
		frame.addKeyListener(new MKeyListener());
		frame.setExtendedState(JFrame.MAXIMIZED_BOTH); 
		frame.setUndecorated(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setBackground(new Color(.5f, .5f, .5f, .01f));
		frame.setContentPane(new DrawPane());
        frame.setVisible(true);
    }

    private static class Listener implements AWTEventListener {
        public void eventDispatched(AWTEvent event) {
			if (MouseEvent.MOUSE_CLICKED == event.getID()) {
				takeScreenshot((int)MouseInfo.getPointerInfo().getLocation().getX(), (int)MouseInfo.getPointerInfo().getLocation().getY());
			}
        }
    }

	
	private static void takeScreenshot(int x, int y)
	{
       try {
    	   boolean exists = true;
    	   String fileName;
		   
           Robot robot = new Robot();
           int n = 0;
    	   do {
			   n++;

	           fileName = "./IconMaker" + Integer.toString(n) + ".png";
	           File tmpDir = new File(fileName);
	           exists = tmpDir.exists();
    	   }while(exists);
 
           // Define an area of size 500*400 starting at coordinates (10,50)
           Rectangle rectArea = new Rectangle(x-(makeicon.size/2), y-(makeicon.size/2), makeicon.size, makeicon.size);
           BufferedImage screenFullImage = robot.createScreenCapture(rectArea);
           ImageIO.write(screenFullImage, "png", new File(fileName));
		   System.out.println("Screenshot taken.");
 
       } catch (AWTException | IOException ex) {
                System.err.println(ex);
       }
    }

}
// Create a component that you can actually draw on.
   class DrawPane extends JPanel {
	   Point drawRect = new Point(0, 0);
	   
	   public DrawPane(){
		   setOpaque(false);
		   addMouseMotionListener(new MouseMotionAdapter() {
            public void mouseMoved(MouseEvent e) {
                drawRect = e.getPoint();
				repaint();
            }
			});
	   }
	   
		public void paintComponent(Graphics g) {
			g.drawRect((int)drawRect.getX()-(makeicon.size/2+1), (int)drawRect.getY()-(makeicon.size/2+1), makeicon.size+2, makeicon.size+2);
		}
		
   }

class MKeyListener extends KeyAdapter {
 
	@Override
	public void keyPressed(KeyEvent event) {
 
		char ch = event.getKeyChar();
		 
		if (ch == 'a' ||ch == 'b'||ch == 'c' ||ch == 'q' ) {
			System.exit(0);
		}
		if( ch == 'm')
				{
					makeicon.frame.setState(JFrame.ICONIFIED);
				}
			}
}

