# IconCutter
100x100px Icon Cutter<br/>
<br/>
Program originally designed for personal use to make 100x100px Icons, and then shared with the community.<br/>
Please note that for this reason the code involved is not up to standard and is very messy.<br/>
<br/>
Features:<br/>
This is a very simple program that when launched will result in a black square following the pointer.  When the mouse button is clicked, the program will save what is on the screen within that square to the directory the program was launched from.<br/>
Pressing the a, b, c, or q keys will terminate the program.<br/>
Pressing the m key will minimize the program, effectively suspending operations.  Operation can be resumed by clicking on it, or similarly juggled by ALT / CMD + TABbing through active programs.<br/>
<br/>
Notes:<br/>
The program will name each Icon a number between 0 and 200,000,000 ergo, there is a steadily increasing chance starting from 1 in 200,000,000 that a clash will occur, and a previous icon will be lost.  <br/>
Checking that the file doesn't exist before writing may be a topic of future updates.<br/>
<br/>
Questions:<br/>
Is there a way to change from 100x100px?<br/>
Theoretically yes, but it involves manually changing the code and if you have to ask I imagine that's probably not something you want to mess with. <br/>
If you want to give it a shot you need to change these two lines:<br/>
           Rectangle rectArea = new Rectangle(x-50, y-50, 100, 100);<br/>
		    	 g.drawRect((int)drawRect.getX()-51, (int)drawRect.getY()-51, 102, 102);<br/>
Where:<br/>
           Rectangle rectArea = new Rectangle(x-(TargetSize/2), y-(TargetSize/2), TargetSize, TargetSize);<br/>
		    	 g.drawRect((int)drawRect.getX()-(TargetSize/2+1), (int)drawRect.getY()-(TargetSize/2), TargetSize+2, TargetSize+2);<br/>
Then you can compile the code by navigating to the directory in the command line and <br/>
           javac makeicon.java<br/>
           java makeicon<br/>
If this is your first time messing with code and it doesn't work, sorry, but there's around 500 places where something could go wrong. <br/>
