package com.company.parser.sax;

import com.company.entity.Currency;
import com.company.entity.Info;
import com.company.entity.PError;
import com.company.entity.Payment;
import com.company.entity.Payments;
import com.company.entity.Status;
import com.company.entity.User;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Handler extends DefaultHandler {
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

    private Payments payments;
    private List<Payment> paymentList = new ArrayList<>();

    private Payment payment;
    private Info info;
    private PError error;

    private boolean isCode = false;
    private boolean isShortMsg = false;
    private boolean isFullMsg = false;
    private boolean isAmount = false;
    private boolean isStatus = false;

    public Payments getPayments() {
        payments.setPayments(paymentList);
        return payments;
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        switch (qName) {
            case PAYMENTS: {
                payments = new Payments();
                payments.setFromDate(LocalDate.parse(attributes.getValue("fromDate")));
                payments.setToDate(LocalDate.parse(attributes.getValue("toDate")));
                break;
            }
            case PAYMENT: {
                payment = new Payment();
                payment.setId(Integer.parseInt(attributes.getValue(ATTR_ID)));
                payment.setDate(LocalDateTime.parse(attributes.getValue(ATTR_DATE_TIME)));
                break;
            }
            case CLIENT: {
                User user = new User();
                user.setId(Integer.parseInt(attributes.getValue(ATTR_ID)));
                user.setName(attributes.getValue(ATTR_NAME));
                if (attributes.getValue(ATTR_ROLE).equals(CLIENT_PAYER)) {
                    payment.setPayer(user);
                } else {
                    payment.setRecipient(user);
                }
                break;
            }
            case AMOUNT: {
                payment.setCurrency(Currency.fromString(attributes.getValue(ATTR_CURRENCY)));
                isAmount = true;
                break;
            }
            case INFO: {
                info = new Info();
                break;
            }
            case STATUS: {
                isStatus = true;
                break;
            }
            case ERROR: {
                info.setStatus(Status.fromString("error"));
                error = new PError();
                break;
            }
            case ERROR_CODE: {
                isCode = true;
                break;
            }
            case SHORT_ERROR_MSG: {
                isShortMsg = true;
                break;
            }
            case FULL_ERROR_MSG: {
                isFullMsg = true;
                break;
            }
        }
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        switch (qName) {
            case PAYMENT: {
                paymentList.add(payment);
                break;
            }
            case INFO: {
                payment.setInfo(info);
                break;
            }
            case ERROR: {
                info.setPError(error);
            }

        }
    }

    @Override
    public void characters(char ch[], int start, int length) throws SAXException {
        if (isAmount) {
            payment.setAmount(Double.parseDouble(new String(ch, start, length)));
            isAmount = false;
        }
        if (isStatus) {
            info.setStatus(Status.fromString(new String(ch, start, length)));
            isStatus = false;
        }
        if (isFullMsg) {
            error.setAdditionalMsg(new String(ch, start, length));
            isFullMsg = false;
        }
        if (isShortMsg) {
            error.setShortMsg(new String(ch, start, length));
            isShortMsg = false;
        }
        if (isCode) {
            error.setCode(Integer.parseInt(new String(ch, start, length)));
            isCode = false;
        }
    }
}
