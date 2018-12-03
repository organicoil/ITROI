package ua.nure.publisher.server;

import ua.nure.publisher.server.service.impl.MagazinesServiceImpl;

import javax.xml.ws.Endpoint;

public class SoapServer {

    public static void main(String[] args) {
        Endpoint.publish("http://localhost:8888/soap/publisher", new MagazinesServiceImpl());
    }

}