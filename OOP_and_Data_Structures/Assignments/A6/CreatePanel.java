// Assignment #: 6
//         Name: Dave Hubbard
//    StudentID: 1202945271
//      Lecture: 3
//  Description: The CreatePanel class organizes the user interface under the account
//				 creation tab. It also updates the account list shared among classes,
//				 displays all accounts and warns the user if any information they entered
//				 is not accepted.

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;

public class CreatePanel extends JPanel
 {
   private Vector accountList;
   private JButton button1;
   private TransferPanel transferPanel;
   private JTextField text1, text2;
   private JLabel label1, label2, label3;
   private JTextArea ta;

 //Constructor initializes components and organize them using certain layouts
 public CreatePanel(Vector accountList, TransferPanel tPanel)
  {
    this.accountList = accountList;
    this.transferPanel = tPanel;
    label1 = new JLabel();
    label2 = new JLabel("Account ID");
    label3 = new JLabel("Amount");
    text1 = new JTextField(12);
    text2 = new JTextField(12);
    button1 = new JButton("Create an Account");
    ta = new JTextArea();
    ta.setText("No Account");

    JPanel p1 = new JPanel();
    p1.setLayout(new GridLayout(2,2));
    p1.add(label2);
    p1.add(text1);
    p1.add(label3);
    p1.add(text2);

    JPanel p2 = new JPanel();
    p2.setLayout(new FlowLayout());
    p2.add(button1);

    JPanel p3 = new JPanel();
    p3.setLayout(new GridLayout(3,1));
    p3.add(label1);
    p3.add(p1);
    p3.add(p2);

    setLayout(new GridLayout(1,2));
    add(p3);
    add(ta);

    label1.setForeground(Color.RED);

    ButtonListener lis = new ButtonListener();
    button1.addActionListener(lis);

  }

/*/////////////////////////////////////////// ButtonListener //
 A listener class that listens to see if the button "Create
 an account" is pushed.When the event occurs, it adds an
 account information from the text fields to the text. It also
 creates an Account object using these two information and add
 it to the accountList. It also does error checking.
////////////////////////////////////////////////////////////*/
  private class ButtonListener implements ActionListener
   {
    public void actionPerformed(ActionEvent event)
     {

			 String id = text1.getText().trim();
			 String amt = text2.getText().trim();

			 if((id.length() == 0 || amt.length() == 0)){		//checks for empty text boxes.
			 	label1.setText("Please fill both id and amount");
			 }
			 else{

				 try{
					double bal = Double.parseDouble(amt);
			 		Account acct = new Account(id, bal);

			// attempts to check the status of account list to determine first account.
					if(accountList.isEmpty())
						ta.setText(acct.toString()+"\n");
					else
						ta.append(acct.toString()+"\n");

					accountList.add(acct);
					label1.setText("Account added.");
					transferPanel.updateAccountList();
			 	 }

				 catch(NumberFormatException ex){
			 		label1.setText("Please enter a number for the amount.");
		 		 }
			 }
     }

   }//end ButtonListener

} //end of CreatePanel class