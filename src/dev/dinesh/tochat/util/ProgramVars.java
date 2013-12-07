package dev.dinesh.tochat.util;

import java.awt.Color;
import java.awt.Font;
import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * @author dinesh
 *
 */
public class ProgramVars
{
  private static String SFRAMETITLE = "T-O-C-H-A-T By DBS _____________ Server";
  private static String CFRAMETITLE = "T-O-C-H-A-T By DBS _____________ Client";
  private static String USER_NAME = null;
  private static String welcometitle = "Welcome in T-O-C-H-A-T by_dbs [v0.6 a]";

  private static int SERVERPORT = 5555; private static int COSERVERPORT = 5556; private static int CLIENTPORT = 5557;
  public static final String USER_NAME_SPACE = "          ";
  private static boolean host = true;

  private static Font ConversationBoxFont = new Font("Futura", 0, 20);
  private static Font ChooseRoleFont = new Font("Futura", 0, 20);

  private static Font Font_A = new Font("Monospaced", 0, 15);
  private static Font Font_B = new Font("Monospaced", 0, 20);

  public static final Color LINEN = new Color(240, 240, 230);

  public static final Color PEACH_PUFF = new Color(255, 218, 185);

  public static final Color LAVENDER_BLUSH = new Color(255, 240, 245);

  public static final Color SADDLE_BROWN = new Color(139, 69, 19);

  public static final Color FIREBRICK = new Color(178, 34, 34);

  public static final Color MAROON = new Color(176, 48, 96);
  public static final String CONNECT = "~!connect!~";
  public static final String DISCONNECT = "~!disconnect!~";

  public static int getUserNameCharLim()
  {
    return 10;
  }

  public static Font getFont_A()
  {
    return Font_A;
  }

  public static void setFont_A(Font font_A)
  {
    Font_A = font_A;
  }

  public static Font getFont_B()
  {
    return Font_B;
  }

  public static void setFont_B(Font font_B)
  {
    Font_B = font_B;
  }
  public static InetAddress getUserInetAddress() {
    try {
      return InetAddress.getLocalHost();
    }
    catch (UnknownHostException e) {
      e.printStackTrace();
    }
    return null;
  }

  public static Font getChooseRoleFont()
  {
    return ChooseRoleFont;
  }

  public static void setChooseRoleFont(Font chooseRoleFont)
  {
    ChooseRoleFont = chooseRoleFont;
  }

  public static String getWelcometitle()
  {
    return welcometitle;
  }

  public static void setWelcometitle(String welcometitle)
  {
    ProgramVars.welcometitle = welcometitle;
  }

  public static String getSFRAMETITLE()
  {
    return SFRAMETITLE;
  }

  public static void setSFRAMETITLE(String sFRAMETITLE)
  {
    SFRAMETITLE = sFRAMETITLE;
  }

  public static String getCFRAMETITLE()
  {
    return CFRAMETITLE;
  }

  public static void setCFRAMETITLE(String cFRAMETITLE)
  {
    CFRAMETITLE = cFRAMETITLE;
  }

  public static Font getConversationBoxFont()
  {
    return ConversationBoxFont;
  }

  public static void setConversationBoxFont(Font conversationBoxFont)
  {
    ConversationBoxFont = conversationBoxFont;
  }

  public static String getUSER_NAME()
  {
    return USER_NAME;
  }

  public static void setUSER_NAME(String uSER_NAME)
  {
    USER_NAME = uSER_NAME;
  }

  public static String getQualifiedName(String unqual)
  {
    String appSpaceString = "";
    if ((unqual.length() != getUserNameCharLim()) && (unqual != null)) {
      int diff = getUserNameCharLim() - unqual.length();
      for (int i = 0; i < diff; i++) {
        appSpaceString = appSpaceString + " ";
      }
      unqual = unqual.concat(appSpaceString);
    }
    return unqual;
  }

  public static int getSERVERPORT()
  {
    return SERVERPORT;
  }

  public static void setSERVERPORT(int sERVERPORT)
  {
    SERVERPORT = sERVERPORT;
  }

  public static int getCOSERVERPORT()
  {
    return COSERVERPORT;
  }

  public static void setCOSERVERPORT(int cOSERVERPORT)
  {
    COSERVERPORT = cOSERVERPORT;
  }

  public static int getCLIENTPORT()
  {
    return CLIENTPORT;
  }

  public static void setCLIENTPORT(int cLIENTPORT)
  {
    CLIENTPORT = cLIENTPORT;
  }

  public static boolean isHost()
  {
    return host;
  }

  public static void setHost(boolean host)
  {
    ProgramVars.host = host;
  }
}