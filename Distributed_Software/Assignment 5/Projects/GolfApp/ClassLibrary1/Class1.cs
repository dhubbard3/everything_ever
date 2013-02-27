using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace ClassLibrary1 {
    public class ScoreTerms {
        //Golf score terms
        public static string scoreToTerms(int score, int par) {
            int diff = score - par;
            switch (diff) {
                case -4:
                    return "Condor";
                case -3:
                    return "Albatross";
                case -2:
                    return "Eagle";
                case -1:
                    return "Birdie";
                case 0:
                    return "Par";
                case 1:
                    return "Bogey";
                case 2:
                    return "Double Bogey";
                case 3:
                    return "Triple Bogey";
                default:
                    break;
            }
            if (diff > 3) {
                return diff.ToString() + " over par";
            }
            else if (diff < -4) {
                return "Liar!";
            }
            else {
                return "Error";
            }
        }
    }
}
