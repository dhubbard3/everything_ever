<%@ Page Title="" Language="C#" MasterPageFile="~/Site.Master" AutoEventWireup="true" CodeBehind="MyCart.aspx.cs" Inherits="GolfApp.MyCart" %>
<asp:Content ID="Content1" ContentPlaceHolderID="HeadContent" runat="server">
</asp:Content>
<asp:Content ID="Content2" ContentPlaceHolderID="MainContent" runat="server">
    Your Shopping Cart<br />
    <asp:ListBox ID="ListBoxCart" runat="server" Width="350px"></asp:ListBox>
    <br />
    <asp:Label ID="lblMsg" runat="server"></asp:Label>
    <br />
    Total Amount:
    <asp:Label ID="lblTotal" runat="server"></asp:Label>
    <br />
    <asp:Button ID="btnRemove" runat="server" onclick="btnRemove_Click" 
        Text="Remove Selected Item" Width="159px" />
    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
    <asp:Button ID="btnShopping" runat="server" onclick="btnShopping_Click" 
        Text="Continue Shopping" Width="156px" />
</asp:Content>
