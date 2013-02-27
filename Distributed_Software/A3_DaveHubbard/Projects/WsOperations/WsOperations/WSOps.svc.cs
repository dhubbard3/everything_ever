using System;
using System.IO;
using System.Collections.Generic;
using System.Linq;
using System.Runtime.Serialization;
using System.ServiceModel;
using System.ServiceModel.Web;
using System.Text;
using System.Xml;

namespace WsOperations
{
    // NOTE: You can use the "Rename" command on the "Refactor" menu to change the class name "Service1" in code, svc and config file together.
    public class Service1 : IService1
    {
        public string[] WsOperations(string url)
        {
            webStringProxy.ServiceClient wsp = new webStringProxy.ServiceClient();
            string webpage = "http://" + url, name= "";
            List<string> list = new List<string>();

           if (webpage.EndsWith(".wsdl") || webpage.EndsWith("?wsdl"))
           {
                XmlReader xr = XmlReader.Create(webpage);
                xr.Read();
                while (xr.Name != "wsdl:binding")
                {
                    xr.Read();
                }

                while (xr.Read())
                {

                    if (xr.IsStartElement())
                    {
                        switch (xr.Name)
                        {
                            case "wsdl:operation":
                                name = xr["name"];
                                list.Add(name);
                                break;
                        }
                    }

                }

           }
            
            return list.ToArray();
        }
    }
}
