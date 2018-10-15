import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.net.*;

/** This class is a panel which has some informative labels, a text
 * area listing messages, and a line at the bottom where you can type
 * a new message and press enter to send it. */

public class SimpleUI extends Panel
{
  
  /**
   * Construct a SimpleUI.
   * 
   * @param name the name you are using to chat with
   *
   * @param c the client object which receives incoming messages and
   * sends outgoing messages
   *
   * @param host the name of the host where the server is (or the
   * multicast address you are using)
   *
   * @param port the IP port used by the client
   */

  public SimpleUI( String name, Client c, String host, int port )
    {
      super( new BorderLayout() );

      myClient = c;
      c.setHandler( new MessageAdder() );

      myName = name;

      Panel p = new Panel( new GridLayout( 2, 1 ) );
      p.add( new Label( "Name: " + name ) );
      p.add( new Label( "Chat room " + host + ':' + port ) );

      add( p, "North" );

      myMessages = new TextArea( "", 15, 30, 
                                 TextArea.SCROLLBARS_VERTICAL_ONLY );
      myMessages.setEditable( false );
      add( myMessages, "Center" );

      myEntry = new TextField();
      myEntry.setEditable( true );
      myEntry.addKeyListener( new MessageSender() );
      add( myEntry, "South" );
    }

  private String myName;
  private TextArea myMessages;
  private TextField myEntry;
  private Client myClient;

  class MessageAdder implements MessageHandler
  {
    public void handleMessage( Message m )
      {
        int where;
         where = myMessages.getText().length();
         myMessages.insert( m.toString() + '\n', where );
         where = myMessages.getText().length();
         myMessages.setCaretPosition( where );
      }
  }

  class MessageSender extends KeyAdapter
  {
    public void keyPressed( KeyEvent e )
      {
        if( e.getKeyCode() == KeyEvent.VK_ENTER )
          {
            Message m = new Message( myName, myEntry.getText() );
            try
              {
                myClient.sendMessage( m );
              }
            catch( IOException ex )
              {
                System.out.println( "Lost contact with server." );
                System.exit(0);
              }
            myEntry.setText( "" );
          }
      }
  }
}
