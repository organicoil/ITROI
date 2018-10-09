package ua.nure.publisher.entity;

import static ua.nure.publisher.constants.ValueConstants.MAGAZINES_NAMESPACE_URI;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {"magazine"})
@XmlRootElement(name = "magazines", namespace = MAGAZINES_NAMESPACE_URI)
public class Magazines {

    @XmlElement(required = true, namespace = MAGAZINES_NAMESPACE_URI)
    private List<Magazine> magazine = new ArrayList<>();

    public List<Magazine> getMagazines() {
        return magazine;
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
