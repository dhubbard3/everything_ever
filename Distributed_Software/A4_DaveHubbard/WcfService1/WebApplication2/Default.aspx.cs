using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.UI;
using System.Web.UI.WebControls;

namespace WebApplication2
{
    public partial class _Default : System.Web.UI.Page
    {
        protected void Page_Load(object sender, EventArgs e)
        {

        }

        protected void Button1_Click(object sender, EventArgs e)
        {
            xmlProxy.Service1Client xp = new xmlProxy.Service1Client();
            TextBox4.Text = xp.ValidateXML(TextBox1.Text, TextBox2.Text);
        }

        protected void Button2_Click(object sender, EventArgs e)
        {
            xmlProxy.Service1Client xp = new xmlProxy.Service1Client();
            TextBox4.Text = xp.XMLTransform(TextBox1.Text, TextBox3.Text);
        }
    }
}
