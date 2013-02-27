using System;
using System.Collections.Generic;
using System.Linq;
using System.Windows.Forms;

namespace TipCalculator
{
    static class Program
    {
        /// <summary>
        /// The main entry point for the application.
        /// </summary>
        [STAThread]
        static void Main()
        {
            Application.EnableVisualStyles();
            Application.SetCompatibleTextRenderingDefault(false);
            CalcView f = new CalcView();
            CalcModel m = new CalcModel();
            CalcModel.notify += new Notifier(f.update);
            CalcModel.error += new Error(f.showError);
            Application.Run(f);
        }
    }
}
