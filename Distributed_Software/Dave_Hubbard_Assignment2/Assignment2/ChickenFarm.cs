using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading;
using System.Diagnostics;

namespace Assignment2
{
    public delegate void priceCutter(int price);

    class ChickenFarm
    {
        static Random r = new Random();
        Retailer retail;
        static MultiCellBuffer mcb;
        public static event priceCutter priceCut; // Define event
        private static int chickenPrice = 4;
        private static int p;


        public ChickenFarm()
        { }

        public ChickenFarm(Retailer r, MultiCellBuffer m)
        {
            retail = r;
            mcb = m;
            openFarm();
        }

        public int getPrice() { 
            return chickenPrice; 
        }

        public void openFarm()
        {
            retail.openFarm();
        }


        public void PricingModel()
        {
            Stopwatch watch = new Stopwatch(); // for time elapsed
            DateTime time = new DateTime();
            time = DateTime.Now;
            string format = "HH:mm:ss";

            Console.WriteLine("Chicken farm is open! Start time: "+ time.ToString(format));
            watch.Start();
            int price;
            while (p <= 10)
            {
                Thread.Sleep(2000); //price is updated every 2 seconds 
                price = r.Next(4, 10);
                Console.WriteLine("Current price is ${0:N}", chickenPrice);
                changePrice(price);
            }
            watch.Stop();
            retail.openFarm();
            Thread.Sleep(3000);
            Console.WriteLine("The Chicken farm has closed! Elapsed time: {0}", watch.Elapsed);
            Console.WriteLine("Press any key to quit");
            Console.ReadLine();
        }

        public static void changePrice(int price)
        {
            if (price < chickenPrice)
            {
                if (priceCut != null)
                {  
                    Console.WriteLine("\n******Price Cut!******\n");
                    priceCut(price); // emit event to subscribers
                    p++;
                }
            }
            chickenPrice = price;
        }

        public string processOrder(int cell) //sends an order to the Order Processing class.
        {
            string ord;
            ord = mcb.getOneCell(cell);
            EncoderDecoder ed = new EncoderDecoder();
            OrderProcessing op = new OrderProcessing(ed.decode(ord),chickenPrice);

            Thread order = new Thread(new ThreadStart(op.process));
            order.Start();
            while (order.IsAlive)
            {
                Thread.Sleep(50);
            }
            return op.printOrder();
        }

        public priceCutter priceCutter
        {
            get
            {
                throw new System.NotImplementedException();
            }
            set
            {
            }
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

        internal OrderProcessing OrderProcessing
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

        internal Retailer Retailer
        {
            get
            {
                throw new System.NotImplementedException();
            }
            set
            {
            }
        }

    }//end ChickenFarm
}
