using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace ConsoleApplication1
{
    class Program
    {
        static void Main(string[] args)
        {
            ServiceReference1.Service1Client tp = new ServiceReference1.Service1Client();
            string zip = Console.ReadLine();
            string info = tp.ZipCodeDistance(zip, zip);
            Console.WriteLine(info);
            Console.ReadLine();
        }
    }
}
