using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.UI;
using System.Web.UI.WebControls;

namespace GolfApp
{
    public partial class SiteMaster : System.Web.UI.MasterPage
    {
        protected void Page_Load(object sender, EventArgs e)
        {
            UserControl3.ImagePath = "Images/ball-banner.png";
            UserControl3.ImageName = "Masters";
            UserControl3.ImageHeight = 200;
            UserControl3.ImageWidth = 730;

            UserControl3_2.ImagePath = "Images/The_Masters_Logo.jpg";
            UserControl3_2.ImageName = "Masters";
            UserControl3_2.ImageHeight = 100;
            UserControl3_2.ImageWidth = 100;
        }
    }
}
