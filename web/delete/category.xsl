<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
    <xsl:output method="html" omit-xml-declaration="yes"/>
    <xsl:param name="categoryName"/>
    <xsl:param name="subCategoryName"/>
    <xsl:param name="unitName"/>
    <xsl:template match="/">
        <html>
            <head>
                <link rel="stylesheet" href="/XMLTask/css/ie.css" type="text/css"
                      media="screen"/>
            </head>
            <body>
                <table>

                    <tr>
                        <TH>
                            <a href="/XMLTask/XSLTServlet?xsl=category">
                                Category
                            </a>
                        </TH>
                        <TH>Subcategory</TH>
                        <TH>Product Name</TH>
                        <TH>Provider</TH>
                        <TH>Model</TH>
                        <TH>Date of Issue</TH>
                        <TH>Color</TH>
                        <TH>Price</TH>
                    </tr>
                    <xsl:for-each select="//category">
                        <xsl:variable name="cName">
                            <xsl:value-of select="@name"/>
                        </xsl:variable>
                        <xsl:element name="tr">
                            <td>
                                <a href="/XMLTask/XSLTServlet?xsl=subCategory&amp;categoryName={$cName}">
                                    <xsl:value-of select="@name"/>
                                </a>
                            </td>
                        </xsl:element>
                    </xsl:for-each>
                </table>
            </body>
        </html>
    </xsl:template>
</xsl:stylesheet>