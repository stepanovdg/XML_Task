<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
                xmlns:val="xalan://com.epam.xmltask.controller.Validator">
    <xsl:output method="xml"/>
    <xsl:param name="categoryName"/>
    <xsl:param name="subCategoryName"/>
    <xsl:param name="unitName"/>
    <xsl:param name="unitProducer"/>
    <xsl:param name="unitModel"/>
    <xsl:param name="unitDate"/>
    <xsl:param name="unitColor"/>
    <xsl:param name="unitStock"/>
    <xsl:param name="unitPrice"/>
    <xsl:param name="validateMessage"/>

    <xsl:template match="/">
        <xsl:variable name="asd">
            <xsl:value-of select="val:validate($validateMessage,$unitName,$unitProducer,
            $unitModel,$unitDate,$unitColor,$unitStock,$unitPrice) "/>
        </xsl:variable>
        <xsl:choose>
            <xsl:when test='string-length($asd) = 0 '>
                <xsl:call-template name="copier"/>
            </xsl:when>
        </xsl:choose>
    </xsl:template>

    <xsl:template match="@*|node()" name="copier">
        <xsl:copy>
            <xsl:apply-templates select="@*|node()"/>
        </xsl:copy>
    </xsl:template>

    <xsl:template match="//category[@name=$categoryName]/subCategory[@name=$subCategoryName]">
        <xsl:element name="subCategory">
            <xsl:attribute name="name">
                <xsl:value-of select="$subCategoryName"/>
            </xsl:attribute>
            <xsl:apply-templates select="*"/>
            <xsl:element name="unit">
                <xsl:attribute name="name">
                    <xsl:value-of select="$unitName"/>
                </xsl:attribute>
                <xsl:attribute name="producer">
                    <xsl:value-of select="$unitProducer"/>
                </xsl:attribute>
                <xsl:attribute name="model">
                    <xsl:value-of select="$unitModel"/>
                </xsl:attribute>
                <xsl:element name="dateOfIssue">
                    <xsl:value-of select="$unitDate"/>
                </xsl:element>
                <xsl:element name="color">
                    <xsl:value-of select="$unitColor"/>
                </xsl:element>
                <xsl:choose>
                    <xsl:when test="$unitStock=true()">
                        <xsl:element name="notInStock">
                            <xsl:value-of select="$unitStock"/>
                        </xsl:element>
                    </xsl:when>
                    <xsl:otherwise>
                        <xsl:element name="price">
                            <xsl:value-of select="$unitPrice"/>
                        </xsl:element>
                    </xsl:otherwise>
                </xsl:choose>
            </xsl:element>
        </xsl:element>
    </xsl:template>


</xsl:stylesheet>