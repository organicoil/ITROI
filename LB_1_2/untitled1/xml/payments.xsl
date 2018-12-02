<?xml version="1.0" encoding="utf-8"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
    <xsl:template name="string-replace-all">
        <xsl:param name="text" />
        <xsl:param name="replace" />
        <xsl:param name="by" />
        <xsl:choose>
            <xsl:when test="$text = '' or $replace = ''or not($replace)" >
                <xsl:value-of select="$text" />
            </xsl:when>
            <xsl:when test="contains($text, $replace)">
                <xsl:value-of select="substring-before($text,$replace)" />
                <xsl:value-of select="$by" />
                <xsl:call-template name="string-replace-all">
                    <xsl:with-param name="text" select="substring-after($text,$replace)" />
                    <xsl:with-param name="replace" select="$replace" />
                    <xsl:with-param name="by" select="$by" />
                </xsl:call-template>
            </xsl:when>
            <xsl:otherwise>
                <xsl:value-of select="$text" />
            </xsl:otherwise>
        </xsl:choose>
    </xsl:template>
    <xsl:template match="/">
        <html>
            <head>
                <meta charset="utf-8"/>
                <title>Payments</title>
            </head>
            <body>
                <h1>Платежи за период с <xsl:value-of select="payments/@fromDate"/> по <xsl:value-of select="payments/@toDate"/>:</h1>
                <table  border="1" style="width: 100%">
                    <tr>
                        <th>Дата и время совершения</th>
                        <th>Отправитель</th>
                        <th>Получатель</th>
                        <th>Сума перевода</th>
                        <th>Дополнительная информация</th>
                    </tr>
                    <xsl:for-each select="payments/payment">
                        <xsl:variable name="rowStyle">
                            <xsl:choose>
                                <xsl:when test="Info/PError">
                                    <xsl:value-of select="'background-color: #ff9698'"/>
                                </xsl:when>
                            </xsl:choose>
                        </xsl:variable>
                        <tr style="{$rowStyle}">
                            <td>
                                <xsl:call-template name="string-replace-all">
                                    <xsl:with-param name="text" select="@dateTime" />
                                    <xsl:with-param name="replace" select="'T'" />
                                    <xsl:with-param name="by" select="' '" />
                                </xsl:call-template>
                            </td>
                            <td title="{client[@role='payer']/@id}"><xsl:value-of select="client[@role='payer']/@name"/></td>
                            <td title="{client[@role='recipient']/@id}"><xsl:value-of select="client[@role='recipient']/@name"/></td>
                            <td>
                                <xsl:value-of select="amount"/>
                                <xsl:choose>
                                    <xsl:when test="amount/@currency = 'dollars'"> $</xsl:when>
                                    <xsl:when test="amount/@currency = 'hryvnia'"> ₴</xsl:when>
                                    <xsl:when test="amount/@currency = 'ruble'"> ₽</xsl:when>
                                    <xsl:when test="amount/@currency = 'euros'"> €</xsl:when>
                                    <xsl:when test="amount/@currency = 'pounds'"> £</xsl:when>
                                </xsl:choose>
                            </td>
                            <td title="{Info/PError/additionalMsg}">
                                <xsl:if test="Info/status">
                                    <xsl:value-of select="Info/status"/>
                                </xsl:if>
                                <xsl:if test="Info/PError">
                                    Ошибка <xsl:value-of select="Info/PError/code"/>: <xsl:value-of select="Info/PError/shortMsg"/>
                                </xsl:if>
                            </td>
                        </tr>
                    </xsl:for-each>
                </table>
            </body>
        </html>
    </xsl:template>
</xsl:stylesheet>