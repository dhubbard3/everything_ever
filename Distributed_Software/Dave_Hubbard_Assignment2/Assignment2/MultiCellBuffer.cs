using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading;

namespace Assignment2
{
    class MultiCellBuffer
    {
        private int maxCells = 0;
        private string[] cells;
        private Semaphore sem;
 

        public MultiCellBuffer(int n)
        {
            maxCells = n;
            cells = new string[n];
            for(int i = 0; i < n; i++)
            {
                cells[i] = "";
            }
            sem = new Semaphore(n, n);
        }

        public string setOneCell(string s){
            sem.WaitOne();
            int cell = -1;
            string result;
            lock (cells) //locks cell array for empty cell searching
            {
                for (int i = 0; i < maxCells; i++)
                {
                    if (cells[i] == "")
                        cell = i;
                }

                if (cell != -1)
                {
                    cells[cell] = s;
                }
                else
                {
                    Console.WriteLine("Error. No empty cells found.");
                }
            }
            ChickenFarm c = new ChickenFarm();
            result = c.processOrder(cell); //upon finding writing to a cell, the farm is called to get the order.
            cells[cell] = "";
            sem.Release(); // cell is emptied and made available
            return result;
        }

        public string getOneCell(int cellNo){
            return cells[cellNo];
        }
    }
}
