<%@ Page Title="Home Page" Language="C#" MasterPageFile="~/Site.master" AutoEventWireup="true"
    CodeFile="Default.aspx.cs" Inherits="_Default" %>

<asp:Content ID="HeaderContent" runat="server" ContentPlaceHolderID="HeadContent">
</asp:Content>
<asp:Content ID="BodyContent" runat="server" ContentPlaceHolderID="MainContent">
    <h2>
        Welcome to the number guess game!
    </h2>
    <p>
        Choose a number between
        <asp:TextBox ID="TextBox1" runat="server" Width="36px"></asp:TextBox>
&nbsp;and
        <asp:TextBox ID="TextBox2" runat="server" Width="36px"></asp:TextBox>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<asp:Button ID="Button1" runat="server" onclick="Button1_Click" Text="Go!" 
            Width="94px" />
        &nbsp;
    </p>
    <p>
        <asp:Label ID="Label1" runat="server" Text="Your Guess :"></asp:Label>&nbsp;<asp:TextBox 
            ID="TextBox3" runat="server" Width="62px"></asp:TextBox>
&nbsp;<asp:Button ID="Button2" runat="server" Text="Guess" onclick="Button2_Click1" />
    &nbsp;<asp:Button ID="Button3" runat="server" Text="Hint" 
            onclick="Button3_Click1" />
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; Points Available:
        <asp:Label ID="Label4" runat="server" Font-Size="Large" Text="0"></asp:Label>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; Total Points:
        <asp:Label ID="Label5" runat="server" Font-Size="Large" Text="0"></asp:Label>
    </p>
    <p> 
        <asp:Label ID="Label2" runat="server" Text="..."></asp:Label>
    </p>
    <p> 
        <asp:Label ID="Label3" runat="server" Text="..."></asp:Label>
    </p>
</asp:Content>
