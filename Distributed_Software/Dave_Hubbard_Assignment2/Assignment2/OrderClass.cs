using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace Assignment2
{
    //The order class has an instance created for each thread to avoid concurrency issues.
    class OrderClass
    {
        private int senderID, cardNo, amount;

        public void setID(int id)
        {
            senderID = id;
        }

        public int getID()
        {
            return senderID;
        }

        public void setCardNo(int num)
        {
            cardNo = num;
        }

        public int getCardNo()
        {
            return cardNo;
        }

        public void setAmt(int amt)
        {
            amount = amt;
        }

        public int getAmt()
        {
            return amount;
        }

        public string toString()
        {
            return ("Retailer: " + senderID + " Card Number: " + cardNo + " Amount: " + amount);
        }


    }//end OrderClass
}
