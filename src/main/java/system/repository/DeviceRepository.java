package system.repository;

import java.util.*;
import org.w3c.dom.*;
import java.io.*;
import org.xml.sax.*;
import system.utils.*;
import system.models.*;
import javax.xml.parsers.*;

public class DeviceRepository {
    private static DeviceRepository instance = null;
    private ArrayList<Phone> phoneStore = null;
    private ArrayList<Tablet> tabletStore = null;
    private ArrayList<Watch> watchStore = null;

    private Document document = null;

    private DeviceRepository() {
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();

            this.document = builder.parse(new File(getClass().getResource("/data/knowledge.xml").getPath()));
            this.document.getDocumentElement().normalize();
        } catch (Exception exception) {
            System.out.println(exception.getMessage());
            System.exit(-1);
        }

    }

    public static DeviceRepository getInstance() {
        if (instance == null) {
            synchronized (DeviceRepository.class) {
                if (instance == null)
                    instance = new DeviceRepository();
            }
        }
        return instance;
    }

    public void parseData() {
        phoneStore = new ArrayList<>();
        tabletStore = new ArrayList<>();
        watchStore = new ArrayList<>();
        // Get all phones
        parse(document, Constants.Type.PHONE);
        // Get all tablets
        parse(document, Constants.Type.TABLET);
        // Get all watches
        parse(document, Constants.Type.WATCH);
    }

    private void parse(Document document, String asType) {
        NodeList nodes = document.getElementsByTagName(asType);
        for (int index = 0; index < nodes.getLength(); index++) {
            Node node = nodes.item(index);
            if (node.getNodeType() == Node.ELEMENT_NODE) {
                Element element = (Element) node;
                store(element, asType);
            }
        }
    }

    private void store(Element element, String asType) {
        switch (asType) {
            case Constants.Type.PHONE:
                phoneStore.add(new Phone(element));
                break;

            case Constants.Type.TABLET:
                tabletStore.add(new Tablet(element));
                break;

            case Constants.Type.WATCH:
                watchStore.add(new Watch(element));
                break;

            default:
                break;
        }
    }

    public ArrayList<String> getAllBrands() {
        Set<String> brands = new HashSet<>();

        for (ElectronicDevice device : phoneStore)
            brands.add(device.getBrand());

        for (ElectronicDevice device : watchStore)
            brands.add(device.getBrand());

        for (ElectronicDevice device : tabletStore)
            brands.add(device.getBrand());
        brands.add("All");

        return new ArrayList<>(brands);
    }

    public ArrayList<ElectronicDevice> getAll() {
        ArrayList<ElectronicDevice> allDevices = new ArrayList<>();
        allDevices.addAll(phoneStore);
        allDevices.addAll(tabletStore);
        allDevices.addAll(watchStore);

        return allDevices;
    }

    public ArrayList<String> getAllTypes() {
        var result = new ArrayList<String>();
        result.add("All");
        result.add(Constants.Type.PHONES);
        result.add(Constants.Type.TABLETS);
        result.add(Constants.Type.WATCHES);
        return result;
    }

    public void reloadData() {
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();

            this.document = builder.parse(new File(getClass().getResource("/data/knowledge.xml").getPath()));
            this.document.getDocumentElement().normalize();
        } catch (Exception exception) {
            System.out.println(exception.getMessage());
            System.exit(-1);
        }
        phoneStore = new ArrayList<>();
        tabletStore = new ArrayList<>();
        watchStore = new ArrayList<>();
        // Get all phones
        parse(document, Constants.Type.PHONE);
        // Get all tablets
        parse(document, Constants.Type.TABLET);
        // Get all watches
        parse(document, Constants.Type.WATCH);
    }

}
