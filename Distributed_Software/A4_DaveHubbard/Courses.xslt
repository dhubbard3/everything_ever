<?xml version="1.0" encoding="utf-8"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
    xmlns:msxsl="urn:schemas-microsoft-com:xslt" exclude-result-prefixes="msxsl"
>
    <xsl:template match="/">
      <html>
        <body>

          <h1>Available Courses</h1>
          <table border="1">
            <tr bgcolor ="blue">
              <td><b>Grad Code </b></td>
              <td>
                <b>Undergrad Code </b>
              </td>
              <td>
                <b>Course Name </b>
              </td>
              <td>
                <b>Instructor </b>
              </td>
              <td>
                <b>Phone </b>
              </td>
              <td>
                <b>Office </b>
              </td>
              <td>
                <b>Room </b>
              </td>
              <td>
                <b>Cap </b>
              </td>
            </tr>
            <xsl:for-each select="Courses/Course">
              <xsl:sort select="Name"/>
              <tr style="font-size: 10pt; font-family: Verdana">
                <td>
                  <xsl:value-of select="Code/@Grad"/>
                </td>
                <td>
                  <xsl:value-of select="Code/@Undergrad"/>
                </td>
                <td>
                  <xsl:value-of select="Name"/>
                </td>
                <td>
                  <xsl:value-of select="Instructor/Name/First"/>
                  <xsl:text> </xsl:text>
                  <xsl:value-of select="Instructor/Name/Last"/>
                </td>
                <td>
                  <xsl:value-of select="Instructor/Contact/Phone"/>
                </td>
                <td>
                  <xsl:value-of select="Instructor/Contact/@Office"/>
                </td>
                <td>
                  <xsl:value-of select="Room"/>
                </td>
                <td>
                  <xsl:value-of select="Cap"/>
                </td>
              </tr>
            </xsl:for-each>
          </table>
          
        </body>
      </html>
    </xsl:template>
</xsl:stylesheet>
