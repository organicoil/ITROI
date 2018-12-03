package ua.nure.publisher.client.dto;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;

/**
 * This object contains factory methods for each
 * Java content interface and Java element interface
 * generated in the ua.nure.publisher.client package.
 * <p>An ObjectFactory allows you to programatically
 * construct new instances of the Java representation
 * for XML content. The Java representation of XML
 * content can consist of schema derived interfaces
 * and classes representing the binding of schema
 * type definitions, element declarations and model
 * groups.  Factory methods for each of these are
 * provided in this class.
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _Magazine_QNAME = new QName("http://ua.nure/magazines/", "magazine");
    private final static QName _Magazines_QNAME = new QName("http://ua.nure/magazines/", "magazines");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: ua.nure.publisher.client
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link Magazines }
     */
    public Magazines createMagazines() {
        return new Magazines();
    }

    /**
     * Create an instance of {@link Magazine }
     */
    public Magazine createMagazine() {
        return new Magazine();
    }

    /**
     * Create an instance of {@link Publishing }
     */
    public Publishing createPublishing() {
        return new Publishing();
    }

    /**
     * Create an instance of {@link Entity }
     */
    public Entity createEntity() {
        return new Entity();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Magazine }{@code >}}
     */
    @XmlElementDecl(namespace = "http://ua.nure/magazines/", name = "magazine")
    public JAXBElement<Magazine> createMagazine(Magazine value) {
        return new JAXBElement<Magazine>(_Magazine_QNAME, Magazine.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Magazines }{@code >}}
     */
    @XmlElementDecl(namespace = "http://ua.nure/magazines/", name = "magazines")
    public JAXBElement<Magazines> createMagazines(Magazines value) {
        return new JAXBElement<Magazines>(_Magazines_QNAME, Magazines.class, null, value);
    }

}
