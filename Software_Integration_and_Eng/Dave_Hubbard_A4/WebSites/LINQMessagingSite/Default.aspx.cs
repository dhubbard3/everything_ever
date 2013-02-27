using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.UI;
using System.Web.UI.WebControls;
using System.Web.UI.WebControls.WebParts;
using System.Web.UI.HtmlControls;
using System.Web.Security;

public partial class _Default : System.Web.UI.Page
{
    protected void Page_Load(object sender, EventArgs e)
    {

    }
    protected void Button1_Click(object sender, EventArgs e)
    {
        string messages = "Click!";
        TextBox2.Text = messages;
        List<string> s = new List<string>();
        s.Add(TextBox1.Text + "s Inbox:\n");
        TextBox2.Text = "List Created";
        messageClassesDataContext message = new messageClassesDataContext();
        IEnumerable<messageServer> messageQuery =
            from m in message.messageServers
            where m.receiverID == TextBox1.Text
            orderby m.timestamp
            select m;
        TextBox2.Text = "Query Finished";
        foreach (messageServer m in messageQuery)
        {
            s.Add("From: " + m.senderID + " at: " + m.timestamp + ":\n " + m.msg + "\n\n");
        }
        string[] st = s.ToArray();
        //messageProxy.MessageClient mp = new messageProxy.MessageClient();
       // string[] s = mp.receiveMsg(TextBox1.Text);
        foreach (string i in st)
        {
            messages += i;
        }
        TextBox2.Text = st[0];
    }

    protected void Button2_Click(object sender, EventArgs e)
    {
        messageProxy.MessageClient mp = new messageProxy.MessageClient();
        mp.sendMsg(TextBox1.Text, TextBox3.Text, TextBox4.Text);
        TextBox4.Text = ("Message Sent to " + TextBox3.Text + ".");
    }
    protected void Button1_Click1(object sender, EventArgs e)
    {
        string messages = "";
        messageProxy.MessageClient mp = new messageProxy.MessageClient();
        string[] s = mp.receiveMsg(TextBox1.Text);
        foreach (string i in s)
        {
            messages += i;
        }
        TextBox2.Text = messages;
    }
}