using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Windows.Forms;

namespace TipCalculator
{
    class CalcController
    {
        CalcModel m;
        public CalcController(CalcModel model)
        {
            m = model;
        }

        public void trackBar1_Scroll(object sender, System.EventArgs e)
        {
            TrackBar bar = (TrackBar)Convert.ChangeType(sender, typeof(TrackBar));
            if (m.pptStatus())
                m.tipAdjust();
            m.setRating(bar.Value);
        }

        public void tipCalcBtn_Click(object sender, EventArgs e)
        {
            m.currentView(0);
        }

        public void tailorBtn_Click(object sender, EventArgs e)
        {
            m.currentView(1);
        }

        public void configBtn_Click(object sender, EventArgs e)
        {
            m.currentView(2);
        }

        public void tipSlider_Scroll(object sender, System.EventArgs e)
        {
            TrackBar bar = (TrackBar)Convert.ChangeType(sender, typeof(TrackBar));
            if (m.pptStatus())
                m.tipAdjust();
            double d = bar.Value/10.0;
            m.changeTipRate(d);
        }

        public void totalBillBox_Leave(object sender, EventArgs e)
        {
            TextBox box = (TextBox)Convert.ChangeType(sender, typeof(TextBox));
            m.changeBill(Convert.ToDouble(box.Text));
            m.getPPT();
        }

        public void guestNoBox_Leave(object sender, EventArgs e)
        {
            TextBox box = (TextBox)Convert.ChangeType(sender, typeof(TextBox));
            m.changeGuestNum(Convert.ToInt32(box.Text));
            m.getPPT();
        }

        public void deducBox_Leave(object sender, EventArgs e)
        {
            TextBox box = (TextBox)Convert.ChangeType(sender, typeof(TextBox));
            m.changeDeduc(Convert.ToDouble(box.Text));
            m.getPPT();
        }

        public void taxBox_Leave(object sender, EventArgs e)
        {
            TextBox box = (TextBox)Convert.ChangeType(sender, typeof(TextBox));
            m.changeTax(Convert.ToDouble(box.Text));
            m.getPPT();
        }

        public void minTaxBox_Leave(object sender, EventArgs e)
        {
            TextBox box = (TextBox)Convert.ChangeType(sender, typeof(TextBox));
            m.changeMinTax(Convert.ToDouble(box.Text));
            m.getPPT();
        }

        public void maxTaxBox_Leave(object sender, EventArgs e)
        {
            TextBox box = (TextBox)Convert.ChangeType(sender, typeof(TextBox));
            m.changeMaxTax(Convert.ToDouble(box.Text));
            m.getPPT();
        }

        public void configTBox_Click(object sender, EventArgs e)
        {
            m.changetaxStatus();
            m.getPPT();
        }

        public void configDBox_Click(object sender, EventArgs e)
        {
            m.changeDeducStatus();
            m.getPPT();
        }

        public void guestSliderA_Scroll(object sender, EventArgs e)
        {
            TrackBar bar = (TrackBar)Convert.ChangeType(sender, typeof(TrackBar));
            if (!m.pptStatus())
                m.tipAdjust();

                m.calculateAdjustedPortion(bar.Value,0);
        }

        public void guestSlider2_Scroll(object sender, EventArgs e)
        {
            TrackBar bar = (TrackBar)Convert.ChangeType(sender, typeof(TrackBar));
            if (!m.pptStatus())
                m.tipAdjust();

                m.calculateAdjustedPortion(bar.Value,1);
        }

        public void guestSlider3_Scroll(object sender, EventArgs e)
        {
            TrackBar bar = (TrackBar)Convert.ChangeType(sender, typeof(TrackBar));
            if (!m.pptStatus())
                m.tipAdjust();

            m.calculateAdjustedPortion(bar.Value, 2);
        }

        public void guestSlider4_Scroll(object sender, EventArgs e)
        {
            TrackBar bar = (TrackBar)Convert.ChangeType(sender, typeof(TrackBar));
            if (!m.pptStatus())
                m.tipAdjust();

            m.calculateAdjustedPortion(bar.Value, 3);
        }

        public void guestSlider5_Scroll(object sender, EventArgs e)
        {
            TrackBar bar = (TrackBar)Convert.ChangeType(sender, typeof(TrackBar));
            if (!m.pptStatus())
                m.tipAdjust();

            m.calculateAdjustedPortion(bar.Value, 4);
        }

        public void guestSlider6_Scroll(object sender, EventArgs e)
        {
            TrackBar bar = (TrackBar)Convert.ChangeType(sender, typeof(TrackBar));
            if (!m.pptStatus())
                m.tipAdjust();

            m.calculateAdjustedPortion(bar.Value, 5);
        }

        public void guestSlider7_Scroll(object sender, EventArgs e)
        {
            TrackBar bar = (TrackBar)Convert.ChangeType(sender, typeof(TrackBar));
            if (!m.pptStatus())
                m.tipAdjust();

            m.calculateAdjustedPortion(bar.Value, 6);
        }

        public void guestSlider8_Scroll(object sender, EventArgs e)
        {
            TrackBar bar = (TrackBar)Convert.ChangeType(sender, typeof(TrackBar));
            if (!m.pptStatus())
                m.tipAdjust();

            m.calculateAdjustedPortion(bar.Value, 7);
        }

        public void guestSlider9_Scroll(object sender, EventArgs e)
        {
            TrackBar bar = (TrackBar)Convert.ChangeType(sender, typeof(TrackBar));
            if (!m.pptStatus())
                m.tipAdjust();

            m.calculateAdjustedPortion(bar.Value, 8);
        }

        public void guestSlider10_Scroll(object sender, EventArgs e)
        {
            TrackBar bar = (TrackBar)Convert.ChangeType(sender, typeof(TrackBar));
            if (!m.pptStatus())
                m.tipAdjust();

            m.calculateAdjustedPortion(bar.Value, 9);
        }
    }
}
