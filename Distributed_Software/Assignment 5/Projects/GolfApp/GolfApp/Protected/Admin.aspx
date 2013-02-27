<%@ Page Title="" Language="C#" MasterPageFile="~/Site.Master" AutoEventWireup="true" CodeBehind="Admin.aspx.cs" Inherits="GolfApp.Protected.Admin" %>
<asp:Content ID="Content1" ContentPlaceHolderID="HeadContent" runat="server">
</asp:Content>
<asp:Content ID="Content2" ContentPlaceHolderID="MainContent" runat="server">
    <h2>Administration</h2>
    <p>
        <% Response.Write("Hello " + Context.User.Identity.Name + ", "); %> <br />
        Use the fields below to add or delete user accounts and recent scores:<br />
        <asp:Label ID="Label1" runat="server" Text="Error Label" ForeColor="Red" 
            Visible="False"></asp:Label>
    </p>
    <p>
        <strong>Add/Delete User Account:</strong><br />
        User Name:<br /><asp:TextBox ID="TextBox1" runat="server" Width="214px" 
            BorderStyle="Solid" BorderWidth="1px"></asp:TextBox><br />
        Password (Required to Add User):<br />
        <asp:TextBox ID="TextBox2" runat="server" 
            Width="214px" BorderStyle="Solid" BorderWidth="1px" TextMode="Password"></asp:TextBox></p>
    <p>
        &nbsp;&nbsp;&nbsp;
        <asp:Button ID="Button1" runat="server" Text="Add" Width="53px" 
            onclick="Button1_Click" />
    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
        <asp:Button ID="Button2" runat="server" Text="Delete" onclick="Button2_Click" />
    </p>
    <p>
        <strong>Add/Delete User Scores:</strong><br />
        User Name:<br /><asp:TextBox ID="TextBox3" runat="server" Width="214px" 
            BorderStyle="Solid" BorderWidth="1px"></asp:TextBox><br />
        Course Name:<br /><asp:TextBox ID="TextBox4" runat="server" 
            Width="214px" BorderStyle="Solid" BorderWidth="1px"></asp:TextBox><br />
        Score:<br /><asp:TextBox ID="TextBox5" runat="server" 
            Width="214px" BorderStyle="Solid" BorderWidth="1px"></asp:TextBox>
    </p>
    <p>
       &nbsp;&nbsp;&nbsp;
        <asp:Button ID="Button3" runat="server" Text="Add" Width="53px" 
            onclick="Button3_Click" />
    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
        <asp:Button ID="Button4" runat="server" Text="Delete" onclick="Button4_Click" />
    </p>
    
</asp:Content>
