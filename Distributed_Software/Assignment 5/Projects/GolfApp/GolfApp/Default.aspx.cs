using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.UI;
using System.Web.UI.WebControls;


//DLLs
using ClassLibrary1;
using ClassLibrary2;
using ClassLibrary3;
using ClassLibrary4;

namespace GolfApp
{
    public partial class _Default : System.Web.UI.Page
    {
        protected void Page_Load(object sender, EventArgs e)
        {

        }

        protected void RadioButton1_CheckedChanged(object sender, EventArgs e) {

        }

        protected void Button1_Click(object sender, EventArgs e) {
            int score = 0;
            int par = 0;

            try {
                score = Convert.ToInt32(TextBox1.Text.ToString());
                par = Convert.ToInt32(TextBox2.Text.ToString());
                    Label1.Text = ScoreTerms.scoreToTerms(score, par);
            }
            catch (FormatException ex) {
                Label1.Text = "Input must be a number.";
            }
            catch (OverflowException ex) {
                Label1.Text = "Number too large.";

            }
        }

        protected void Button2_Click(object sender, EventArgs e) {
            int yards = 0;

            try {
                yards = Convert.ToInt32(TextBox3.Text.ToString());
                Label2.Text = ParCalculate.parCalculator(yards).ToString();
            }
            catch (FormatException ex) {
                Label2.Text = "Input must be a number.";
            }
            catch (OverflowException ex) {
                Label2.Text = "Number too large.";
            }
        }

        protected void Button3_Click(object sender, EventArgs e) {
            int yards = 0;

            try {
                yards = Convert.ToInt32(TextBox4.Text.ToString());
                Label3.Text = Conversion.yardsToMeters(yards).ToString();
            }
            catch (FormatException ex) {
                Label3.Text = "Input must be a number.";
            }
            catch (OverflowException ex) {
                Label3.Text = "Number too large.";
            }
        }

        protected void Button4_Click(object sender, EventArgs e) {
            int meters = 0;

            try {
                meters = Convert.ToInt32(TextBox5.Text.ToString());
                Label4.Text = Conversion.metersToYards(meters).ToString();
            }
            catch (FormatException ex) {
                Label4.Text = "Input must be a number.";
            }
            catch (OverflowException ex) {
                Label4.Text = "Number too large.";
            }
        }

        protected void Button5_Click(object sender, EventArgs e) {
            int yards = 0;

            try {
                yards = Convert.ToInt32(TextBox6.Text.ToString());
                if (RadioButton1.Checked) {
                    Label5.Text = ClubID.clubIdentifier(true, yards);
                }
                else if (RadioButton2.Checked) {
                    Label5.Text = ClubID.clubIdentifier(false, yards);
                }
                else {
                    Label5.Text = "Logic Error";
                }
            }
            catch (FormatException ex) {
                Label5.Text = "Input must be a number.";
            }
            catch (OverflowException ex) {
                Label5.Text = "Number too large.";
            }
        }
    }
}


public class GolfItem
{
    public string _Name;
    public string _ModelNum;
    public double _Price;
    public bool _InCart;
    public GolfItem(string name, string modelnum, double price)
    {
        _Name = name;
        _ModelNum = modelnum;
        _Price = price;
        _InCart = false;
    }
}
