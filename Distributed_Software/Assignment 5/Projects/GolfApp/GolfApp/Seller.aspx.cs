using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.UI;
using System.Web.UI.WebControls;

namespace GolfApp
{
    public partial class Seller : System.Web.UI.Page
    {
        protected void Page_Load(object sender, EventArgs e)
        {

        }

        protected void btnSubmit_Click(object sender, EventArgs e)
        {
            string name = txtName.Text;
            string modelnum = txtModelNum.Text;
            string sPrice = txtPrice.Text;
            double price = Convert.ToDouble(sPrice);
            GolfItem Item1 = new GolfItem(name, modelnum, price);
            string num = Convert.ToString(Session.Count + 1);
            string catalogKey = "sItem" + num;
            Session[catalogKey] = Item1;
            Response.Redirect("OnlineShop.aspx");
        }
    }
}