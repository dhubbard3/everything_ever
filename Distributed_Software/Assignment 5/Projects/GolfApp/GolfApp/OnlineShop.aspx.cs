using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.UI;
using System.Web.UI.WebControls;

namespace GolfApp
{
    public partial class OnlineShop : System.Web.UI.Page
    {
        GolfItem Item1, Item2;
        string catalogKey;
        protected void Page_Load(object sender, EventArgs e)
        {
            if ((Session.Count == 0) && (ListBoxCatalog.Items.Count) == 0)
            {
                ListBoxCatalog.Items.Clear();
                string name = "Odyssey Metal X Blade Putter";
                string modelnum = "OD-19405R";
                double price = 149.99;
                Item1 = new GolfItem(name, modelnum, price);
                Session["sItem1"] = Item1;
                ListBoxCatalog.Items.Add(Item1._Name);
                name = "FootJoy Men's Contour Casuals Spikeless Golf Shoes";
                modelnum = "FJ#54275";
                price = 114.99;
                Item2 = new GolfItem(name, modelnum, price);
                Session["sItem2"] = Item2;
                ListBoxCatalog.Items.Add(Item2._Name);
            }
            if ((Session.Count != 0) && (ListBoxCatalog.Items.Count == 0))
            {
                for (Int16 i = 1; i <= Session.Count; i++)
                {
                    catalogKey = "sItem" + i;
                    Item1 = (GolfItem)Session[catalogKey];
                    ListBoxCatalog.Items.Add(Item1._Name);
                }
            }
        }

        protected void btnSeller_Click(object sender, EventArgs e)
        {
            Response.Redirect("Seller.aspx");
        }

        protected void btnViewItem_Click(object sender, EventArgs e)
        {
            if (ListBoxCatalog.SelectedIndex < 0)
                lblName.Text = "Please select an item in the list above";
            else
            {
                string num = Convert.ToString(ListBoxCatalog.SelectedIndex + 1);
                catalogKey = "sItem" + num;
                GolfItem Item = (GolfItem)Session[catalogKey];
                lblName.Text = "<br />Name: " + Item._Name + "<br />Model Number: " +
                    Item._ModelNum + "<br />Price: " + Item._Price;
            }
        }

        protected void btnAddToCar_Click(object sender, EventArgs e)
        {
            if (ListBoxCatalog.SelectedIndex < 0)
                lblName.Text = "Please select an item in the list above";
            else
            {
                string num = Convert.ToString(ListBoxCatalog.SelectedIndex + 1);
                string catalogKey = "sItem" + num;
                Item1 = (GolfItem)Session[catalogKey];
                Item1._InCart = true;
                Session[catalogKey] = Item1;
                Response.Redirect("MyCart.aspx");
            }
        }
    }
}