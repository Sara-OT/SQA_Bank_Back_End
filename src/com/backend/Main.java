package com.backend;

import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {
        Bank testing = new Bank();
        testing.readAccounts("test.txt");
        testing.exportNewAccounts();
    }
}
