
package ua.nure.publisher.dto;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * <p>Java class for anonymous complex type.
 *
 * <p>The following schema fragment specifies the expected content contained within this class.
 *
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="magazine" type="{http://ua.nure/magazines/}magazine" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
        "magazine"
})
@XmlRootElement(name = "magazines", namespace = "http://ua.nure/magazines/")
public class Magazines {

    @XmlElement(namespace = "http://ua.nure/magazines/")
    protected List<Magazine> magazine = new ArrayList<>();

    /**
     * Gets the value of the magazine property.
     *
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the magazine property.
     *
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getMagazine().add(newItem);
     * </pre>
     *
     *
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Magazine }
     */
    public List<Magazine> getMagazines() {
        if (magazine == null) {
            magazine = new ArrayList<>();
        }
        return this.magazine;
    }

    public void add(Magazine magazine) {
        this.magazine.add(magazine);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Magazines)) {
            return false;
        }
        Magazines magazines = (Magazines) o;
        return Objects.equals(magazine, magazines.magazine);
    }

    @Override
    public int hashCode() {
        return Objects.hash(magazine);
    }

    @Override
    public String toString() {
        return "Magazines{" +
                "magazine=" + magazine +
                '}';
    }
}
