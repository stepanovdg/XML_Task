<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform" >
    <xsl:output method="html" omit-xml-declaration="yes"/>
    <xsl:param name="categoryName"/>
    <xsl:param name="subCategoryName"/>
    <xsl:param name="unitName"/>
    <xsl:template match="/">
        <html>
            <head>
                <link rel="stylesheet" href="/XMLTask/css/ie.css" type="text/css"
                      media="screen"/>
                <script type="text/javascript" src="/XMLTask/js/jquery-1.7.1.js"/>
                <script type="text/javascript" src="/XMLTask/js/xsl.js"/>
            </head>
            <body id="addBody">
                <form id="addForm" name="add" method="POST" action="/XMLTask/XSLTServlet?xsl=unit">
                    <!--<div id="catName">
                        <th>
                            Category
                        </th>
                        <select id="catNameSelect" name="categoryName" required="true" value="{$categoryName}">
                            <option value="">
                                New...
                            </option>
                            <xsl:for-each select="//category">
                                <xsl:variable name="cName">
                                    <xsl:value-of select="@name"/>
                                </xsl:variable>
                                <option value="{$cName}">
                                    <xsl:value-of select="@name"/>
                                </option>
                            </xsl:for-each>
                        </select>
                    </div>
                    <div id="catNameInput">
                        <th>
                            Enter New Category Name
                        </th>
                        <input type="text" name="categoryName" value=""/>
                    </div>
                    <div id="subCatName">
                        <th>
                            SubCategory
                        </th>
                        <select name="subCategoryName" required="true" value="$subCategoryName">
                            <option value="">
                                New...
                            </option>
                            <xsl:for-each select="//category[@name=$categoryName]/subCategory">
                                <xsl:variable name="sName">
                                    <xsl:value-of select="@name"/>
                                </xsl:variable>
                                <option value="{$sName}">
                                    <xsl:value-of select="@name"/>
                                </option>
                            </xsl:for-each>
                        </select>
                    </div>
                     <div id="subCatNameInput">
                        <th>
                            Enter New SubCategory Name
                        </th>
                        <input type="text" name="subCategoryName" value=""/>
                    </div>
                    -->
                    <div id="catName">
                        <th>
                            Category
                        </th>
                        <input type="text" name="categoryName" value="{$categoryName}"  readonly="true" />
                    </div>
                    <div id="subCatName">
                        <th>
                            SubCategory
                        </th>
                        <input type="text" name="subCategoryName" value="{$subCategoryName}"  readonly="true"/>
                    </div>
                    <div id="unitInput">
                        <div id="unitName">
                            <th>
                                Unit Name
                            </th>
                            <input type="text" name="unitName" value=""/>
                        </div>
                        <div id="unitProducer">
                            <th>
                                Unit Producer
                            </th>
                            <input type="text" name="unitProducer" value=""/>
                        </div>
                        <div id="unitModel">
                            <th>
                                Unit Model
                            </th>
                            <input type="text" name="unitModel" value=""/>
                        </div>
                        <div id="unitDate">
                            <th>
                                Unit Date
                            </th>
                            <input type="text" name="unitDate" value=""/>
                        </div>
                        <div id="unitColor">
                            <th>
                                Unit Color
                            </th>
                            <input type="text" name="unitColor" value=""/>
                        </div>
                        <div id="unitStock">
                            <th>
                                Unit is not in Stock
                            </th>
                            <input id="unitStockBox" type="checkbox" name="unitStock" value="true" />
                        </div>
                        <div id="unitPrice">
                            <th>
                                Unit Price
                            </th>
                            <input  type="text" name="unitPrice" value=""/>
                        </div>
                        <div id="submit">
                            <input id="submitButton" type="submit" value="Save"/>
                        </div>
                        <div id="reset">
                            <input id="resetButton" type="reset" value="Reset"/>
                        </div>
                        <div id="cancel">
                            <input id="cancelButton" type="button" value="Cancel"/>
                        </div>
                    </div>
                </form>
            </body>
        </html>
    </xsl:template>
</xsl:stylesheet>