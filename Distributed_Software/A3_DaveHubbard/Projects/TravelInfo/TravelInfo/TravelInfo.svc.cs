using System;
using System.Collections.Generic;
using System.Linq;
using System.Runtime.Serialization;
using System.ServiceModel;
using System.ServiceModel.Web;
using System.Text;
using System.Xml;
using System.IO;

namespace TravelInfo
{
    // NOTE: You can use the "Rename" command on the "Refactor" menu to change the class name "Service1" in code, svc and config file together.
    public class Service1 : IService1
    {
        public double distanceConversion(double value, string type) //converts miles to kilometers and vice versa
        {
            double result;
            switch (type)
            {
                case "m": result = value * 1.609;
                    break;
                case "km": result =  value * 0.621371192;
                    break;
                default:
                    result = value * 1.609;
                    break;
            }
            return result;
        }

        public string basicZipInfo(string zip)
        {
            string info = "", result = "No Info. Try another zip code.", state, zipCode, lat, lon;
            zipProxy.USZip zipInfo = new zipProxy.USZip();
            info = zipInfo.ValidateZip(zip);
            XmlReader xr = XmlReader.Create(new StringReader(info));
            while (xr.Read())
            {
                if (xr.Name == "item")
                {
                    zipCode = xr["zip"];
                    state = xr["state"];
                    lat = xr["latitude"];
                    lon = xr["longitude"];
                    result = "The zip code " + zip + "is located in " + state + ". \n";
                    result += "Global coordinates are latitude " + lat + "and longitude " + lon + ".";
                }
            }
                return result;
        }

        public double ZipCodeDistance(string zip1, string zip2) //uses haversine formula to calculate zip distance
        {
            double result = 0;
            string info1, info2;
            zipProxy.USZip zip = new zipProxy.USZip();
            info1 = zip.ValidateZip(zip1);
            info2 = zip.ValidateZip(zip2);
            double p1Lat=0, p1Lon=0, p2Lat=0, p2Lon=0;
            XmlReader x1 = XmlReader.Create(new StringReader(info1));
            XmlReader x2 = XmlReader.Create(new StringReader(info2));

            while (x1.Read())
            {
                if (x1.Name == "item")
                {
                    p1Lat = Convert.ToDouble(x1["latitude"]);
                    p1Lon = Convert.ToDouble(x1["longitude"]);
                }
            }

            while (x2.Read())
            {
                if (x2.Name == "item")
                {
                    p2Lat = Convert.ToDouble(x2["latitude"]);
                    p2Lon = Convert.ToDouble(x2["longitude"]);
                }
            }

            double radius = 6371;
            double dLat = (Math.PI / 180) * (p2Lat - p1Lat);
            double dLon = (Math.PI / 180) * (p2Lon - p1Lon);
            double d = Math.Sin(dLat / 2) * Math.Sin(dLat / 2) + Math.Cos((Math.PI / 180) * (p1Lat)) * Math.Cos((Math.PI / 180) * (p2Lon)) * Math.Sin(dLon / 2) * Math.Sin(dLon / 2);
            double calc = 2 * Math.Asin(Math.Min(1, Math.Sqrt(d)));
            result = radius * calc;
            return result;
        }
    }
}
