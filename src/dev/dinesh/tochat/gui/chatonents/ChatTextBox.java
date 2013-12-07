package dev.dinesh.tochat.gui.chatonents;

import java.awt.Color;

import javax.swing.JTextField;

import dev.dinesh.tochat.util.ProgramVars;

public class ChatTextBox extends JTextField
{
  private static final long serialVersionUID = 1L;

  public ChatTextBox()
  {
    setFont(ProgramVars.getFont_A());
    setBackground(ProgramVars.LAVENDER_BLUSH);
    setForeground(Color.BLACK);
    setHorizontalAlignment(0);
  }

  public String getText()
  {
    return super.getText().trim();
  }
}