using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading;

namespace Assignment2
{
    class MainDriver
    {
        private const int retailerNum = 10; //Number of retailers
        private const int multiCellNum = 4;

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

        internal ChickenFarm ChickenFarm
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
        } //Number of buffer cells
        
        static void Main(string[] args)
        {
            MultiCellBuffer mcb = new MultiCellBuffer(multiCellNum);
            Retailer retail = new Retailer(mcb, retailerNum);
            ChickenFarm chicken = new ChickenFarm(retail, mcb);
           
            ChickenFarm.priceCut += new priceCutter(retail.chickenOnSale);

            Thread farm = new Thread(new ThreadStart(chicken.PricingModel));
            farm.Start(); //Single farm thread. 
      
            Thread[] retailers = new Thread[retailerNum];
            for (int i = 0; i < retailerNum; i++)
            {   // Start N retailer threads
                retailers[i] = new Thread(new ThreadStart(retail.startRetail));
                retailers[i].Name = (i + 1).ToString();
                retailers[i].Start();
            }

        }
    }
}
