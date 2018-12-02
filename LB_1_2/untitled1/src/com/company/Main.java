package com.company;

import javax.xml.transform.TransformerException;
import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException, TransformerException, ClassNotFoundException {
        String fileName = "D:\\intellij idea\\projects\\untitled1\\xml\\payments.xml";
        String xslFileName = "D:\\intellij idea\\projects\\untitled1\\xml\\payments.xsl";
        String output = "D:\\intellij idea\\projects\\untitled1\\xml\\payments.html";
        XslApplier.applyXsl(fileName, output, xslFileName);
    }
}
