import java.awt.AWTEvent;
import java.awt.MouseInfo;
import java.awt.Toolkit;
import java.awt.Color;
import java.awt.event.AWTEventListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import javax.swing.JFrame;
import javax.swing.JTextField;
import java.awt.AWTException;
import java.awt.FlowLayout;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException; 
import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.util.Random;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.MouseMotionAdapter;



public class makeicon{
	static JFrame frame = new JFrame();
	
	public static void main (String args[])
	{
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
		   Random rand = new Random();
		   int n = rand.nextInt(200000000);
		   
           Robot robot = new Robot();
           String fileName = "./" + Integer.toString(n) + ".png";
 
           // Define an area of size 500*400 starting at coordinates (10,50)
           Rectangle rectArea = new Rectangle(x-50, y-50, 100, 100);
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
				//g.fillRect(20, 20, 100, 200); // Draw on g here e.g.
				g.drawRect((int)drawRect.getX()-51, (int)drawRect.getY()-51, 102, 102);
			}
			
	   }

	class MKeyListener extends KeyAdapter {
	 
		@Override
		public void keyPressed(KeyEvent event) {
			System.exit(0);	
		}
	}

