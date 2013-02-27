<%@ Page Title="Home Page" Language="C#" MasterPageFile="~/Site.master" AutoEventWireup="true"
    CodeBehind="Default.aspx.cs" Inherits="GolfApp._Default" %>

<%@ Register TagPrefix="control1" TagName="UserControl1" Src="~/UserControl1.ascx" %>
<%@ Register TagPrefix="control4" TagName="UserControl4" Src="~/UserControl4.ascx" %>
<asp:Content ID="HeaderContent" runat="server" ContentPlaceHolderID="HeadContent">
</asp:Content>
<asp:Content ID="BodyContent" runat="server" ContentPlaceHolderID="MainContent">
    <h2>
        WELCOME!</h2>
    <asp:Panel ID="Panel1" runat="server" Height="273px">
</asp:Panel>
    <p>
        <strong>Calculate how you did on the last hole in golf terms:&nbsp;
            <asp:Button ID="Button1" runat="server" OnClick="Button1_Click" 
            Text="Calculate" />
        </strong>
    </p>
    <p>
        Score:
        <asp:TextBox ID="TextBox1" runat="server"></asp:TextBox>
        &nbsp; Par:
        <asp:TextBox ID="TextBox2" runat="server"></asp:TextBox>
        &nbsp; Golf Term:
        <asp:Label ID="Label1" runat="server"></asp:Label>
    </p>
    <p>
        <strong>Par Calculator: Find out what par a number of yards would translate to:
            <asp:Button ID="Button2" runat="server" OnClick="Button2_Click" 
            Text="Calculate" />
        </strong>
    </p>
    <p>
        Yards:
        <asp:TextBox ID="TextBox3" runat="server"></asp:TextBox>
        Par:&nbsp;
        <asp:Label ID="Label2" runat="server"></asp:Label>
    </p>
    <p>
        <strong>Golf Measurements Conversion:</strong></p>
    <p>
        Yards to Meters:
        <asp:Button ID="Button3" runat="server" Text="Convert" 
            onclick="Button3_Click" />
    </p>
    <p>
        Yards:
        <asp:TextBox ID="TextBox4" runat="server"></asp:TextBox>
        &nbsp;Meters:
        <asp:Label ID="Label3" runat="server"></asp:Label>
    </p>
    <p>
        Meters to Yards:
        <asp:Button ID="Button4" runat="server" Text="Convert" 
            onclick="Button4_Click" />
    </p>
    <p>
        Meters:&nbsp;
        <asp:TextBox ID="TextBox5" runat="server"></asp:TextBox>
        &nbsp;Yards:
        <asp:Label ID="Label4" runat="server"></asp:Label>
    </p>
    <p>
        <strong>Find out which club you should use:
            <asp:Button ID="Button5" runat="server" Text="Calculate" 
            onclick="Button5_Click" />
        </strong>
    </p>
    <p>
        &nbsp;Distance to hit:
        <asp:TextBox ID="TextBox6" runat="server"></asp:TextBox>
        &nbsp;<asp:RadioButton ID="RadioButton1" runat="server" Checked="True" OnCheckedChanged="RadioButton1_CheckedChanged"
            Text="Man" GroupName="Gender" />
        &nbsp;<asp:RadioButton ID="RadioButton2" runat="server" Text="Woman" 
            GroupName="Gender" />
        &nbsp;&nbsp; Club:&nbsp;
        <asp:Label ID="Label5" runat="server"></asp:Label>
    </p>
    <div>
        <div>
            <control4:UserControl4 ID="UserControl4" runat="server" />
        </div>
        <div>
            <br />
            <control1:UserControl1 ID="UserControl1" runat="server" />
        </div>
    </div>
</asp:Content>
