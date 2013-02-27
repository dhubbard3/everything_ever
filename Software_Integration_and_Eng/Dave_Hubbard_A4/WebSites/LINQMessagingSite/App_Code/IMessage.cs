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

// NOTE: You can use the "Rename" command on the "Refactor" menu to change the interface name "IMessage" in both code and config file together.
[ServiceContract]
public interface IMessage
{
	[OperationContract]
	void sendMsg(string senderID, string receiveID, string msg);

    [OperationContract]
    string[] receiveMsg(string receiveID);
}
