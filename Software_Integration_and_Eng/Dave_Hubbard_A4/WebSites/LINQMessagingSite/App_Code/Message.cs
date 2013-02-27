using System;
using System.Collections.Generic;
using System.Linq;
using System.Runtime.Serialization;
using System.ServiceModel;
using System.Text;
using System.Web;
using System.Web.UI;
using System.Web.UI.WebControls;
using System.Web.UI.WebControls.WebParts;
using System.Web.UI.HtmlControls;
using System.Web.Security;

// NOTE: You can use the "Rename" command on the "Refactor" menu to change the class name "Message" in code, svc and config file together.
public class Message : IMessage
{
	public void sendMsg(string senderID, string receiveID, string msg)
	{
        string stamp = DateTime.Now.ToString();
        messageClassesDataContext message = new messageClassesDataContext();
        messageServer ms = new messageServer();
        ms.senderID = senderID;
        ms.receiverID = receiveID;
        ms.msg = msg;
        ms.timestamp = stamp;
        message.messageServers.InsertOnSubmit(ms);
        message.SubmitChanges();
	}

    public string[] receiveMsg(string receiveID)
    {
        List<string> s = new List<string>();
        s.Add(receiveID + "'s Inbox:\n\n");
        messageClassesDataContext message = new messageClassesDataContext();
        IEnumerable<messageServer> messageQuery =
            from m in message.messageServers
            where m.receiverID == receiveID
            select m;
        foreach (messageServer m in messageQuery)
        {
            s.Add("From: " + m.senderID + "  at: " + m.timestamp + "\n " + m.msg + "\n\n");
        }
        string[] st = s.ToArray();
        return st;
    }
}
