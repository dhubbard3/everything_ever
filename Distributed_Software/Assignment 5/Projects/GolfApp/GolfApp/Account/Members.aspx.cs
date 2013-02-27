using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.UI;
using System.Web.UI.WebControls;
using System.IO;
using System.Xml;

namespace GolfApp.Account
{
    public partial class Members : System.Web.UI.Page
    {
        protected void Page_Load(object sender, EventArgs e)
        {

        }

        protected void Button1_Click(object sender, EventArgs e)
        {
            string course, score, name;
            course = TextBox1.Text;
            score = TextBox2.Text;
            name = Context.User.Identity.Name;

            string fLocation = Path.Combine(Request.PhysicalApplicationPath, @"App_Data\Scores.xml");
            if (File.Exists(fLocation))
            {
                XmlDocument xd = new XmlDocument();
                xd.Load(fLocation);
                XmlNode newNode = xd.SelectSingleNode("Users");
                XmlNode xNode = xd.CreateNode(XmlNodeType.Element, "user", "");
                XmlAttribute xName = xd.CreateAttribute("name");
                XmlAttribute xCourse = xd.CreateAttribute("course");
                XmlAttribute xScore = xd.CreateAttribute("score");
                xName.Value = name;
                xCourse.Value = course;
                xScore.Value = score;
                xNode.Attributes.Append(xName);
                xNode.Attributes.Append(xCourse);
                xNode.Attributes.Append(xScore);
                newNode.AppendChild(xNode);
                xd.Save(fLocation);
            }
        }
    }
}