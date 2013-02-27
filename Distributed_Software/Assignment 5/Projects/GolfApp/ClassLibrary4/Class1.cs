using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace ClassLibrary4 {
    public class ClubID {
        // Club Identifier
        public static string clubIdentifier(bool isMan, int yards) {
            if (isMan) {
                if (yards < 65)
                    return "Putter";
                else if ((yards >= 65) && (yards < 80))
                    return "Lob Wedge";
                else if ((yards >= 80) && (yards < 90))
                    return "Sand Wedge";
                else if ((yards >= 90) && (yards < 100))
                    return "Gap Wedge";
                else if ((yards >= 100) && (yards < 110))
                    return "Pitching Wedge";
                else if ((yards >= 110) && (yards < 130))
                    return "9 Iron";
                else if ((yards >= 130) && (yards < 140))
                    return "8 Iron";
                else if ((yards >= 140) && (yards < 150))
                    return "7 Iron";
                else if ((yards >= 150) && (yards < 160))
                    return "6 Iron";
                else if ((yards >= 160) && (yards < 170))
                    return "5 Iron";
                else if ((yards >= 170) && (yards < 180))
                    return "4 Iron";
                else if ((yards >= 180) && (yards < 190))
                    return "3 Iron";
                else if ((yards >= 190) && (yards < 200))
                    return "2 Iron";
                else if ((yards >= 200) && (yards < 210))
                    return "5 Wood";
                else if ((yards >= 210) && (yards < 230))
                    return "3 Wood";
                else if (yards >= 230)
                    return "1 Wood (Driver)";
                else
                    return "Error";
            }
            else {
                if (yards < 35)
                    return "Putter";
                else if ((yards >= 35) && (yards < 50))
                    return "Lob Wedge";
                else if ((yards >= 50) && (yards < 60))
                    return "Sand Wedge";
                else if ((yards >= 60) && (yards < 70))
                    return "Gap Wedge";
                else if ((yards >= 70) && (yards < 80))
                    return "Pitching Wedge";
                else if ((yards >= 80) && (yards < 90))
                    return "9 Iron";
                else if ((yards >= 90) && (yards < 100))
                    return "8 Iron";
                else if ((yards >= 100) && (yards < 110))
                    return "7 Iron";
                else if ((yards >= 110) && (yards < 120))
                    return "6 Iron";
                else if ((yards >= 120) && (yards < 130))
                    return "5 Iron";
                else if ((yards >= 130) && (yards < 140))
                    return "4 Iron";
                else if ((yards >= 140) && (yards < 150))
                    return "3 Iron";
                else if ((yards >= 150) && (yards < 160))
                    return "2 Iron";
                else if ((yards >= 160) && (yards < 170))
                    return "5 Wood";
                else if ((yards >= 170) && (yards < 180))
                    return "3 Wood";
                else if (yards >= 180)
                    return "1 Wood (Driver)";
                else
                    return "Error";
            }
        }
    }
}
