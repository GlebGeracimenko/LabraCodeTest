package ua.com.codefire.parser;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by gleb on 31.05.17.
 */
public class CSVReader {
    public List<CSVRecord> getRecords() {
        File file = new File("stub.csv");
        try {
            CSVParser csvParser = new CSVParser(new FileReader(file), CSVFormat.RFC4180);
            return csvParser.getRecords();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new ArrayList<>();
    }
}
