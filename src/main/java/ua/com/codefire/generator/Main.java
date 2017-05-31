/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ua.com.codefire.generator;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Random;

/**
 *
 * @author Yevhenii Rodin
 */
public class Main {

    private static final Random RND = new Random();
    
    private static final String FILE_NAME = "stub.csv";
    private static final int LIMIT = 3_000_000;

    public static void main(String[] args) throws IOException {
        try (PrintWriter writer = new PrintWriter(new BufferedOutputStream(new FileOutputStream(FILE_NAME)))) {
            for (int i = 0; i < LIMIT; i++) {
                String line = getDataLine();
                int firstValue = getDataValue();
                int secondvalue = getDataValue();
                writer.printf("%s,%d,%d\n", line, firstValue, secondvalue);
            }
        }
    }

    private static String getDataLine() {
        StringBuilder builder = new StringBuilder();
        int letters = RND.nextInt(12) + 4;
        for (int i = 0; i < letters; i++) {
            builder.append((char) (RND.nextInt(26) + 65));
        }
        return builder.toString();
    }

    private static int getDataValue() {
        return RND.nextInt(256);
    }
}
