using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Windows.Forms;

//The two services used are a NumberGuessing service and an Encryption/Decryption service. Both services are from the ASU repository 
//located at venus.eas.asu.edu.
namespace Browser
{
    public partial class Form1 : Form
    {
        int lower = 0; // variables used for the number guess.
        int upper = 0;
        int num = 0;

        public Form1()
        {
            InitializeComponent();
        }

        private void cryptoBtn_Click(object sender, EventArgs e) //Cryptography Encrypt button.
        {
            string entry, encryptTxt, decryptTxt;
            cryptoProxy.Service cp = new cryptoProxy.Service();
            entry = textBox1.Text;
            encryptTxt = cp.Encrypt(entry);
            decryptTxt = cp.Decrypt(encryptTxt);
            label2.Text = decryptTxt;
            label1.Text = encryptTxt;
        }

        private void ngBtn_Click(object sender, EventArgs e) //number guessing Start!/Reset! button.
        {   
            numberGuessProxy.Service np = new numberGuessProxy.Service();
            Random r = new Random();
            bool b = true;
            lower = r.Next(13);
            upper = r.Next(51);
            label7.Text = ("The number is between " + lower + " and " + upper + ".");
            np.secretNumber(lower, b, upper, b, out num, out b);
            ngBtn.Text = "Reset!";
        }

        private void guessBtn_Click(object sender, EventArgs e) //number guessing Guess button.
        {
            numberGuessProxy.Service np2 = new numberGuessProxy.Service();
            string guess = textBox2.Text;
            bool b = true;
            try{
                int num2 = Convert.ToInt32(guess);
                label8.Text = np2.checkNumber(num2, b, num, b) + "!";
            } catch(FormatException ex){
                label8.Text = "Please enter an integer";
            }

        }

        private void goBtn_Click(object sender, EventArgs e)
        {
            webBrowser1.Navigate(textBox3.Text);
        }
    }
}
