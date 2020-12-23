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
        System.out.println(root.getNodeName());

        // Get all employees
        NodeList nList = document.getElementsByTagName("employee");
        System.out.println("============================");
    }

}
