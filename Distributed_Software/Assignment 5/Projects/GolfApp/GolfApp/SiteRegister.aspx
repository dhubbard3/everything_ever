<%@ Page Title="Register" Language="C#" MasterPageFile="~/Site.master" AutoEventWireup="true"
    CodeBehind="SiteRegister.aspx.cs" Inherits="GolfApp.SiteRegister" %>

<asp:Content ID="HeaderContent" runat="server" ContentPlaceHolderID="HeadContent">
</asp:Content>
<asp:Content ID="BodyContent" runat="server" ContentPlaceHolderID="MainContent">
    <h2>Create Account</h2>

     <br />
    Enter your information below to register an account:<br />
    <br />
    User Name:<br />
    <asp:TextBox ID="TextBox1" runat="server" BorderStyle="Solid" BorderWidth="1px" 
        Width="304px"></asp:TextBox>

     &nbsp;&nbsp;&nbsp;&nbsp;<br />
    <br />
    Password:<br />
    <asp:TextBox ID="TextBox2" runat="server" BorderStyle="Solid" BorderWidth="1px" 
        Width="304px"></asp:TextBox>
    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<br />
    <br />
    Confim Password:<br />
    <asp:TextBox ID="TextBox3" runat="server" BorderStyle="Solid" BorderWidth="1px" 
        Width="304px"></asp:TextBox>
    <br />
    <br />
    <asp:Label ID="Label1" runat="server" ForeColor="Red" Text="Label" 
        Visible="False"></asp:Label>
    <br />
    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<br />
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
     <asp:Button ID="createUser" OnClick="CreateUser" runat="server" Text="Create Account"/>
</asp:Content>
