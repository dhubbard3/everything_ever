using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Activities;

namespace DeclarativeServiceLibrary1
{

    public sealed class CodeActivity1 : CodeActivity
    {
        // Define an activity input argument of type string
        public InArgument<int> num { get; set; }
        public OutArgument<string> tip { get; set; }

        // If your activity returns a value, derive from CodeActivity<TResult>
        // and return the value from the Execute method.
        protected override void Execute(CodeActivityContext context)
        {
            // Obtain the runtime value of the Text input argument
            int n = context.GetValue(this.num);
            Random r = new Random();
            string hint = "";
            int choice = r.Next(0, 3);
            switch (choice)
            {
                case 0:
                    if ((n % 2) == 0)
                        hint = "The number is even.";
                    else
                        hint = "The number is odd.";
                    break;
                case 1:
                    int factor = n * (r.Next(2, 15));
                    hint = "The number is a factor of " + factor + ".";
                    break;
                case 2:
                    int divi = r.Next(0, n + 1);
                    if ((n % divi) == 0)
                        hint = "The number is divisible by " + divi + ".";
                    else
                        hint = "The number is not divisible by " + divi + ".";
                    break;
            }
            context.SetValue(this.tip, hint);
        }
    }
}
