<%@ Page Language="C#" AutoEventWireup="true" CodeFile="Default.aspx.cs" Inherits="_Default" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
<head runat="server">
    <title></title>
</head>
<body>
    <form id="form1" runat="server">
    <div>
    
        <br />
        Login as:
        <asp:TextBox ID="TextBox1" runat="server" Width="408px"></asp:TextBox>
&nbsp;&nbsp;
        <asp:Button ID="Button1" runat="server" Text="Retrieve Messages" 
            onclick="Button1_Click1" />
        <br />
        <br />
        <asp:TextBox ID="TextBox2" runat="server" Height="226px" TextMode="MultiLine" 
            Width="920px"></asp:TextBox>
        <br />
        <br />
        To:
        <asp:TextBox ID="TextBox3" runat="server" Width="436px"></asp:TextBox>
&nbsp;&nbsp;&nbsp;
        <asp:Button ID="Button2" runat="server" onclick="Button2_Click" 
            Text="Send Message" />
        <br />
        <asp:TextBox ID="TextBox4" runat="server" Height="186px" TextMode="MultiLine" 
            Width="920px">Type Message Here</asp:TextBox>
        <br />
    
    </div>
    </form>
</body>
</html>
