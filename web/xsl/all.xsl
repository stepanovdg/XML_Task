<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
    <xsl:output method="html" omit-xml-declaration="yes"/>
    <!--<xsl:output method="xml"/>-->
    <xsl:param name="categoryName"/>
    <xsl:param name="subCategoryName"/>
    <xsl:param name="unitName"/>
    <xsl:param name="unitProducer"/>
    <xsl:param name="unitModel"/>
    <xsl:param name="unitDate"/>
    <xsl:param name="unitColor"/>
    <xsl:param name="unitStock"/>
    <xsl:param name="unitPrice"/>
    <xsl:param name="showAdd"/>
    <xsl:param name="validateMessage"/>
    <xsl:template match="/">

        <html>
            <head>
                <link rel="stylesheet" href="css/ie.css" type="text/css" media="screen"/>
                <script type="text/javascript" src="js/jquery-1.7.1.js"/>
                <script type="text/javascript" src="js/xsl.js"/>
            </head>
            <body>
                <table id="goodsTable">

                    <tr>
                        <TH>Category</TH>
                        <TH>Subcategory</TH>
                        <TH>Product Name</TH>
                        <TH>Provider</TH>
                        <TH>Model</TH>
                        <TH>Date of Issue</TH>
                        <TH>Color</TH>
                        <TH>Price</TH>
                    </tr>

                    <xsl:call-template name="category">
                    </xsl:call-template>
                </table>
                <a href="/XMLTask/">
                           Back to Index
                </a>
            </body>
        </html>
    </xsl:template>

    <xsl:template name="category">

        <xsl:for-each select="//category">
            <xsl:variable name="cName">
                <xsl:value-of select="@name"/>
            </xsl:variable>
            <xsl:element name="tr">
                <td>
                    <a href="XSLTServlet?categoryName={$cName}">
                        <xsl:value-of select="@name"/>
                    </a>
                </td>
                <td>
                    <xsl:value-of select="count(//category[@name=$cName]/subCategory/*)"/>
                </td>
                <xsl:if test="$categoryName=$cName">
                    <td>
                        <xsl:call-template name="subCategory">
                        </xsl:call-template>
                    </td>
                </xsl:if>

            </xsl:element>
        </xsl:for-each>

    </xsl:template>

    <xsl:template name="subCategory">

        <xsl:for-each select="//category[@name=$categoryName]/subCategory">
            <xsl:variable name="sName">
                <xsl:value-of select="@name"/>
            </xsl:variable>

            <tr>
                <td/>
                <td>
                    <a href="XSLTServlet?categoryName={$categoryName}&amp;subCategoryName={$sName}">
                        <xsl:value-of select="@name"/>
                    </a>
                </td>
                <td>
                    <xsl:value-of select="count(//category[@name=$categoryName]/subCategory[@name=$sName]/*)"/>
                </td>
                <xsl:if test="$subCategoryName=$sName">
                    <td>
                        <xsl:call-template name="unit">
                        </xsl:call-template>
                    </td>
                </xsl:if>
            </tr>
        </xsl:for-each>

    </xsl:template>

    <xsl:template name="unit">

        <xsl:for-each select="//category[@name=$categoryName]/subCategory[@name=$subCategoryName]/unit">
            <xsl:variable name="stock" select="./notInStock"/>
            <tr>
                <td/>
                <td/>
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
        <tr>
            <xsl:choose>
                <xsl:when test="$showAdd=true()">
                    <form  name="add" method="POST" action="XSLTServlet">
                        <input type="hidden" name="xsl" value="Add"/>
                        <input type="hidden" name="check" value="true"/>
                        <input type="hidden" id="validateMessage" value="{$validateMessage}"/>
                        <input type="hidden" id="categoryName" name="categoryName" value="{$categoryName}"/>
                        <input type="hidden" id="subCategoryName" name="subCategoryName" value="{$subCategoryName}"/>
                        <td>
                            <input id="submitButton" type="submit" value="Save"/>
                        </td>
                        <td>
                            <input id="cancelButton" type="button" value="Cancel"/>
                        </td>
                        <td>
                            <input type="text" name="unitName" value="{$unitName}"/>
                        </td>
                        <td>
                            <input type="text" name="unitProducer" value="{$unitProducer}"/>
                        </td>
                        <td>
                            <input type="text" name="unitModel" value="{$unitModel}"/>
                        </td>
                        <td>
                            <input type="text" name="unitDate" value="{$unitDate}"/>
                        </td>
                        <td>
                            <input type="text" name="unitColor" value="{$unitColor}"/>
                        </td>
                        <td>
                            <input id="unitStockBox" type="checkbox" name="unitStock" value="true"/>
                            <div id="unitPrice" style="width=5px">
                                <input type="text" name="unitPrice" value="{$unitPrice}"/>
                            </div>
                        </td>
                    </form>
                </xsl:when>
                <xsl:otherwise>
                    <td/>
                    <td/>
                    <td>
                        <a href="XSLTServlet?showAdd=true&amp;categoryName={$categoryName}&amp;subCategoryName={$subCategoryName}">
                            Add New Unit
                        </a>
                    </td>
                    <td/>
                    <td/>
                    <td/>
                    <td/>
                </xsl:otherwise>
            </xsl:choose>
        </tr>
    </xsl:template>


</xsl:stylesheet>