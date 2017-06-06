package ua.com.codefire.core;

import org.apache.commons.csv.CSVRecord;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import ua.com.codefire.configuration.AppConfig;
import ua.com.codefire.multithreding.ThreadForSaveByNativeQuery;
import ua.com.codefire.parser.CSVReader;

import java.util.List;

/**
 * Created by gleb on 01.06.17.
 */
public class Main4 {
    public static void main(String[] args) throws InterruptedException {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        applicationContext.register(AppConfig.class);
        applicationContext.refresh();
        List<CSVRecord> records = new CSVReader().getRecords();
        int poolSize = 10;
        int part = records.size() / poolSize;
        int start = 0;
        int end = part;
        for (int i = 0; i < poolSize; i++) {
            ThreadForSaveByNativeQuery thread = (ThreadForSaveByNativeQuery) applicationContext.getBean("threadForSaveByNativeQuery", start, end);
            start = end;
            end += part;
            thread.start();
        }
    }
}
