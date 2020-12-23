package system.models;

import java.util.ArrayList;
import org.w3c.dom.*;


import javax.xml.parsers.*;
import java.io.*;
import org.xml.sax.*;

public class KnowledgeStore {
    private static KnowledgeStore instance = null;
    private ArrayList<Phone> phoneStore = null;

    private KnowledgeStore() {
    }

    public static KnowledgeStore getInstance() {
        if (instance == null) {
            synchronized (KnowledgeStore.class) {
                if (instance == null)
                    instance = new KnowledgeStore();
            }
        }
        return instance;
    }

    public void loadKnowledge() throws ParserConfigurationException, SAXException, IOException {
        phoneStore = new ArrayList<>();
        // Get Document Builder
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();

        // Build Document
        Document document = builder.parse(new File(getClass().getResource("/data/knowledge.xml").getPath()));

        // Normalize the XML Structure; It's just too important !!
        document.getDocumentElement().normalize();

        // Here comes the root nodeF
        Element root = document.getDocumentElement();

        // Get all employees
        NodeList nList = document.getElementsByTagName("phone");
        System.out.println("============================");
        for (int index = 0; index < nList.getLength(); index++) {
            Node phoneNode = nList.item(index);
            if (phoneNode.getNodeType() == Node.ELEMENT_NODE) {
                Element phoneElement = (Element) phoneNode;
                
    System.out.println("Model : "  + phoneElement.getElementsByTagName("model").item(0).getTextContent());
    System.out.println("Last Name : "   + phoneElement.getElementsByTagName("brand").item(0).getTextContent());
    System.out.println("Location : "    + phoneElement.getElementsByTagName("year").item(0).getTextContent());
            }
        }
    }

}
