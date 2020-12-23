package system.models;

import org.w3c.dom.Element;

public class Tablet {
    private String brand;
    private String model;
    private Integer year;
    private Double inches;

    public Tablet(Element element) {
        model = element.getElementsByTagName("model").item(0).getTextContent();
        brand = element.getElementsByTagName("brand").item(0).getTextContent();
        year = Integer.parseInt(element.getElementsByTagName("year").item(0).getTextContent());
        inches = Double.parseDouble(element.getElementsByTagName("inches").item(0).getTextContent());
    }
}
