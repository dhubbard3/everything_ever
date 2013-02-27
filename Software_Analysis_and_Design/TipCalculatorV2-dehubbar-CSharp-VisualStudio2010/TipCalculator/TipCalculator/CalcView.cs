using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Windows.Forms;

namespace TipCalculator
{
    public partial class CalcView : Form
    {

        CalcModel c;
        CalcController ctrl;
        Random r = new Random();
        int screen = 0;
        
        public CalcView()
        {
            InitializeComponent();
            tipCalcBtn.BringToFront();
            tailorBtn.BringToFront();
            configBtn.BringToFront();
            c = new CalcModel();
            ctrl = new CalcController(c);
            initializeHandlers();
            percentLbl.Text = "15.0%";
            taxLbl.ForeColor = Color.Gray;
            tipSlider.Value = 150;
            tailorTipValue.Text = "15.0%";
            serviceAdjustLbl.Visible = false;
        }

        private void initializeHandlers()
        {
            trackBar1.Scroll += new System.EventHandler(ctrl.trackBar1_Scroll);
            tipSlider.Scroll += new System.EventHandler(ctrl.tipSlider_Scroll);
            trackBarA.Scroll += new System.EventHandler(ctrl.guestSliderA_Scroll);
            trackBar2.Scroll += new System.EventHandler(ctrl.guestSlider2_Scroll);
            trackBar3.Scroll += new System.EventHandler(ctrl.guestSlider3_Scroll);
            trackBar4.Scroll += new System.EventHandler(ctrl.guestSlider4_Scroll);
            trackBar5.Scroll += new System.EventHandler(ctrl.guestSlider5_Scroll);
            trackBar6.Scroll += new System.EventHandler(ctrl.guestSlider6_Scroll);
            trackBar7.Scroll += new System.EventHandler(ctrl.guestSlider7_Scroll);
            trackBar8.Scroll += new System.EventHandler(ctrl.guestSlider8_Scroll);
            trackBar9.Scroll += new System.EventHandler(ctrl.guestSlider9_Scroll);
            trackBar10.Scroll += new System.EventHandler(ctrl.guestSlider10_Scroll);
            totalBillBox.Leave += new System.EventHandler(ctrl.totalBillBox_Leave);
            guestNoBox.Leave += new System.EventHandler(ctrl.guestNoBox_Leave);
            deducBox.Leave += new System.EventHandler(ctrl.deducBox_Leave);
            taxBox.Leave += new System.EventHandler(ctrl.taxBox_Leave);
            minTaxBox.Leave += new System.EventHandler(ctrl.minTaxBox_Leave);
            maxTaxBox.Leave += new System.EventHandler(ctrl.maxTaxBox_Leave);
            configTBox.Click += new System.EventHandler(ctrl.configTBox_Click);
            configDBox.Click += new System.EventHandler(ctrl.configDBox_Click);
            tipCalcBtn.Click += new EventHandler(ctrl.tipCalcBtn_Click);
            tailorBtn.Click += new EventHandler(ctrl.tailorBtn_Click);
            configBtn.Click += new EventHandler(ctrl.configBtn_Click);
            errorButton.Click += new EventHandler(errorButton_Click);
        }

        private void viewScreen(int n)
        {
            if (n == 0)
            {
                tailoringGroup.Visible = false;
                configGroup.Visible = false;
                TipCalc.Visible = true;
            }
            else if (n == 1)
            {
                tailoringGroup.Visible = true;
                configGroup.Visible = false;
                TipCalc.Visible = false;
            }
            else
            {
                configGroup.Visible = true;
                tailoringGroup.Visible = false;
                TipCalc.Visible = false;
            }
        }

