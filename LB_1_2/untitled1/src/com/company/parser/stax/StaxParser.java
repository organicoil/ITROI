package com.company.parser.stax;

import com.company.entity.Currency;
import com.company.entity.Info;
import com.company.entity.PError;
import com.company.entity.Payment;
import com.company.entity.Payments;
import com.company.entity.Status;
import com.company.entity.User;

import javax.xml.namespace.QName;
import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.Attribute;
import javax.xml.stream.events.EndElement;
import javax.xml.stream.events.StartElement;
import javax.xml.stream.events.XMLEvent;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class StaxParser {
    private static final String ATTR_ID = "id";
    private static final String ATTR_CURRENCY = "currency";
    private static final String ATTR_DATE_TIME = "dateTime";
    private static final String ATTR_ROLE = "role";
    private static final String ATTR_NAME = "name";

    private static final String PAYMENT = "payment";
    private static final String PAYMENTS = "payments";
    private static final String CLIENT = "client";
    private static final String CLIENT_PAYER = "payer";
    private static final String AMOUNT = "amount";
    private static final String INFO = "Info";
    private static final String STATUS = "status";
    private static final String ERROR = "PError";
    private static final String SHORT_ERROR_MSG = "shortMsg";
    private static final String FULL_ERROR_MSG = "additionalMsg";
    private static final String ERROR_CODE = "code";

    public static Payments parse(String name) {
        Payments payments = new Payments();
        List<Payment> paymentList = new ArrayList<>();
        Payment payment = new Payment();
        Info info = new Info();
        PError pError = new PError();
        XMLInputFactory xmlInputFactory = XMLInputFactory.newInstance();
        try {
            XMLEventReader xmlEventReader = xmlInputFactory.createXMLEventReader(new FileInputStream(name));
            while(xmlEventReader.hasNext()){
                XMLEvent xmlEvent = xmlEventReader.nextEvent();
                if (xmlEvent.isStartElement()){
                    StartElement startElement = xmlEvent.asStartElement();
                    switch (startElement.getName().getLocalPart()) {
                        case PAYMENTS:
                            payments.setFromDate(LocalDate.parse(getAttr(startElement, "fromDate")));
                            payments.setToDate(LocalDate.parse(getAttr(startElement, "toDate")));
                            break;
                        case PAYMENT:
                            payment = new Payment();
                            payment.setId(Integer.parseInt(getAttr(startElement, ATTR_ID)));
                            payment.setDate(LocalDateTime.parse(getAttr(startElement, ATTR_DATE_TIME)));
                            break;
                        case CLIENT:
                            User user = new User();
                            user.setId(Integer.parseInt(getAttr(startElement, ATTR_ID)));
                            user.setName(getAttr(startElement, ATTR_NAME));
                            if (getAttr(startElement, ATTR_ROLE).equals(CLIENT_PAYER)) {
                                payment.setPayer(user);
                            } else {
                                payment.setRecipient(user);
                            }
                            break;
                        case AMOUNT:
                            payment.setCurrency(Currency.fromString(getAttr(startElement, ATTR_CURRENCY)));
                            xmlEvent = xmlEventReader.nextEvent();
                            payment.setAmount(Double.parseDouble(xmlEvent.asCharacters().getData()));
                            break;
                        case INFO: {
                            info = new Info();
                            break;
                        }
                        case ERROR: {
                            info.setStatus(Status.fromString("error"));
                            pError = new PError();
                            break;
                        }
                        case ERROR_CODE: {
                            xmlEvent = xmlEventReader.nextEvent();
                            pError.setCode(Integer.parseInt(xmlEvent.asCharacters().getData()));
                            break;
                        }
                        case SHORT_ERROR_MSG: {
                            xmlEvent = xmlEventReader.nextEvent();
                            pError.setShortMsg(xmlEvent.asCharacters().getData());
                            break;
                        }
                        case FULL_ERROR_MSG: {
                            xmlEvent = xmlEventReader.nextEvent();
                            pError.setAdditionalMsg(xmlEvent.asCharacters().getData());
                            break;
                        }
                    }
                }

                if(xmlEvent.isEndElement()){
                    EndElement endElement = xmlEvent.asEndElement();
                    switch (endElement.getName().getLocalPart()) {
                        case PAYMENT: {
                            paymentList.add(payment);
                            break;
                        }
                        case INFO: {
                            payment.setInfo(info);
                            break;
                        }
                        case ERROR: {
                            info.setPError(pError);
                        }
                    }
                }
            }

        } catch (FileNotFoundException | XMLStreamException e) {
            e.printStackTrace();
        }
        payments.setPayments(paymentList);
        return payments;
    }

    private static String getAttr(StartElement e, String name) {
        return e.getAttributeByName(new QName(name)).getValue();
    }
}
