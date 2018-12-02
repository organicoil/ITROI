<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0"
                xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
                xmlns:mag="http://ua.nure/magazines/"
                xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
                xsi:schemaLocation="http://ua.nure/magazines/ ../xsd/magazines.xsd">
    
    <xsl:output method="html" doctype-public="html"/>
    
    <xsl:template match="mag:magazines">
        <h2>Magazines</h2>
        <xsl:for-each select="mag:magazine">
            <xsl:sort select="@id"/>
            <ul>
                <li>
                    <xsl:value-of select="@id"/>
                </li>
                <li>
                    <xsl:value-of select="mag:title"/>
                </li>
                <li>
                    <xsl:value-of select="mag:description"/>
                </li>
                <li>
                    <xsl:value-of select="mag:price"/>
                </li>
                <li>
                    <xsl:value-of select="mag:publishing"/>
                </li>
                <li>
                    <xsl:value-of select="mag:category"/>
                </li>
            </ul>
        </xsl:for-each>
    </xsl:template>

</xsl:stylesheet>