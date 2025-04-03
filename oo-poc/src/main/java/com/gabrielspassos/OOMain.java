package com.gabrielspassos;

import com.gabrielspassos.media.JsonMedia;
import com.gabrielspassos.media.Media;
import com.gabrielspassos.media.XmlMedia;

public class OOMain {

    public static void main(String[] args) {
        // Create a new MyCustomDocument instance
        MyCustomDocument document = new MyCustomDocument("Hello, World!");

        Media jsonMedia = new JsonMedia();
        Media xmlMedia = new XmlMedia();

        // Convert the document content to JSON format
        String jsonOutput = document.contentToMedia(jsonMedia);

        // Print the JSON output
        System.out.println(jsonOutput);

        // Convert the document content to XML format
        String xmlOutput = document.contentToMedia(xmlMedia);

        // Print the XML output
        System.out.println(xmlOutput);
    }
}