        public void update()
        {
            screen = c.getView();
            viewScreen(screen);
            totalLbl.Text = c.calculateGrandTotal().ToString("C");
            
            // Tip Calculation Screen //
            if (screen == 0)
            {
                guestNoBox.Text = c.getGuestNum().ToString();
                totalBillBox.Text = c.getBill().ToString("F2");
                deducBox.Text = c.getDeduc().ToString("F2");
                taxBox.Text = c.getTax().ToString("F2");
                percentLbl.Text = c.getTipRate().ToString("F1")+"%";
                tipTotalResultLbl.Text = c.calcualteTotalTip().ToString("C");
                perPerLbl.Text = c.PPTStatus().ToString("C");
                starLbl.Text = c.getRating().ToString();
                if (c.pptStatus())
                {
                    serviceAdjustLbl.Visible = true;
                    perPerLbl.Text = "Adjusted";
                    perPerLbl.ForeColor = Color.Red;
                }
                else
                {
                    serviceAdjustLbl.Visible = false;
                    perPerLbl.ForeColor = Color.Teal;
                }
                if (configDBox.Checked)
                    deducLbl.ForeColor = Color.Teal;
                else
                    deducLbl.ForeColor = Color.Gray;
                if (configTBox.Checked)
                    taxLbl.ForeColor = Color.Teal;
                else
                    taxLbl.ForeColor = Color.Gray;
            }

            // Tailoring Screen //
            if (screen == 1)
            {
                double ppt = c.PPTStatus();
                populateGuestFields(c.getGuestNum());
                if (c.pptStatus())
                {
                    tipSlider.Value = (int)(c.findArate() * 1000);
                }
                else
                {
                    setGuestSliders(ppt);
                    tipSlider.Value = (int)(c.getTipRate());
                }
                guest1Lbl.Text = c.getGuestPPT(0).ToString("C");
                guest2Lbl.Text = c.getGuestPPT(1).ToString("C");
                guest3Lbl.Text = c.getGuestPPT(2).ToString("C");
                guest4Lbl.Text = c.getGuestPPT(3).ToString("C");
                guest5Lbl.Text = c.getGuestPPT(4).ToString("C");
                guest6Lbl.Text = c.getGuestPPT(5).ToString("C");
                guest7Lbl.Text = c.getGuestPPT(6).ToString("C");
                guest8Lbl.Text = c.getGuestPPT(7).ToString("C");
                guest9Lbl.Text = c.getGuestPPT(8).ToString("C");
                guest10Lbl.Text = c.getGuestPPT(9).ToString("C");
                tailorTipValue.Text = c.calcualteTotalTip().ToString("C");
                tailorTipLbl.Text = (c.findArate() * 100).ToString("F1") + "%";
            }

            // Config Screen //
            if (screen == 2)
            {
                minTaxBox.Text = c.getMinTax().ToString("F1");
                maxTaxBox.Text = c.getMaxTax().ToString("F1");
                percentLbl.Text = c.getTipRate().ToString("F1") + "%";
                tipTotalResultLbl.Text = c.calcualteTotalTip().ToString("C");
                perPerLbl.Text = c.PPTStatus().ToString("C");
            }
        }

        public void showError(int n)
        {
            int header = r.Next(1, 4);
            errorBox.Visible = true;
            errorBox.BringToFront();
            switch (header)
            {
                case 1: errorBox.Text = "Error";
                    break;
                case 2: errorBox.Text = "Uh Oh...";
                    break;
                case 3: errorBox.Text = "Slow down there, turbo...";
                    break;
                case 4: errorBox.Text = "On second thought...";
                    break;
            }
            switch (n)
            {
                case 1: errorLbl.Text = "Minimum tax % exceeds maximum tax %. Restoring to defaults.";
                    minTaxBox.Text = "5.0";
                    maxTaxBox.Text = "25.0";
                    break;
                case 2: errorLbl.Text = "Maximum tax % is lower than minimum tax %. Restoring to defaults.";
                    minTaxBox.Text = "5.0";
                    maxTaxBox.Text = "25.0";
                    break;
                case 3: errorLbl.Text = "Number of guests must range from 1 to 10. Restoring to default.";
                    guestNoBox.Text = "1";
                    break;
                case 4: errorLbl.Text = "Deduction amount cannot exceed Bill Total. Deduction must be positve.";
                    deducBox.Text = "0.0";
                    break;
                case 5: errorLbl.Text = "Tax must be positve.";
                    taxBox.Text = "0.0";
                    break;
                case 6: errorLbl.Text = "Bill must be positve.";
                    totalBillBox.Text = "0.0";
                    break;
            }
        }

        private void populateGuestFields(int n)
        {
            int rows = 10;
            if (n > 7)
                tableLayoutPanel1.AutoScroll = true;
            else
                tableLayoutPanel1.AutoScroll = false;
            for (int i = 0; i < rows; i++)
            {
                if (i < n)
                {
                    tableLayoutPanel1.GetControlFromPosition(0, i).Visible = true;
                }
                else
                {
                    tableLayoutPanel1.GetControlFromPosition(0, i).Visible = false;
                }
            }
        }

        private void setGuestSliders(double ppt)
        {
            int n = c.getGuestNum();
            for (int i = 0; i < n; i++)
            {
                switch (i)
                {
                    case 0: trackBarA.Value = (1000 / n);
                        guest1Lbl.Text = ppt.ToString("C");
                        break;
                    case 1: trackBar2.Value = (1000 / n);
                        guest2Lbl.Text = ppt.ToString("C");
                        break;
                    case 2: trackBar3.Value = (1000 / n);
                        guest3Lbl.Text = ppt.ToString("C");
                        break;
                    case 3: trackBar4.Value = (1000 / n);
                        guest4Lbl.Text = ppt.ToString("C");
                        break;
                    case 4: trackBar5.Value = (1000 / n);
                        guest5Lbl.Text = ppt.ToString("C");
                        break;
                    case 5: trackBar6.Value = (1000 / n);
                        guest6Lbl.Text = ppt.ToString("C");
                        break;
                    case 6: trackBar7.Value = (1000 / n);
                        guest7Lbl.Text = ppt.ToString("C");
                        break;
                    case 7: trackBar8.Value = (1000 / n);
                        guest8Lbl.Text = ppt.ToString("C");
                        break;
                    case 8: trackBar9.Value = (1000 / n);
                        guest9Lbl.Text = ppt.ToString("C");
                        break;
                    case 9: trackBar10.Value = (1000 / n);
                        guest10Lbl.Text = ppt.ToString("C");
                        break;
                }
            }
        }
        

        private void errorButton_Click(object sender, EventArgs e)
        {
            errorLbl.Text = "Error";
            errorBox.Visible = false;
        }

    }
}
