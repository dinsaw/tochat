package dev.dinesh.tochat.gui;

import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import dev.dinesh.tochat.gui.chatonents.ChatFrame;
import dev.dinesh.tochat.util.ProgramVars;

public class RoleDetailsFrame extends JFrame
  implements ItemListener, ActionListener
{
  private static final long serialVersionUID = 1L;
  private JRadioButton createHostButton;
  private JRadioButton connectRadioButton;
  private JTextField serverport;
  private JTextField yourip;
  private JTextField hostip;
  private JTextField hostport;
  private JTextField clientport;
  private JLabel spLabel;
  private JLabel youripLabel;
  private JLabel hostipLabel;
  private JLabel hostportLabel;
  private JLabel clientportLabel;
  private JButton okButton;

  public RoleDetailsFrame()
  {
    setFrameProperty();
    initControls();
    addControls();
    getRootPane().setDefaultButton(this.okButton);
    setVisible(true);
  }

  private void setFrameProperty()
  {
    setSize(400, 500);
    setTitle("Role Details");
    setResizable(false);
    setDefaultCloseOperation(3);
    setLocationRelativeTo(null);
  }

  private void initControls()
  {
    this.createHostButton = new JRadioButton("Create Host");
    this.connectRadioButton = new JRadioButton("Connect To Host");
    this.createHostButton.setSize(140, 35);
    this.connectRadioButton.setSize(140, 35);

    this.serverport = new JTextField();
    this.yourip = new JTextField();
    this.hostip = new JTextField();
    this.hostport = new JTextField();
    this.clientport = new JTextField();

    this.yourip.setSize(300, 35);
    this.serverport.setSize(200, 35);
    this.hostip.setSize(200, 35);
    this.hostport.setSize(200, 35);
    this.clientport.setSize(200, 35);

    this.yourip.setEnabled(false);
    this.yourip.setText(ProgramVars.getUserInetAddress().toString());

    this.spLabel = new JLabel("Enter Server Port     :");
    this.youripLabel = new JLabel("Your IP :");
    this.hostipLabel = new JLabel("Enter Host IP           :");
    this.hostportLabel = new JLabel("Enter Host Port       :");
    this.clientportLabel = new JLabel("Enetr your Port      :");

    this.youripLabel.setSize(70, 35);
    this.spLabel.setSize(140, 35);
    this.hostipLabel.setSize(140, 35);
    this.hostportLabel.setSize(140, 35);
    this.clientportLabel.setSize(140, 35);

    this.okButton = new JButton("Apply");
    this.okButton.setSize(100, 50);

    ButtonGroup radioGroup = new ButtonGroup();
    radioGroup.add(this.createHostButton);
    radioGroup.add(this.connectRadioButton);

    this.okButton.addActionListener(this);
    this.createHostButton.addItemListener(this);
    this.connectRadioButton.addItemListener(this);

    boolean b = ProgramVars.isHost();
    this.createHostButton.setSelected(b);
    this.connectRadioButton.setSelected(!b);
    setForHost(b);
  }

  public void setForHost(boolean host)
  {
    this.serverport.setEnabled(host);

    this.hostip.setEnabled(!host);
    this.hostport.setEnabled(!host);
    this.clientport.setEnabled(!host);
  }

  private void addControls()
  {
    Container cp = getContentPane();
    cp.setLayout(null);

    this.youripLabel.setLocation(10, 10);
    this.yourip.setLocation(80, 10);
    this.createHostButton.setLocation(10, 70);
    this.spLabel.setLocation(40, 100);
    this.serverport.setLocation(180, 100);
    this.connectRadioButton.setLocation(10, 220);
    this.hostipLabel.setLocation(40, 250);
    this.hostip.setLocation(180, 250);
    this.hostportLabel.setLocation(40, 300);
    this.hostport.setLocation(180, 300);
    this.clientportLabel.setLocation(40, 350);
    this.clientport.setLocation(180, 350);
    this.okButton.setLocation(150, 400);

    add(this.youripLabel);
    add(this.yourip);
    add(this.createHostButton);
    add(this.spLabel);
    add(this.serverport);
    add(this.connectRadioButton);
    add(this.hostipLabel);
    add(this.hostip);
    add(this.hostportLabel);
    add(this.hostport);
    add(this.okButton);
    add(this.clientportLabel);
    add(this.clientport);
  }

  public void itemStateChanged(ItemEvent ie) {
    Object o = ie.getSource();
    if ((o == this.createHostButton) || (o == this.connectRadioButton))
      setForHost(this.createHostButton.isSelected());
  }

  public void actionPerformed(ActionEvent ae)
  {
    Object o = ae.getSource();
    if (o == this.okButton)
      if (this.createHostButton.isSelected()) {
          new HostFrame(Integer.parseInt(this.serverport.getText()), 1024);
          dispose();
      }
      else {
        System.out.println("connect to host selected");
        int gclientport = Integer.parseInt(this.clientport.getText());
        int gserverPort = Integer.parseInt(this.hostport.getText());
        new ChatFrame(gclientport, gserverPort, this.hostip.getText(), 1024);
        dispose();
      }
  }
}