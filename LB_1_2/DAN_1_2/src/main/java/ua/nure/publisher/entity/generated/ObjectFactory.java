
package ua.nure.publisher.entity.generated;

import javax.xml.bind.annotation.XmlRegistry;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the ua.nure.publisher.entity.generated package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {


    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: ua.nure.publisher.entity.generated
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link Magazines }
     * 
     */
    public Magazines createMagazines() {
        return new Magazines();
    }

    /**
     * Create an instance of {@link Magazine }
     * 
     */
    public Magazine createMagazine() {
        return new Magazine();
    }

    /**
     * Create an instance of {@link Publishing }
     * 
     */
    public Publishing createPublishing() {
        return new Publishing();
    }

    /**
     * Create an instance of {@link Entity }
     * 
     */
    public Entity createEntity() {
        return new Entity();
    }

}
