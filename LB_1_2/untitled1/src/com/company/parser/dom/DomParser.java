package com.company.parser.dom;

import com.company.entity.Currency;
import com.company.entity.Info;
import com.company.entity.PError;
import com.company.entity.Payment;
import com.company.entity.Status;
import com.company.entity.User;
import com.sun.org.apache.xerces.internal.parsers.DOMParser;
import com.company.entity.Payments;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class DomParser {

    public static Payments parse(String name) throws IOException, SAXException {
        Payments payments = new Payments();
        List<Payment> paymentList = new ArrayList<>();

        DOMParser parser = new DOMParser();
        parser.parse(name);
        Document doc = parser.getDocument();
        NodeList root = doc.getChildNodes();

        Node pmnts = DomUtils.getNode("payments", root);
        payments.setToDate(LocalDate.parse(DomUtils.getNodeAttr("toDate", pmnts)));
        payments.setFromDate(LocalDate.parse(DomUtils.getNodeAttr("fromDate", pmnts)));

        for (Node paymentNode : DomUtils.toList(pmnts.getChildNodes(), "payment")) {
            Payment payment = new Payment();
            payment.setId(Integer.parseInt(DomUtils.getNodeAttr("id", paymentNode)));
            payment.setDate(LocalDateTime.parse(DomUtils.getNodeAttr("dateTime", paymentNode)));

            setUsers(payment, paymentNode);
            setAmountAndCurrency(payment, paymentNode);
            Info info = new Info();
            payment.setInfo(info);

            setErrorToInfoIfPresent(info, DomUtils.getNode("Info", paymentNode.getChildNodes()));
            setStatusToInfoIfPresent(info,  DomUtils.getNode("Info", paymentNode.getChildNodes()));

            paymentList.add(payment);
        }

        payments.setPayments(paymentList);
        return payments;
    }

    private static void setStatusToInfoIfPresent(Info info, Node infoNode) {
        Node status = DomUtils.getNode("status", infoNode.getChildNodes());
        if (status != null) {
            info.setStatus(Status.fromString(DomUtils.getNodeValue(status)));
        }
    }

    private static void setUsers(Payment payment, Node paymentNode) {
        List<Node> users = DomUtils.toList(paymentNode.getChildNodes(), "client");

        Node payer = users.stream()
                .filter(n -> DomUtils.getNodeAttr("role", n).equalsIgnoreCase("payer"))
                .findFirst().get();
        Node recipient = users.stream()
                .filter(n -> DomUtils.getNodeAttr("role", n).equalsIgnoreCase("recipient"))
                .findFirst().get();
        payment.setRecipient(getUser(recipient));
        payment.setPayer(getUser(payer));
    }

    private static User getUser(Node node) {
        User user = new User();
        user.setId(Integer.parseInt(DomUtils.getNodeAttr("id", node)));
        user.setName(DomUtils.getNodeAttr("name", node));
        return user;
    }

    private static void setAmountAndCurrency(Payment payment, Node paymentNode) {
        Node amount = DomUtils.getNode("amount", paymentNode.getChildNodes());
        payment.setCurrency(Currency.fromString(DomUtils.getNodeAttr("currency", amount)));
        payment.setAmount(Double.parseDouble(DomUtils.getNodeValue(amount)));
    }

    private static void setErrorToInfoIfPresent(Info info, Node infoNode) {
        Node errorNode = DomUtils.getNode("PError", infoNode.getChildNodes());
        if (errorNode != null) {
            PError error = new PError();
            error.setCode(Integer.parseInt(DomUtils.getNodeValue("code", errorNode.getChildNodes())));
            error.setShortMsg(DomUtils.getNodeValue("shortMsg", errorNode.getChildNodes()));
            if (DomUtils.getNode("additionalMsg", errorNode.getChildNodes()) != null) {
                error.setAdditionalMsg(DomUtils.getNodeValue("additionalMsg", errorNode.getChildNodes()));
            }
            info.setPError(error);
            info.setStatus(Status.fromString("error"));
        }
    }
}
