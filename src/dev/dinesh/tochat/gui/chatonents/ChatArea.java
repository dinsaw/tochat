package dev.dinesh.tochat.gui.chatonents;

import java.awt.Color;

import javax.swing.JTextPane;
import javax.swing.text.AttributeSet;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyleContext;

import dev.dinesh.tochat.util.ProgramVars;

public class ChatArea extends JTextPane
{
  private static final long serialVersionUID = 1L;
  private String lastFrom = null;
  private Color aColor = ProgramVars.FIREBRICK; private Color bColor = ProgramVars.SADDLE_BROWN; private Color currentColor = this.aColor; private Color fromColor = Color.BLACK;

  public ChatArea()
  {
  }

  public ChatArea(int width, int height)
  {
    this();
    setChatAreaProperty(width, height);
  }

  private void setChatAreaProperty(int width, int height)
  {
    System.out.println("set proprty");
    setSize(width, height);
    setFocusable(false);
    setFont(ProgramVars.getFont_A());
    setBackground(ProgramVars.LINEN);
    setEditable(false);
  }

  public void appendMessage(String from, String message)
  {
    if ((getLastFrom() == null) || (from.equals(getLastFrom()))) {
      System.out.println("equal" + from + ":" + message + "           isedit" + isEditable());
      String dfrom;
      if (from.equals(getLastFrom())) {
        dfrom = "           ";
        System.out.println("equal notnull" + from + ":" + message);
      }
      else {
        dfrom = ProgramVars.getQualifiedName(from) + ":";
        System.out.println("equal null" + from + ":" + message);
      }

      String dmesssage = message;

      append("\n" + dfrom, getFromColor());
      append(dmesssage, getCurrentColor());

      setLastFrom(from);
    } else {
      System.out.println("unequal" + from + ":" + message);
      switchCurrentColor();

      String dfrom = ProgramVars.getQualifiedName(from) + ":";
      String dmesssage = message;

      append("\n\n" + dfrom, getFromColor());
      append(dmesssage, getCurrentColor());

      setLastFrom(from);
    }
  }

  private void append(String text, Color color) {
    setEditable(true);
    StyleContext sc = StyleContext.getDefaultStyleContext();
    AttributeSet aset = sc.addAttribute(SimpleAttributeSet.EMPTY, StyleConstants.Foreground, color);
    int len = getDocument().getLength();
    setCaretPosition(len);
    setCharacterAttributes(aset, false);
    replaceSelection(text);
    setEditable(false);
  }

  public String getLastFrom()
  {
    return this.lastFrom;
  }

  public void setLastFrom(String lastFrom)
  {
    this.lastFrom = lastFrom;
  }
  private Color getCurrentColor() {
    return this.currentColor;
  }
  private void switchCurrentColor() {
    this.currentColor = (this.currentColor == this.aColor ? this.bColor : this.aColor);
  }

  public Color getFromColor()
  {
    return this.fromColor;
  }

  public void setFromColor(Color fromColor)
  {
    this.fromColor = fromColor;
  }
}