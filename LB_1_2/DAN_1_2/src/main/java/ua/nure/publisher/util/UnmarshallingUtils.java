package ua.nure.publisher.util;

import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.util.ArrayList;
import java.util.List;

public class UnmarshallingUtils {

    public static String getValue(Element parent, String nameSpace, String nodeName) {
        return getValues(parent, nameSpace, nodeName).get(0);
    }

    public static List<String> getValues(Element parent, String nameSpace, String nodeName) {
        List<String> values = new ArrayList<>();
        NodeList elements = parent.getElementsByTagNameNS(nameSpace, nodeName);
        for (int i = 0; i < elements.getLength(); i++) {
            Node node = elements.item(i);
            if (node != null) {
                values.add(node.getTextContent());
            } else {
                values.add("");
            }
        }
        return values;
    }


}
