using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Windows.Forms;

namespace zipCodeInfo 
    //takes a zip code and returns a string with information on the state, latitude, and longitude. The returning string is an XML document
    //if I'm not mistaken, but I'm not familiar with how to separate the info, so the text is a bit jumbled. Service is referenced from
    //www.webservicemart.com/uszip.asmx?WSDL
{
    public partial class Form1 : Form
    {
        public Form1()
        {
            InitializeComponent();
        }

        private void button1_Click(object sender, EventArgs e)
        {
            string zip1;
            string result;
            zipProxy.USZip zipInfo = new zipProxy.USZip();

            zip1 = textBox1.Text;
            if (zip1 == "")
            {
                label3.Text = "Enter a zip code.";
            }
            else
            {
                result = zipInfo.ValidateZip(zip1);
                label3.Text = result;
            }

        }
    }
}
