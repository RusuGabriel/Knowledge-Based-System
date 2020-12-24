package system.models;

import org.w3c.dom.Element;

public class Watch extends ElectronicDevice{


    public Watch(Element element) {
        setModel(element.getElementsByTagName("model").item(0).getTextContent());
        setBrand(element.getElementsByTagName("brand").item(0).getTextContent());
        setYear(Integer.parseInt(element.getElementsByTagName("year").item(0).getTextContent()));
    }
}
