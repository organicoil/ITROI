package ua.nure.journaldev.server;

import ua.nure.journaldev.server.service.impl.PersonServiceImpl;

import javax.xml.ws.Endpoint;

public class SOAPPublisher {

    public static void main(String[] args) {
        Endpoint.publish("http://localhost:8888/ws/person", new PersonServiceImpl());
    }

}