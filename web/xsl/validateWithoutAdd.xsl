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
    </xsl:template>

</xsl:stylesheet>