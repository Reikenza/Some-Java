import java.awt.event.*;

/** This class causes java to exit when a window is closed.
* Use this by writing<br>
*   <code>frame.addWindowListener(new ExitWhenClosed());</code><br>
* where <code>frame</code> is your main window.
*/

public  class ExitWhenClosed extends WindowAdapter
{
  /** This makes the program exit when you close a window with your window
   * manager. */
  public void windowClosing( WindowEvent e )
    {
      System.exit(0);
    }
}
