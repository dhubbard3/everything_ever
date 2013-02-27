using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.UI;
using System.Web.UI.WebControls;
using System.IO;
using System.Xml;

namespace GolfApp.Protected
{
    public partial class Admin : System.Web.UI.Page
    {
        protected void Page_Load(object sender, EventArgs e)
        {

        }

        protected bool deleteUserAccount(string username, string password)
        {
            string fLocation = Path.Combine(Request.PhysicalApplicationPath, @"App_Data\Users.xml");
            if (File.Exists(fLocation))
            {
                FileStream FS = new FileStream(fLocation, FileMode.Open);
                XmlDocument xd = new XmlDocument();
                xd.Load(FS);
                FS.Close();
                XmlNode node = xd;
                XmlNodeList children = node.ChildNodes;
                foreach (XmlNode child in children)
                {
                    if (child.Name == "Users")
                    {
                        XmlNode parent = child;
                        XmlNodeList att = child.ChildNodes;
                        foreach (XmlNode a in att)
                        {
                            if (a.Attributes["name"].Value == username)
                            {
                                if (a.Attributes["password"].Value == password)
                                {
                                    parent.ParentNode.RemoveChild(a);
                                    xd.Save(fLocation);
                                    return true;
                                }
                            }

                        }
                    }
                }
            }
            return false;
        }

        protected bool deleteUserScores(string username, string course, string score)
        {
            string fLocation = Path.Combine(Request.PhysicalApplicationPath, @"App_Data\Scores.xml");
            if (File.Exists(fLocation))
            {
                FileStream FS = new FileStream(fLocation, FileMode.Open);
                XmlDocument xd = new XmlDocument();
                xd.Load(FS);
                FS.Close();
                XmlNode node = xd;
                XmlNodeList children = node.ChildNodes;
                foreach (XmlNode child in children)
                {
                    if (child.Name == "Users")
                    {
                        XmlNode parent = child;
                        XmlNodeList att = child.ChildNodes;
                        foreach (XmlNode a in att)
                        {
                            if (a.Attributes["name"].Value == username)
                            {
                                if (a.Attributes["course"].Value == course)
                                {
                                    if (a.Attributes["score"].Value == score)
                                    {
                                        parent.RemoveChild(a);
                                        xd.Save(fLocation);
                                        return true;
                                    }
                                }
                            }

                        }
                    }
                }
            }
            return false;
        }

        protected void Button1_Click(object sender, EventArgs e)
        {
            string name, password;
            name = TextBox1.Text;
            password = TextBox2.Text;
            
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

        protected void Button2_Click(object sender, EventArgs e)
        {
            if (deleteUserAccount(TextBox1.Text, TextBox2.Text))
            {
                Label1.Text = "User Removed.";
            }
            else
                Label1.Text = "User Not Found.";
            Label1.Visible = true;
        }

        protected void Button3_Click(object sender, EventArgs e)
        {
               string course, score, name;
            course = TextBox4.Text;
            score = TextBox5.Text;
            name = TextBox3.Text;

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

        protected void Button4_Click(object sender, EventArgs e)
        {
            if (deleteUserScores(TextBox3.Text, TextBox4.Text, TextBox5.Text))
            {
                Label1.Text = "User Score Removed.";
            }
            else
                Label1.Text = "User Score Not Found.";
            Label1.Visible = true;
        }
    }
}