using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.UI;
using System.Web.UI.WebControls;

public partial class ZipInfo : System.Web.UI.Page
{
    protected void Page_Load(object sender, EventArgs e)
    {

    }
    protected void Button1_Click(object sender, EventArgs e)
    {
        travelProxy.Service1Client tp = new travelProxy.Service1Client();
        string result = tp.basicZipInfo(TextBox1.Text);
        TextBox2.Text = result;
    }
}