<?xml version="1.0" encoding="UTF-8"?>

<xsl:stylesheet version="1.0"
	xmlns:xsl="http://www.w3.org/1999/XSL/Transform">

<xsl:template match="/books">
  <html>
  <body>  
    <table border="1px" cellpadding="0" cellspacing="0">
      <tr>
        <th>#</th>
		<th>Название</th>
        <th>Автор</th>
		<th>Цена</th>
		<th>Количество</th>
      </tr>
		<xsl:apply-templates/> 
    </table>
  </body>
  </html>
</xsl:template>

<xsl:template match="book">
<tr>

          <td><xsl:value-of select="@id"/></td>
          <td><xsl:value-of select="title"/></td>
		  <td><xsl:value-of select="author"/></td>
		  <td><xsl:value-of select="price"/></td>
		  <td><xsl:value-of select="count"/></td>
		  </tr>

      </xsl:template>

</xsl:stylesheet>