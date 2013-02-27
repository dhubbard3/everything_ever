using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace Assignment2
{
    class OrderProcessing
    {
        private int amount, cardNo, ID, price;
        private double total, shipping = 5.99;
        private string OrderStatus = "Awaiting Processing";

        public OrderProcessing(OrderClass oc, int p)
        {
            amount = oc.getAmt();
            cardNo = oc.getCardNo();
            ID = oc.getID();
            price = p;
        }

        public void process(){ //checks card number and calculates total price for the order. Sets confirmation string.
            if (checkCardNo())
            {
                double tax = (amount * price) * .097;
                total = (amount * price) + tax + shipping;
                OrderStatus = ("Retailer " + ID + ": order processed. Total for " + amount + " chicken(s) at " + price.ToString("C") + " was " + total.ToString("C") + ".");
            }
            else
                OrderStatus = ("Retailer" + ID + ": Order could not be processed. Invalid card number.");

        }

        public bool checkCardNo()
        {
            if (cardNo >= 2000 && cardNo <= 4000)
                return true;
            else
                return false;
        }

        public string printOrder(){ //Sends confirmation string upon request.
            return OrderStatus;
        }
    }
}
