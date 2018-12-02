package ua.nure.publisher.converter;

import org.apache.log4j.Logger;
import ua.nure.publisher.parser.stax.StaxMagazinesMarshallerImpl;

import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.TransformerFactoryConfigurationError;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import java.io.FileWriter;
import java.io.IOException;
import java.io.StringWriter;

public class XmlToHtmlConverter {

    private static final Logger LOG = Logger.getLogger(StaxMagazinesMarshallerImpl.class);

    public void convertXMLToHTML(String xmlPath, String xsltPath, String htmlOutputPath) {
        try (FileWriter fileWriter = new FileWriter(htmlOutputPath)) {
            StringWriter stringWriter = new StringWriter();
            Source xmlSource = new StreamSource(xmlPath);
            Source xsltSource = new StreamSource(xsltPath);

            TransformerFactory tFactory = TransformerFactory.newInstance();
            Transformer transform = tFactory.newTransformer(xsltSource);
            transform.transform(xmlSource, new StreamResult(stringWriter));
            fileWriter.write(stringWriter.toString());
        } catch (IOException | TransformerFactoryConfigurationError | TransformerException e) {
            LOG.error("Failed to convert xml to html", e);
        }
    }
}
