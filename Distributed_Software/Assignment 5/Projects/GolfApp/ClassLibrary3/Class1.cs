using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace ClassLibrary3 {
    public class Conversion {
        //Convert Yards to Meters
        public static double yardsToMeters(double yards) {
            return yards * 0.9144;
        }

        //Convert Meters to Yards
        public static double metersToYards(double meters) {
            return meters * 1.0936133;
        }
    }
}
