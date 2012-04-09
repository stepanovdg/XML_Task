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
                        <TH>
                            <a href="/XMLTask/XSLTServlet?xsl=subCategory&amp;categoryName={$categoryName}">
                                SubCategory
                            </a>
                        </TH>
                        <TH>Product Name</TH>
                        <TH>Provider</TH>
                        <TH>Model</TH>
                        <TH>Date of Issue</TH>
                        <TH>Color</TH>
                        <TH>Price</TH>
                    </tr>
                    <xsl:for-each select="//category[@name=$categoryName]/subCategory[@name=$subCategoryName]/unit">
                        <xsl:variable name="stock" select="./notInStock"/>
                        <tr>
                            <td>
                                <xsl:value-of select="$categoryName"/>
                            </td>
                            <td>
                                <xsl:value-of select="$subCategoryName"/>
                            </td>
                            <td>
                                <xsl:value-of select="@name"/>
                            </td>
                            <td>
                                <xsl:value-of select="@producer"/>
                            </td>
                            <td>
                                <xsl:value-of select="@model"/>
                            </td>
                            <td>
                                <xsl:value-of select="./dateOfIssue"/>
                            </td>
                            <td style="background:{./color}; color:{./color}">
                                <xsl:value-of select="./color"/>
                            </td>
                            <td>
                                <xsl:choose>
                                    <xsl:when test="$stock=true()">
                                        Not In Stock
                                    </xsl:when>
                                    <xsl:otherwise>
                                        <xsl:value-of select="./price"/>
                                    </xsl:otherwise>
                                </xsl:choose>

                            </td>
                        </tr>
                    </xsl:for-each>
                </table>
                <a href="/XMLTask/XSLTServlet?xsl=add&amp;categoryName={$categoryName}&amp;subCategoryName={$subCategoryName}">
                    Add
                </a>
            </body>
        </html>
    </xsl:template>
</xsl:stylesheet>