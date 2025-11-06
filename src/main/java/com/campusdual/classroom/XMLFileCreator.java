package com.campusdual.classroom;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;

public class XMLFileCreator {

    public static void createDocument() {
        try {
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder();
            Document doc = db.newDocument();

            Element root = doc.createElement("shoppinglist");
            doc.appendChild(root);

            Element items = doc.createElement("items");
            root.appendChild(items);

            Object[][] data = {
                    { "Manzana", 2 },
                    { "Leche", 1 },
                    { "Pan", 3 },
                    { "Huevo", 2 },
                    { "Queso", 1 },
                    { "Cereal", 1 },
                    { "Agua", 4 },
                    { "Yogur", 6 },
                    { "Arroz", 2 }
            };

            for (Object[] entry : data) {
                Element item = doc.createElement("item");
                item.setAttribute("quantity", entry[1].toString());
                item.setTextContent(entry[0].toString());
                items.appendChild(item);
            }

            File file = new File("src/main/resources/shoppingList.xml");
            file.getParentFile().mkdirs();

            Transformer transformer = TransformerFactory.newInstance().newTransformer();

            transformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
            transformer.setOutputProperty(OutputKeys.STANDALONE, "no");
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");

            transformer.transform(new DOMSource(doc), new StreamResult(file));

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}