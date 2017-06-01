package ua.com.codefire.core;

import org.apache.commons.csv.CSVRecord;
import ua.com.codefire.parser.CSVReader;

import java.util.List;

/**
 * Created by gleb on 01.06.17.
 */
public class Main2 {
    public static void main(String[] args) throws InterruptedException {
        List<CSVRecord> records = new CSVReader().getRecords();
        int poolSize = 10;
        int part = records.size() / poolSize;
        int start = 0;
        int end = part;
        for (int i = 0; i < poolSize; i++) {
            Saver2 saver = new Saver2(records, start, end);
            start = end;
            end += part;
            saver.start();
        }
    }
}
