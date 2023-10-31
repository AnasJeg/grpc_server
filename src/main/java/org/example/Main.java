package org.example;

import org.example.connection.Connect;

public class Main {
    public static void main(String[] args) {
        Connect.getConnection();
        System.out.println("Hello world!");
    }
}