using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.UI;
using System.Web.UI.WebControls;

public partial class ZipDistance : System.Web.UI.Page
{
    protected void Page_Load(object sender, EventArgs e)
    {

    }
    protected void Button1_Click(object sender, EventArgs e)
    {
        travelProxy.Service1Client tp = new travelProxy.Service1Client();
        double result = tp.ZipCodeDistance(TextBox1.Text, TextBox2.Text);
        TextBox3.Text = "The distance between " + TextBox1.Text + " and " + TextBox2.Text + " is " + result.ToString() + " miles.";
    }
}