import java.io.*;

/** A Message object represents a single message to the chat
 * room consisting of a name and a string of text. */

public class Message
  implements Serializable
{
  public Message()
    {
      this( "", "" );
    }

  public Message( String name, String text )
    {
      myName = name;
      myText = text;
    }

  public void setName( String name )
    {
      myName = name;
    }

  public void setText( String text )
    {
      myText = text;
    }

  public String getName()
    {
      return myName;
    }

  public String getText()
    {
      return myText;
    }

  public String toString()
    {
      StringBuffer sb = new StringBuffer();
      sb.append( myName );
      sb.append( ": " );
      sb.append( myText );
      return sb.toString();
    }

  private String myName;
  private String myText;

}
