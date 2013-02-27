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
            Console.WriteLine("Enter URL. http://");
            string url = Console.ReadLine();
            testProxy.Service1Client tp = new testProxy.Service1Client();
            string[] sa = tp.WsOperations(url);
            foreach (string s in sa)
            {
                Console.WriteLine(s);
            }
            Console.WriteLine("Press Enter to quit.");
            Console.ReadLine();

        }
    }
}
