package system.models;

import org.w3c.dom.Element;

public class Phone {
    private String model;
    private String brand;
    private Integer year;

    public Phone(Element element) {
        model = element.getElementsByTagName("model").item(0).getTextContent();
        brand = element.getElementsByTagName("brand").item(0).getTextContent();
        year = Integer.parseInt(element.getElementsByTagName("year").item(0).getTextContent());
    }
}