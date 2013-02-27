using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace TipCalculator
{
    public delegate void Notifier();
    public delegate void Error(int n);
    class CalcModel
    {
        private static int numOfGuests, view, rating;
        private static double bill, totalBill, tipRate, tax, deduc, totalTip, PPT, adjustedTip, aRate, baseT;
        private static double minRate, maxRate;
        private bool addDeduc, addTax, pptAdjusted;
        private double[] guestValues;
        public static event Notifier notify;
        public static event Error error;

        public CalcModel()
        {
            minRate = 5;
            maxRate = 25;
            numOfGuests = 1;
            bill = 0;
            totalBill = 0;
            tipRate = 15;
            tax = 0;
            deduc = 0;
            totalTip = 0;
            PPT = 0;
            adjustedTip = 0;
            aRate = 0;
            addDeduc = true;
            addTax = false;
            pptAdjusted = false;
            guestValues = new double[10];
            baseT = 0;
            rating = 3;

        }

        public void currentView(int i)
        {
            view = i;
            notify();
        }

        public int getView()
        {
            return view;
        }

        public void setRating(int n)
        {
            rating = n;
            changePresetRate();
            getPPT();
            setGuestPPT();
            notify();
        }

        public int getRating()
        {
            return rating;
        }

        public double calculateBaseTotal()
        {
            double total = bill;
            if (addDeduc == true)
                total -= deduc;
            if (addTax == true)
                total += tax;

            return total;
        }

        public double calculateGrandTotal()
        {
            baseT = calculateBaseTotal();
            double tip = calcualteTotalTip();
            totalBill = baseT + tip;
            return totalBill;
        }

        public double calcualteTotalTip()
        {
            double baseT = calculateBaseTotal();
            if (pptAdjusted)
            {
                adjustedTip = calculateGuestValues();
                return adjustedTip;
            }
            else
                totalTip = baseT * (tipRate / 100);
            return totalTip;
        }

        public double calcualteAdjustedTip()
        {
            double baseT = calculateBaseTotal();
            adjustedTip = baseT * (tipRate / 100);
            return adjustedTip;
        }

        public void calculateAdjustedPortion(int n, int m)
        {
            getPPT();
            double d = (n / 10.0);
            adjustedTip = totalTip;
            double result = adjustedTip * (d / 100.0);
            guestValues[m] = result;
            notify();
        }

        public double findArate()
        {
            return (totalBill-baseT) / baseT;
        }

        public double calculateGuestValues()
        {
            double d = 0.0;
            for (int i = 0; i < numOfGuests; i++)
            {
                d += guestValues[i];
            }
            adjustedTip = d;
            return adjustedTip;
        }

        public void getPPT()
        {
            if (!pptAdjusted)
            {
                double p = calcualteTotalTip();
                PPT = p / numOfGuests;
                setGuestPPT();
            }
            else PPT = -1;
        }

        public double PPTStatus()
        {
            return PPT;
        }
        
        public void changeGuestNum (int n)
        {
            numOfGuests = n;
            if (numOfGuests > 10 || numOfGuests < 1)
            {
                numOfGuests = 1;
                error(3);
            }
            getPPT();
            notify();
        }

        public int getGuestNum()
        {
            return numOfGuests;
        }

        public void changeBill(double m)
        {
            bill = m;
            if (bill < 0)
            {
                bill = 0;
                error(6);
            }
            getPPT();
            notify();
        }

        public double getBill()
        {
            return bill;
        }

        public void changeTotalBill(double m)
        {
            totalBill = m;
            notify();
        }

        public void changeDeduc(double m)
        {
            deduc = m;
            if (deduc > bill || deduc < 0)
            {
                deduc = 0;
                error(4);
            }
            getPPT();
            notify();
        }

        public double getDeduc()
        {
            return deduc;
        }

        public void changeTax(double m)
        {
            tax = m;
            if (tax < 0)
            {
                tax = 0;
                error(5);
            }
            getPPT();
            notify();
        }

        public double getTax()
        {
            return tax;
        }

        public void changeTipRate(double m)
        {
            tipRate = m;
            notify();
        }

        public double getTipRate()
        {
            if (PPT < 0)
                return adjustedTip * 10;
            else
            return tipRate;
        }

        public void changeMinTax(double m)
        {
            minRate = m;
            if (minRate > maxRate)
            {
                minRate = 5;
                maxRate = 25;
                error(1);
            }
            changePresetRate();
            notify();
        }

        public void changeMaxTax(double m)
        {
            maxRate = m;
            if (maxRate < minRate)
            {
                minRate = 5;
                maxRate = 25;
                error(2);
            }
            changePresetRate();
            notify();
        }

        public double getMinTax()
        {
            return minRate;
        }

        public double getMaxTax()
        {
            return maxRate;
        }

        public void changePresetRate()
        {
            double midRate = (minRate + maxRate) / 2;
            switch (rating)
            {
                case 1: tipRate = minRate;
                    break;
                case 2: tipRate = (minRate + midRate) / 2;
                    break;
                case 3: tipRate = midRate;
                    break;
                case 4: tipRate = (midRate + maxRate) / 2;
                    break;
                case 5: tipRate = maxRate;
                    break;
            }
            notify();
        }

        public void changeDeducStatus()
        {
            if (addDeduc == true)
                addDeduc = false;
            else
                addDeduc = true;
            notify();
        }

        public void changetaxStatus()
        {
            if (addTax == true)
                addTax = false;
            else
                addTax = true;
            notify();
        }

        public void tipAdjust()
        {
            if (pptAdjusted == true)
                pptAdjusted = false;
            else
                pptAdjusted = true;
        }

        public bool pptStatus()
        {
            return pptAdjusted;
        }

        public void setGuestPPT()
        {
            for (int i = 0; i < numOfGuests; i++)
            {
                guestValues[i] = PPT;
            }
        }

        public double getGuestPPT(int i)
        {
            return guestValues[i];
        }
    }
}
