using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.UI;
using System.Web.UI.WebControls;

namespace GolfApp
{
    public partial class MyCart : System.Web.UI.Page
    {
        string catalogKey;
        protected void Page_Load(object sender, EventArgs e)
        {
            Double totalAmount = 0;
            for (Int16 i = 1; i <= Session.Count; i++)
            {
                catalogKey = "sItem" + i;
                GolfItem Item = (GolfItem)Session[catalogKey];
                if (Item._InCart)
                {
                    ListBoxCart.Items.Add(Item._Name);
                    totalAmount = totalAmount + Convert.ToDouble(Item._Price);
                }
                else
                    ListBoxCart.Items.Add("");
            }
            lblTotal.Text = Convert.ToString(totalAmount);
        }

        protected void btnRemove_Click(object sender, EventArgs e)
        {
            if (ListBoxCart.SelectedIndex < 0)
                lblMsg.Text = "PLease select an item in the list above";
            else
            {
                string num = Convert.ToString(ListBoxCart.SelectedIndex + 1);
                string catalogKey = "sItem" + num;
                GolfItem Item = (GolfItem)Session[catalogKey];
                Item._InCart = false;
                Session[catalogKey] = Item;
                ListBoxCart.Items.Clear();
                Double totalAmount = 0;
                for (Int16 i = 1; i <= Session.Count; i++)
                {
                    catalogKey = "sItem" + i;
                    Item = (GolfItem)Session[catalogKey];
                    if (Item._InCart)
                    {
                        ListBoxCart.Items.Add(Item._Name);
                        totalAmount = totalAmount + Convert.ToDouble(Item._Price);
                    }
                    else
                        ListBoxCart.Items.Add("");
                }
                lblTotal.Text = Convert.ToString(totalAmount);
            }
        }

        protected void btnShopping_Click(object sender, EventArgs e)
        {
            Response.Redirect("OnlineShop.aspx");
        }
    }
}