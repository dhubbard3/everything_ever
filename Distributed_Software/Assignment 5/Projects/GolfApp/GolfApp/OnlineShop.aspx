<%@ Page Title="" Language="C#" MasterPageFile="~/Site.Master" AutoEventWireup="true" CodeBehind="OnlineShop.aspx.cs" Inherits="GolfApp.OnlineShop" %>
<asp:Content ID="Content1" ContentPlaceHolderID="HeadContent" runat="server">
</asp:Content>
<asp:Content ID="Content2" ContentPlaceHolderID="MainContent" runat="server">
    <asp:ListBox ID="ListBoxCatalog" runat="server" Width="350px"></asp:ListBox>
    <br />
    <asp:Button ID="btnViewItem" runat="server" onclick="btnViewItem_Click" 
        Text="View Item Detail" />
    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
    <asp:Button ID="btnSeller" runat="server" onclick="btnSeller_Click" 
        Text="Add Item to Catalog" Width="174px" />
    <br />
    <asp:Button ID="btnAddToCar" runat="server" onclick="btnAddToCar_Click" 
        Text="Add to Cart" />
    <br />
    <br />
    <asp:Label ID="lblName" runat="server"></asp:Label>
</asp:Content>
