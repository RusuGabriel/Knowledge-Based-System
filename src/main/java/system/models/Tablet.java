package system.models;

import org.w3c.dom.Element;

public class Tablet extends ElectronicDevice {
    private Double inches;

    public Tablet(Element element) {
        super(element);
        inches = Double.parseDouble(element.getElementsByTagName("inches").item(0).getTextContent());
    }
}
