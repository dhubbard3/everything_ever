<%@ Control Language="C#" AutoEventWireup="true" CodeBehind="UserControl4.ascx.cs"
    Inherits="GolfApp.UserControl4" %>
<!-- Drop down list with the gold touraments -->
<h3>List of Golf Tournaments</h3>
<asp:DropDownList ID="golfTourn" runat="server">
    <asp:ListItem>-- List of Golf Tournaments --</asp:ListItem>
    <asp:ListItem>The Masters</asp:ListItem>
    <asp:ListItem>United States Open Championship</asp:ListItem>
    <asp:ListItem>The British Open</asp:ListItem>
    <asp:ListItem>PGA Championship</asp:ListItem>
</asp:DropDownList>
