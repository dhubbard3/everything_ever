<%@ Page Language="C#" AutoEventWireup="true" CodeFile="WSOperations.aspx.cs" Inherits="_Default" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
<head runat="server">
    <title></title>
</head>
<body>
    <form id="form1" runat="server">
    <div>
    
        Enter WSDL URL Address:&nbsp;
        <br />
        http://<asp:TextBox ID="TextBox1" runat="server" Width="370px"></asp:TextBox>
        <asp:Button ID="Button1" runat="server" onclick="Button1_Click" Text="Search" />
        <br />
    
    </div>
    <asp:TextBox ID="TextBox2" runat="server" Height="348px" TextMode="MultiLine" 
        Width="568px"></asp:TextBox>
    </form>
</body>
</html>
