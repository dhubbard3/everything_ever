using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.Security;
using System.Web.UI;
using System.Web.UI.WebControls;
using System.Xml;
using System.IO;

namespace GolfApp
{
    public partial class SiteLogin : System.Web.UI.Page
    {
        protected void Page_Load(object sender, EventArgs e)
        {

        }

        protected void LoginUsers(object sender, EventArgs e)
        {
            
            if (FormsAuthentication.Authenticate(LoginUser.UserName, LoginUser.Password))
                FormsAuthentication.RedirectFromLoginPage(LoginUser.UserName, false);
            else if(findUser(LoginUser.UserName, LoginUser.Password))
                FormsAuthentication.RedirectFromLoginPage(LoginUser.UserName, false);
            else
                LoginUser.FailureText = "Invalid login";
        }

        protected void LinkButton1_Click(object sender, EventArgs e)
        {
            Response.Redirect("SiteRegister.aspx");
        }

        protected bool findUser(string username, string password)
        {
            string fLocation = Path.Combine(Request.PhysicalApplicationPath, @"App_Data\Users.xml");
            if (File.Exists(fLocation))
            {
                FileStream FS = new FileStream(fLocation, FileMode.Open);
                XmlDocument xd = new XmlDocument();
                xd.Load(FS);
                XmlNode node = xd;
                XmlNodeList children = node.ChildNodes;
                foreach (XmlNode child in children)
                {
                    if (child.Name == "Users")
                    {
                        XmlNodeList att = child.ChildNodes;
                        foreach (XmlNode a in att)
                        {
                            if (a.Attributes["name"].Value == username)
                            {
                                if (a.Attributes["password"].Value == password)
                                    return true;
                            }
                          
                        }
                    }
                }
            }
            return false;
        }

    }
}