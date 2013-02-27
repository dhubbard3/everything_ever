using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.UI;
using System.Web.UI.WebControls;

namespace GolfApp {
    public partial class UserControl3 : System.Web.UI.UserControl {
        protected void Page_Load(object sender, EventArgs e) {
        }
        
        string m_ImageName = "";
        int m_ImageWidth = 0;

        /*
        protected void Button1_Click(object sender, EventArgs e) {
            if (ImagePath == string.Empty)
                return;
            else
                Response.Redirect(ImagePath);
        }*/

        public string ImagePath {
            get {
                return Image1.ImageUrl;
            }
            set {
                Image1.ImageUrl = value;
            }
        }

        public string ImageName {
            get {
                return m_ImageName;
            }
            set {
                m_ImageName = value;
            }
        }

        public int ImageHeight {
            set {
                Image1.Height = value;
            }
        }

        public int ImageWidth {
            get {
                return m_ImageWidth;
            }
            set {
                Image1.Width = value;
            }
        }
    }

}