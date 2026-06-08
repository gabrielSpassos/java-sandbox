package com.gabrielspassos;

import com.gabrielspassos.hash.CustomHashTable;
import com.gabrielspassos.service.HashService;

public class Main {
    static void main() {
        IO.println("Hash POC!");
        var hashService = new HashService();
        var hashTable = new CustomHashTable<String, Integer>();

        IO.println("Basic hash of hello: " + hashService.basicHash("hello"));
        IO.println("DJB2 hash of hello: " + hashService.djb2Hash("hello"));

        IO.println("Add foo at hash table: " + hashTable.put("foo", 1));
        IO.println("Add bar at hash table: " + hashTable.put("bar", 2));
        IO.println("Get bar from hash table: " + hashTable.get("bar"));
        IO.println("Get foo from hash table: " + hashTable.get("foo"));
        IO.println("Get tar from hash table: " + hashTable.get("tar"));
        IO.println("Remove foo from hash table: " + hashTable.remove("foo"));
        IO.println("Remove tar from hash table: " + hashTable.remove("tar"));
    }
}
