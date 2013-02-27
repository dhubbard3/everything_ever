using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading;
using System.Diagnostics;

namespace Assignment2
{
    class Retailer
    {   
        bool farmOpen = false;
        static int threadCount, turnstile = 0;
        Random r = new Random();
        MultiCellBuffer mcb;
        Semaphore retailSem1, retailSem2;

        public Retailer(MultiCellBuffer m, int n)
        {
            mcb = m;
            threadCount = n;
            retailSem1 = new Semaphore(0, n);
            retailSem2 = new Semaphore(0, n);
        }

        internal MultiCellBuffer MultiCellBuffer
        {
            get
            {
                throw new System.NotImplementedException();
            }
            set
            {
            }
        }

        internal OrderClass OrderClass
        {
            get
            {
                throw new System.NotImplementedException();
            }
            set
            {
            }
        }

        internal EncoderDecoder EncoderDecoder
        {
            get
            {
                throw new System.NotImplementedException();
            }
            set
            {
            }
        }

        public void startRetail()  //for starting retailer threads  
        {       
            OrderClass order = new OrderClass(); //Each thread receives their own "Order Form"
            EncoderDecoder ed = new EncoderDecoder(); //Each thread receives its own Encoder, simulating an independent client-side operation
            int amt= r.Next(10,50);
            string finalOrder, callback;
            DateTime time = new DateTime();
            string format = "HH:mm:ss";
            Stopwatch watch = new Stopwatch(); //uses a Stopwatch for elapsed time

            while (farmOpen == true)
            {
                retailSem1.WaitOne(); ////////////////////////////////////////////////////////////////////////////////////////////
                    turnstile += 1;
                if (turnstile == threadCount)
                {
                    turnstile = 0;
                    retailSem2.Release(threadCount);//This block uses a double turnstile technique to prevent concurrency issues.
                }                                   // Though it still seems to have issues every now and then...
                retailSem2.WaitOne();/////////////////////////////////////////////////////////////////////////////////////////////

                time = DateTime.Now;
                watch.Start();
                order.setID(Convert.ToInt32(Thread.CurrentThread.Name)); //create new order
                order.setAmt(r.Next(10,60));
                order.setCardNo(r.Next(2000,4000));

                finalOrder = ed.encode(order); // encode order
                time = DateTime.Now; // for timestamp
                Console.WriteLine("Retailer {0} has placed an order for {1} chickens." + time.ToString(format), Thread.CurrentThread.Name, amt);
                callback = mcb.setOneCell(finalOrder); // place order and receive confirmation.
                watch.Stop();
                Console.WriteLine(callback +"\n" + watch.Elapsed);  
            }
        }
        
        public void chickenOnSale(int p)
        {  // Event handler
            Console.WriteLine("Chickens are on sale for ${0:N}.\n", p);
            retailSem1.Release(threadCount);
        }

        public void openFarm()
        {
            if (farmOpen == true)
                farmOpen = false;
            else
                farmOpen = true;
        }

    }//end Retailer
}
