using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace ClassLibrary2 {
    public class ParCalculate {
        //Par calculator. Given distance in yards. Return par
        public static int parCalculator(int yards) {
            if (yards < 100)
                return 2;
            else if ((yards >= 100) && (yards <= 250))
                return 3;
            else if ((yards > 250) && (yards <= 450))
                return 4;
            else if (yards > 450)
                return 5;
            else
                return 0;
        }
    }
}
