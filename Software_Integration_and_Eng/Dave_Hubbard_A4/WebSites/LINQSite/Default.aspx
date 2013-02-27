<%@ Page Language="C#" AutoEventWireup="true" CodeFile="Default.aspx.cs" Inherits="_Default" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
<head runat="server">
    <title></title>
</head>
<body>
    <form id="form1" runat="server">
    <div>
    This webpage shows the results from three different LINQ queries. 
        <asp:Button ID="Button1" runat="server" Text="Run Program" 
            onclick="Button1_Click" />
    </div>
    <asp:TextBox ID="TextBox1" runat="server" Height="410px" Width="880px" 
        TextMode="MultiLine"></asp:TextBox>
    </form>
</body>
</html>
