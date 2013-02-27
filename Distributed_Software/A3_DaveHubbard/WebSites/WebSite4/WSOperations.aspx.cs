using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.UI;
using System.Web.UI.WebControls;

public partial class _Default : System.Web.UI.Page
{
    protected void Page_Load(object sender, EventArgs e)
    {

    }
    protected void Button1_Click(object sender, EventArgs e)
    {
        wsProxy.Service1Client wsp = new wsProxy.Service1Client();
        string[] sa = wsp.WsOperations(TextBox1.Text);
        string result = "";
        if (sa.Length > 0)
        {
            foreach (string s in sa)
            {
                result += s + "\n";
            }
        }
        else
            result = "No methods found.";
                TextBox2.Text = result;
    }
}