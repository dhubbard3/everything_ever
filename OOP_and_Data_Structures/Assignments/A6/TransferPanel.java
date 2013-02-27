// Assignment #: 6
//         Name: Dave Hubbard
//    StudentID: 1202945271
//      Lecture: 3
//  Description: The TransferPanel class sets up the GUI for the Account
//				 transfer tab, updates the combo boxes for selection and
//				 handles transferring duties when a balance is entered.

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import java.util.*;

public class TransferPanel extends JPanel
 {
   private Vector accountList;
   private JLabel label1, label2, label3, label4, label5, label6;
   private JComboBox cb1, cb2;
   private JTextField tb1;
   private JButton button1;

   //Constructor initialize each component and arrange them using
   //certain layouts
   public TransferPanel(Vector accountList)
     {
      this.accountList = accountList;
      button1 = new JButton("Transfer!");
      label1 = new JLabel("Please choose FROM account for a transfer:");
      label2 = new JLabel("Please choose TO account for a transfer:");
      label3 = new JLabel("Enter a Transfer Amount:");
      label4 = new JLabel();
      label5 = new JLabel();
      label6 = new JLabel();
      tb1 = new JTextField();
      cb1 = new JComboBox(accountList);
      cb2 = new JComboBox(accountList);

	  JPanel p1 = new JPanel();
	  p1.setLayout(new GridLayout(3,1));
	  p1.add(label1);
	  p1.add(cb1);
	  p1.add(label2);

	  JPanel p2 = new JPanel();
	  p2.setLayout(new GridLayout(3,1));
	  p2.add(cb2);
	  p2.add(label3);
	  p2.add(tb1);

	  JPanel p3 = new JPanel();
	  p3.setLayout(new GridLayout(4,1));
	  p3.add(button1);
	  p3.add(label4);
	  p3.add(label5);
	  p3.add(label6);

	  setLayout(new GridLayout(3,1));
	  add(p1);
	  add(p2);
	  add(p3);

	  ButtonListener lis = new ButtonListener();
	  button1.addActionListener(lis);
     }

    //This method  refreshes the JComboBoxes with
    //updated vector information
    public void updateAccountList()
     {
       cb1.updateUI();
       cb2.updateUI();
     }

 //ButtonListener class listens to see the button "Transfer!" is pushed.
 //A user can choose which bank account to transfer from and another account to transfer to,
 //then choose a transfer amount and push the "Transfer" button.
 //This should update both FROM and TO account balances and display them
 //below the "Transfer!" button
 //(You should make use of the toString( ) method of the Account class. )
 private class ButtonListener implements ActionListener
  {
       public void actionPerformed(ActionEvent event)
        {
       		try{
       			String amt = tb1.getText();

       			int a1 = cb1.getSelectedIndex();
       			int a2 = cb2.getSelectedIndex();

       			Account trans1 = (Account)accountList.get(a1); //currently selected accounts
       			Account trans2 = (Account)accountList.get(a2);

       			double bal = Double.parseDouble(amt);
       			double nb1 = trans1.getAmount(); // current balances
       			double nb2 = trans2.getAmount();

       			if(a1 != a2){ // ensures same account is not selected
       				nb1 = nb1 - bal;
       				nb2 = nb2 + bal;
       				trans1.setAmount(nb1);
       				trans2.setAmount(nb2);
       				label4.setText("The current balances are:");
					label5.setText(trans1.toString());
					label6.setText(trans2.toString());
				}
				else{
					label4.setText("Please choose different accounts."); // if same account
				}
			}
			catch(NumberFormatException ex){
				label4.setText("Please enter a number for the transfer amount");
			}
        }
  } //end of ButtonListener class

} //end of TransferPanel class
