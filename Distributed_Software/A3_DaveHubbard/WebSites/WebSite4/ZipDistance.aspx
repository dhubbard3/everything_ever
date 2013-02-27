<%@ Page Language="C#" AutoEventWireup="true" CodeFile="ZipDistance.aspx.cs" Inherits="ZipDistance" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
<head runat="server">
    <title></title>
</head>
<body>
    <form id="form1" runat="server">
    <div>
    
        Enter Zip 1:
        <asp:TextBox ID="TextBox1" runat="server"></asp:TextBox>
&nbsp;<br />
        Enter Zip 2:
        <asp:TextBox ID="TextBox2" runat="server"></asp:TextBox>
        <br />
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
        <asp:Button ID="Button1" runat="server" onclick="Button1_Click" 
            Text="Calculate" />
        <br />
        <br />
        <asp:TextBox ID="TextBox3" runat="server" Width="426px"></asp:TextBox>
    
    </div>
    </form>
</body>
</html>
