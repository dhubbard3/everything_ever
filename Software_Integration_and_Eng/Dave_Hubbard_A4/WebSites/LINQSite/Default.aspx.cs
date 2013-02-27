using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.UI;
using System.Web.UI.WebControls;

public partial class _Default : System.Web.UI.Page
{
    Instructors[] instructors;
    protected void Page_Load(object sender, EventArgs e)
    {
        instructors = new Instructors[]{
            new Instructors{first="Kevin", last="Burger", office="BYENG 501", course="Principles of Programming Languages with Java"},
            new Instructors{first="Kevin", last="Burger", office="BYENG 501", course="Principles of Programming Languages with C++"},
            new Instructors{first="Kevin", last="Burger", office="BYENG 501", course="Programming for Computer Engineers"},
            new Instructors{first="Bassam", last="Matar", office="BYENG 406", course="Digital Design Fundamentals"},
            new Instructors{first="Mutsumi", last="Nakamura", office="BYENG 520", course="Object-Oriented Programming & Data Structures"},
            new Instructors{first="Mutsumi", last="Nakamura", office="BYENG 520", course="Computer Organization/ Assembly Programming Language"},
            new Instructors{first="Mutsumi", last="Nakamura", office="BYENG 520", course="Honors Directed Study"},
            new Instructors{first="Mutsumi", last="Nakamura", office="BYENG 520", course="Honors Thesis"},
            new Instructors{first="Chitta", last="Baral", office="BYENG 572", course="Computer Organization/ Assembly Programming Language"},
            new Instructors{first="Chitta", last="Baral", office="BYENG 572", course="Honors Directed Study"},
            new Instructors{first="Chitta", last="Baral", office="BYENG 572", course="Natural Language and Understanding"},
            new Instructors{first="Wing", last="Cheng", office="BYENG 412", course="Intro to Programming Languages"},
            new Instructors{first="Andrea", last="Richa", office="BYENG 440", course="Data Structures & Algorithms"},
            new Instructors{first="Andrea", last="Richa", office="BYENG 440", course="Thesis"},
            new Instructors{first="Toni", last="Farley", office="BYENG 417", course="Principles of Programming Languages"},
            new Instructors{first="Frank", last="Calliss", office="BYENG 512", course="Software Analysis & Design"},
            new Instructors{first="Joohyung", last="Lee", office="BYENG 472", course="Logic for Computer Scientists"},
            new Instructors{first="Joohyung", last="Lee", office="BYENG 472", course="Knowledge, Represent/Reasoning"},
            new Instructors{first="Partha", last="Dasgupta", office="BYENG 428", course="Operating Systems"},
            new Instructors{first="Partha", last="Dasgupta", office="BYENG 428", course="Applied Cryptography"},
        };
    }
    protected void Button1_Click(object sender, EventArgs e)
    {
        string result = "---------Upper Division Courses---------\n\n";
        courseClassesDataContext courseDB = new courseClassesDataContext();
        IEnumerable<course> courseQuery =   // LINQ Query 1.6a
            from c in courseDB.courses
            where c.ccode > 300
            orderby c.cap
            select c;
        foreach(course c in courseQuery){
             result += ("Course = " + c.title +  ", Instructor = " + c.instructor + "\n");
        }
        result += "\n\n";
        var courseGroups =                  //LINQ Query 1.6b
            from c in courseDB.courses
            group c by (int)(c.ccode / 100) into g
            select new { Level = g.Key, Courses = g };
        foreach (var g in courseGroups)
        {
            result += "---------"+ g.Level + "00 Level Courses---------\n";
            foreach (var c in g.Courses)
            {
                result += c.title + "\n";
            }
            result += "\n";
        }
        TextBox1.Text = result;
    }

    class Instructors
    {
        public string first, last, office, course;
    }
}