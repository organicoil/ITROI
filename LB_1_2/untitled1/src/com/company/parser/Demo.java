package com.company.parser;

import com.company.entity.Payment;
import com.company.entity.Payments;
import com.company.parser.dom.DomParser;
import com.company.parser.sax.Handler;
import com.company.parser.stax.StaxParser;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.File;
import java.io.IOException;

public class Demo {
    public static void main(String[] args) throws IOException, SAXException {
        System.out.println("SAX PARSER");
        sax();

        System.out.println();
        System.out.println("STAX PARSER");
        stax();

        System.out.println();
        System.out.println("STAX PARSER");
        dom();
    }

    private static void dom() throws IOException, SAXException {
        Payments payments = DomParser.parse("D:\\intellij idea\\projects\\untitled1\\xml\\payments.xml");
        for(Payment p : payments.getPayments())
            System.out.println(p);
    }

    private static void stax() {
        Payments payments = StaxParser.parse("D:\\intellij idea\\projects\\untitled1\\xml\\payments.xml");
        for(Payment p : payments.getPayments())
            System.out.println(p);
    }

    private static void sax() {
        SAXParserFactory saxParserFactory = SAXParserFactory.newInstance();
        try {
            SAXParser saxParser = saxParserFactory.newSAXParser();
            Handler handler = new Handler();
            saxParser.parse(new File("D:\\intellij idea\\projects\\untitled1\\xml\\payments.xml"), handler);
            Payments payments = handler.getPayments();
            for(Payment p : payments.getPayments())
                System.out.println(p);
        } catch (ParserConfigurationException | SAXException | IOException e) {
            e.printStackTrace();
        }
    }
}
