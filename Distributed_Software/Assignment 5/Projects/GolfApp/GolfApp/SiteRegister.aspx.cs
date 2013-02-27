using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.UI;
using System.Web.UI.WebControls;
using System.IO;
using System.Xml;

namespace GolfApp
{
    public partial class SiteRegister : System.Web.UI.Page
    {
        protected void Page_Load(object sender, EventArgs e)
        {

        }

        protected void CreateUser(object sender, EventArgs e)
        {
            string name, password, passCheck;
            name = TextBox1.Text;
            password = TextBox2.Text;
            passCheck = TextBox3.Text;

            if (password == passCheck)
            {
                string fLocation = Path.Combine(Request.PhysicalApplicationPath, @"App_Data\Users.xml");
                if (File.Exists(fLocation))
                {
                    XmlDocument xd = new XmlDocument();
                    xd.Load(fLocation);
                    XmlNode newNode = xd.SelectSingleNode("Users");
                    XmlNode xNode = xd.CreateNode(XmlNodeType.Element, "user", "");
                    XmlAttribute xName = xd.CreateAttribute("name");
                    XmlAttribute xPass = xd.CreateAttribute("password");
                    xName.Value = name;
                    xPass.Value = password;
                    xNode.Attributes.Append(xName);
                    xNode.Attributes.Append(xPass);
                    newNode.AppendChild(xNode);
                    xd.Save(fLocation);

                }
            }
            else
            {
                Label1.Text = "Password does not match. Try Again.";
                Label1.Visible = true;
                TextBox2.Text = "";
                TextBox3.Text = "";
            }
        }//end create user
    }


}