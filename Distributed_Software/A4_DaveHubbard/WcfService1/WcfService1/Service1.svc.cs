using System;
using System.Collections.Generic;
using System.Linq;
using System.Runtime.Serialization;
using System.ServiceModel;
using System.ServiceModel.Web;
using System.Text;
using System.Xml;
using System.Xml.Schema;
using System.IO;
using System.Xml.XPath;
using System.Xml.Xsl;



namespace WcfService1
{
    // NOTE: You can use the "Rename" command on the "Refactor" menu to change the class name "Service1" in code, svc and config file together.
    public class Service1 : IService1
    {
 
        public string ValidateXML(string xmlFile, string xsdFile)
        {
            XmlSchemaSet sc = new XmlSchemaSet();
            sc.Add(xmlFile, xsdFile);
            XmlReaderSettings settings = new XmlReaderSettings();
            settings.ValidationType = ValidationType.Schema;
            settings.Schemas = sc;
            XmlReader reader = XmlReader.Create(xmlFile, settings);
            try{
                while(reader.Read());
            }
            catch(Exception e){
                return e.Message;
            }
                return "No Error";
        }

        public string XMLTransform(string xmlFile, string xslFile)
        {
            StringWriter sw = new StringWriter();
           try {
                XPathDocument doc = new XPathDocument(xmlFile);
                XslCompiledTransform xt = new XslCompiledTransform();
                xt.Load(xslFile);
                xt.Transform(doc, null, sw);        
	       }
           catch (Exception ex) {
            return ex.Message;
           }
           return sw.ToString();
        }
    }
}
