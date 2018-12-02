package com.company;

import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Templates;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class XslApplier {
    public static void applyXsl(String xmlFileName, String outputFileName, String xslFileName)
            throws IOException, TransformerException {
        File output = new File(outputFileName);
        if (!output.exists()) {
            output.createNewFile();
        }
        TransformerFactory factory = TransformerFactory.newInstance();
        Templates template = factory.newTemplates(new StreamSource(new FileInputStream(xslFileName)));
        Transformer xformer = template.newTransformer();
        Source source = new StreamSource(new FileInputStream(xmlFileName));
        Result result = new StreamResult(new FileOutputStream(outputFileName));
        xformer.transform(source, result);
    }
}
