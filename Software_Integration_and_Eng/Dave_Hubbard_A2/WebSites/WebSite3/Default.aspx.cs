using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.UI;
using System.Web.UI.WebControls;

public partial class _Default : System.Web.UI.Page
{
    static int secret;
    static int totalPoints = 0;
    static int points;
    protected void Page_Load(object sender, EventArgs e)
    {
        
    }
    protected void Button1_Click(object sender, EventArgs e)
    {
        numGuessProxy.ServiceClient ngp = new numGuessProxy.ServiceClient();
        int min = Convert.ToInt32(TextBox1.Text);
        int max = Convert.ToInt32(TextBox2.Text);
        secret = ngp.secretNumber(min, max);
        Button1.Text = "Restart";
        Label4.Text = "500";
        points = 500;
    }
  
    protected void Button2_Click1(object sender, EventArgs e)
    {
        if (points > 0)
        {
            numGuessProxy.ServiceClient ngp = new numGuessProxy.ServiceClient();
            int guess = Convert.ToInt32(TextBox3.Text);
            string result = ngp.checkNumber(guess, secret);
            Label2.Text = "The number is " + result + "!";


            if (result == "correct")
            {
                totalPoints += points;
                Label5.Text = Convert.ToString(totalPoints);
                points = 0;
            }
            else
                points -= 100;
            Label4.Text = Convert.ToString(points);
        }
        else
            Label2.Text = "Too bad, there are no more points to earn! Try Again!";
    }
    protected void Button3_Click1(object sender, EventArgs e)
    {
        if (points > 0)
        {
            hintProxy.ServiceClient hp = new hintProxy.ServiceClient();
            Label3.Text = hp.hint(secret);
            points -= 50;
            Label4.Text = Convert.ToString(points);
        }
        else
            Label3.Text = "Sorry, you can't afford any more hints!";
    }
}
