<%@ Page Language="C#" AutoEventWireup="true" CodeFile="ZipInfo.aspx.cs" Inherits="ZipInfo" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
<head runat="server">
    <title></title>
</head>
<body>
    <form id="form1" runat="server">
    <div>
    
        Enter Zip:
        <asp:TextBox ID="TextBox1" runat="server" Width="128px"></asp:TextBox>
&nbsp;<asp:Button ID="Button1" runat="server" onclick="Button1_Click" Text="Search" />
        <br />
        <br />
        <asp:TextBox ID="TextBox2" runat="server" Height="62px" Width="792px"></asp:TextBox>
    
    </div>
    </form>
</body>
</html>
