package ua.nure.publisher.client;

import ua.nure.publisher.server.dto.Category;
import ua.nure.publisher.server.dto.Magazine;
import ua.nure.publisher.server.dto.Publishing;
import ua.nure.publisher.server.service.MagazinesService;

import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import java.net.MalformedURLException;
import java.net.URL;

public class SoapClient {

    private static Magazine magazine1 = getMagazineTemplate(1);
    private static Magazine magazine2 = getMagazineTemplate(2);

    public static void main(String[] args) throws MalformedURLException {
        URL url = new URL("http://localhost:8888/soap/publisher?wsdl");
        QName qname = new QName("http://impl.service.server.publisher.nure.ua/", "MagazinesServiceImplService");
        Service service = Service.create(url, qname);
        MagazinesService magazinesService = service.getPort(MagazinesService.class);

        magazinesService.addMagazine(magazine1);
        magazinesService.addMagazine(magazine2);
        System.out.println("Magazines: " + magazinesService.getMagazines());
        System.out.println("Magazine with id == 1: " + magazinesService.getMagazineById(1));
        magazinesService.removeMagazineById(1);
        System.out.println("Magazines after delete: " + magazinesService.getMagazines());
        magazinesService.clearMagazines();
        System.out.println("Magazines after clear: " + magazinesService.getMagazines());
    }

    private static Magazine getMagazineTemplate(int id) {
        Magazine template = new Magazine();
        template.setId(id);
        template.setCategory(Category.SPORT);
        template.setDescription("-");
        template.setPrice(99.0);
        template.setTitle("template");
        template.setPublishing(getPublishingTemplate());
        return template;
    }

    private static Publishing getPublishingTemplate() {
        Publishing publishing = new Publishing();
        publishing.setPerMonthPublishCount(1);
        return publishing;
    }

}