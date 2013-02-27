using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace TestWcfServiceConsoleApp
{
    class Program
    {
        static void Main(string[] args)
        {
            myServiceRef.ServiceClient myWcfProxy = new myServiceRef.ServiceClient();
            Int32 test1 = 5;
            Int32 test2 = 13;
            Double result1 = myWcfProxy.root(test2);
            Int32 result2 = myWcfProxy.doubleThenAdd(test1, test2);
            Console.WriteLine("Square root of {0} = {1}", test2, result1);
            Console.WriteLine("The numbers {0} and {1} both doubled and then added together = {2}.", test1, test2, result2);
            myWcfProxy.Close();
            Console.WriteLine("Press <ENTER> to terminate the client");
            Console.ReadLine();
        }
    }
}
