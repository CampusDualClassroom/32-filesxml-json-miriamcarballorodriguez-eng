package com.campusdual.classroom;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import org.junit.platform.commons.function.Try;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Properties;

public class JSONFileCreator {

    public static void createDocument() {
        try {
            JsonObject root = new JsonObject();
            JsonArray itemsArray = new JsonArray();

            Object[][] data = {
                    {"Manzana", 2},
                    {"Leche", 1},
                    {"Pan", 3},
                    {"Huevo", 2},
                    {"Queso", 1},
                    {"Cereal", 1},
                    {"Agua", 4},
                    {"Yogur", 6},
                    {"Arroz", 2}
            };

            for (Object[] entry : data) {
                JsonObject item = new JsonObject();
                item.addProperty("text", entry[0].toString());
                item.addProperty("quantity", (int) entry[1]);
                itemsArray.add(item);
            }

            root.add("items", itemsArray);

            File file = new File("src/main/resources/shoppingList.json");
            file.getParentFile().mkdirs();
            Gson gson = new GsonBuilder().setPrettyPrinting().create();

            try (FileWriter writer = new FileWriter(file)) {
                gson.toJson(root, writer);
            }

        } catch (IOException e) {
            throw new RuntimeException("Error creating JSON file", e);
        }
    }
}