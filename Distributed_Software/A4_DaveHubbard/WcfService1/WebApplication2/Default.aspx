<%@ Page Title="Home Page" Language="C#" MasterPageFile="~/Site.master" AutoEventWireup="true"
    CodeBehind="Default.aspx.cs" Inherits="WebApplication2._Default" %>

<asp:Content ID="HeaderContent" runat="server" ContentPlaceHolderID="HeadContent">
</asp:Content>
<asp:Content ID="BodyContent" runat="server" ContentPlaceHolderID="MainContent">
    <h2>
       
    </h2>
    <p>
        Enter URL of XML file:&nbsp;
        <asp:TextBox ID="TextBox1" runat="server" Width="350px"></asp:TextBox>
        
    </p>
<p>
        Enter URL of .xsd file:&nbsp;
        <asp:TextBox ID="TextBox2" runat="server" Width="354px"></asp:TextBox>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
        <asp:Button ID="Button1" runat="server" onclick="Button1_Click" 
            Text="Validate XML" Width="130px" />
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
        <asp:Button ID="Button2" runat="server" Text="Generate HTML" Width="128px" 
            onclick="Button2_Click" />
        
    </p>
    <p>
    
        Enter URL of .xsl(t) file:
        <asp:TextBox ID="TextBox3" runat="server" Width="350px"></asp:TextBox>
    
    </p>
<p>
    
    <asp:TextBox ID="TextBox4" runat="server" Height="306px" TextMode="MultiLine" 
        Width="892px"></asp:TextBox>
    
    </p>
</asp:Content>
