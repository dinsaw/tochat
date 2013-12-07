package dev.dinesh.tochat.gui;

import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

import dev.dinesh.tochat.util.ProgramVars;

public class ChooseRoleFrame extends JFrame
  implements ActionListener
{
  private static final long serialVersionUID = 1L;
  private JButton createHostButton;
  private JButton connectToHostButton;
  private JLabel orLabel;

  public ChooseRoleFrame()
  {
    setFrameProperty();
    initControls();
    addControls();
    setVisible(true);
  }

  private void setFrameProperty()
  {
    setSize(400, 70);
    setTitle(ProgramVars.getUSER_NAME() + ", Choose Your Role");
    setResizable(false);
    setDefaultCloseOperation(3);
    setLocationRelativeTo(null);
  }

  private void addControls()
  {
    Container cp = getContentPane();

    GroupLayout gl = new GroupLayout(cp);
    cp.setLayout(gl);

    gl.setAutoCreateGaps(true);
    gl.setAutoCreateContainerGaps(true);

    gl.setHorizontalGroup(
      gl.createSequentialGroup()
      .addComponent(this.createHostButton)
      .addComponent(this.orLabel)
      .addComponent(this.connectToHostButton));

    gl.setVerticalGroup(
      gl.createSequentialGroup()
      .addGroup(gl.createParallelGroup(GroupLayout.Alignment.LEADING)
      .addComponent(this.createHostButton)
      .addComponent(this.orLabel)
      .addComponent(this.connectToHostButton)));
  }

  private void initControls()
  {
    this.createHostButton = new JButton("Create a Host");
    this.connectToHostButton = new JButton("Connect To Host");

    this.orLabel = new JLabel("OR");

    this.createHostButton.setFont(ProgramVars.getChooseRoleFont());
    this.connectToHostButton.setFont(ProgramVars.getChooseRoleFont());

    this.createHostButton.addActionListener(this);
    this.connectToHostButton.addActionListener(this);
  }

  public void actionPerformed(ActionEvent ae) {
    Object o = ae.getSource();
    if (o == this.createHostButton) {
      ProgramVars.setHost(true);
      dispose();
      new RoleDetailsFrame();
    }
    else if (o == this.connectToHostButton) {
      ProgramVars.setHost(false);
      dispose();
      new RoleDetailsFrame();
    }
  }
}