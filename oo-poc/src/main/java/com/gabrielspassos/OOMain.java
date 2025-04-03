package com.gabrielspassos;

public class OOMain {

    public static void main(String[] args) {
        // Create a new MyCustomDocument instance
        MyCustomDocument document = new MyCustomDocument("Hello, World!");

        // Create a new JsonMedia instance
        Media media = new JsonMedia();

        // Convert the document content to JSON format
        String jsonOutput = document.contentToMedia(media);

        // Print the JSON output
        System.out.println(jsonOutput);
    }
}
