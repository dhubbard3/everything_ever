using System;
using System.IO;
using System.Collections.Generic;
using System.Linq;
using System.Runtime.Serialization;
using System.ServiceModel;
using System.ServiceModel.Web;
using System.Text;

namespace WsdlAddress
{
    
    public class Service1 : IService1
    {
        public string[] getWsdlAddress(string url)
        {
            webStringProxy.ServiceClient wsp = new webStringProxy.ServiceClient();
            string pageContents = "",currentLine;
            string webpage = "http://" + url;
            string[] result;

           
            pageContents = wsp.GetWebContent(webpage); //use getWebContent service to retrieve webpage contents.
           
           
            StringReader sr = new StringReader(pageContents);
            currentLine = sr.ReadLine();
            List<string> list = new List<string>();
            while (currentLine != null)
            {
                string[] sa;
                sa = currentLine.Split(new Char[] { ' ','"','<','>','\t','\n' });
                foreach (string s in sa)
                {
                    if (s.EndsWith(".wsdl") || s.EndsWith("?wsdl"))
                    {
                        list.Add(s);
                    }
                }
                currentLine = sr.ReadLine();
            }
            result = list.ToArray();
            return result;
        }//end getWsdlAddress
    }
}
