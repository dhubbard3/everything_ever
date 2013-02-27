using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.UI;
using System.Web.UI.WebControls;

public partial class MtoKM : System.Web.UI.Page
{
    protected void Page_Load(object sender, EventArgs e)
    {

    }
    protected void Button1_Click(object sender, EventArgs e)
    {
        travelProxy.Service1Client tp = new travelProxy.Service1Client();
        double result;
        double d = Convert.ToDouble(TextBox1.Text);
        if (RadioButtonList1.SelectedIndex == 0)
            result = tp.distanceConversion(d, "km");
        else
            result = tp.distanceConversion(d, "m");
        TextBox2.Text = result.ToString();
    }
    
}