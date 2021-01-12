package system.models;
import org.w3c.dom.Element;

public class ElectronicDevice {
    private String brand;
    private String model;
    private Integer year;
    private Double price;

    ElectronicDevice(Element element) {
        setModel(element.getElementsByTagName("model").item(0).getTextContent());
        setBrand(element.getElementsByTagName("brand").item(0).getTextContent());
        setYear(Integer.parseInt(element.getElementsByTagName("year").item(0).getTextContent()));
        setPrice(Double.parseDouble(element.getElementsByTagName("price").item(0).getTextContent()));
    }

    public String getBrand() {
        return brand;
    }

    public String getModel() {
        return model;
    }

    public Integer getYear() {
        return year;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }


}
