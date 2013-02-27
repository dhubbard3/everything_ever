<%@ Page Title="" Language="C#" MasterPageFile="~/Site.Master" AutoEventWireup="true" CodeBehind="Members.aspx.cs" Inherits="GolfApp.Account.Members" %>
<asp:Content ID="Content1" ContentPlaceHolderID="HeadContent" runat="server">
</asp:Content>
<asp:Content ID="Content2" ContentPlaceHolderID="MainContent" runat="server">
    <h2>Members</h2>
    <p><% Response.Write("Welcome " + Context.User.Identity.Name + "!"); %> <br />
        Tell us about your recent accomplishments!
    </p>
    <p><strong>Enter Your Information Here:</strong><br />
      Course Name:<br /><asp:TextBox ID="TextBox1" runat="server" Width="214px" 
            BorderStyle="Solid" BorderWidth="1px"></asp:TextBox><br />
        Score:<br /><asp:TextBox ID="TextBox2" runat="server" 
            Width="214px" BorderStyle="Solid" BorderWidth="1px"></asp:TextBox>
    </p>
    <p>
        <asp:Label ID="Label1" runat="server" ForeColor="Red" Text="Label" 
            Visible="False"></asp:Label><br />
        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
        <asp:Button ID="Button1" runat="server" Text="Submit" onclick="Button1_Click" />
    </p>
</asp:Content>
