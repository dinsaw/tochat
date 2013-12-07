package dev.dinesh.tochat.gui;

import java.awt.Container;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import dev.dinesh.tochat.util.ProgramVars;

public class WelcomeFrame extends JFrame
  implements ActionListener, KeyListener
{
  private static final long serialVersionUID = 1L;
  private JLabel nameLabel;
  private JLabel noteLabel;
  private JButton enterButton;
  private JTextField nameField;

  public void setNameLabel(JLabel nameLabel)
  {
    this.nameLabel = nameLabel;
  }

  public void setNoteLabel(JLabel noteLabel)
  {
    this.noteLabel = noteLabel;
  }

  public WelcomeFrame()
  {
    super(ProgramVars.getWelcometitle());
    setFrameProperty();
    initcontrols();
    addcontrols();
    getRootPane().setDefaultButton(this.enterButton);
    setVisible(true);
  }

  private void setFrameProperty()
  {
    setSize(500, 200);
    setResizable(false);
    setDefaultCloseOperation(3);
    setLocationRelativeTo(null);
  }

  public JTextField getNameField()
  {
    return this.nameField;
  }

  public void setNameField(JTextField nameField)
  {
    this.nameField = nameField;
  }
  private void addcontrols() {
    Container cp = getContentPane();

    GroupLayout gl = new GroupLayout(cp);
    cp.setLayout(gl);
    gl.setAutoCreateGaps(true);
    gl.setAutoCreateContainerGaps(true);
    gl.setAutoCreateContainerGaps(true);
    gl.setHorizontalGroup(
      gl.createSequentialGroup()
      .addGroup(gl.createParallelGroup(GroupLayout.Alignment.LEADING)
      .addComponent(this.nameLabel)
      .addComponent(this.nameField)
      .addComponent(this.noteLabel))
      .addComponent(this.enterButton));

    gl.setVerticalGroup(
      gl.createSequentialGroup()
      .addComponent(this.nameLabel)
      .addGroup(gl.createParallelGroup(GroupLayout.Alignment.LEADING)
      .addComponent(this.nameField)
      .addComponent(this.enterButton))
      .addComponent(this.noteLabel));
  }

  private void initcontrols()
  {
    this.nameLabel = new JLabel("Enter your Name ");
    this.noteLabel = new JLabel("This name will be displayed in chat.");
    this.nameField = new JTextField(15);
    Font nameFont = new Font("Calibri", 0, 40);
    this.nameField.setFont(nameFont);
    this.enterButton = new JButton("OK");
    this.enterButton.setEnabled(false);
    this.enterButton.addActionListener(this);
    this.nameField.addKeyListener(this);
  }

  public void actionPerformed(ActionEvent ae) {
    if (ae.getSource() == this.enterButton) {
      dispose();
      ProgramVars.setUSER_NAME(this.nameField.getText().trim());
      new ChooseRoleFrame();
    }
  }

  public void keyPressed(KeyEvent arg0)
  {
  }

  public void keyReleased(KeyEvent arg0)
  {
    this.enterButton.setEnabled(this.nameField.getText().trim().length() != 0);
  }

  public void keyTyped(KeyEvent arg0)
  {
  }
}