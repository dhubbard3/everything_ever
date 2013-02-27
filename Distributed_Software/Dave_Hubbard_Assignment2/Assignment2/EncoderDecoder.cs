using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace Assignment2
{
    //The EncoderDecoder class has an instance created for each thread to avoid concurrency issues.
    class EncoderDecoder
    {
        public string encode(OrderClass order)
        {
            int id = order.getID();
            int card = order.getCardNo();
            int amt = order.getAmt();
            return id + "&" + card + "&" + amt + "&";
        }

        public OrderClass decode(string order)
        {
            string[] Split = order.Split(new Char[] {'&'});
            OrderClass oc = new OrderClass();
            oc.setID(Convert.ToInt32(Split[0]));
            oc.setCardNo(Convert.ToInt32(Split[1]));
            oc.setAmt(Convert.ToInt32(Split[2]));
            return oc;
        }
    }//end EncoderDecoder
}
