package dev.dinesh.tochat.starters;

import javax.swing.UnsupportedLookAndFeelException;

import dev.dinesh.tochat.gui.WelcomeFrame;

/**
 * @author dinesh
 *
 */
public class MainClass
{
  public static void main(String[] args)
    throws ClassNotFoundException, InstantiationException, IllegalAccessException, UnsupportedLookAndFeelException
  {
    new WelcomeFrame();
  }
}