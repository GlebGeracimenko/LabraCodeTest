package ua.com.codefire.core;

import org.apache.commons.csv.CSVRecord;
import ua.com.codefire.dao.StubDao;

import java.util.List;

import static ua.com.codefire.dao.impl.StubDaoImpl.getInstance;

/**
 * Created by gleb on 01.06.17.
 */
public class Saver2 extends Thread {
    private List<CSVRecord> records;
    private int start;
    private int end;
    private StubDao stubDao;

    public Saver2(List<CSVRecord> records, int start, int end) {
        this.records = records;
        this.start = start;
        this.end = end;
        this.stubDao = getInstance();
    }

    @Override
    public void run() {
        System.out.println("Run thread start = " + start + ", end = " + end);
        for (int i = start; i < end; i++) {
            CSVRecord record = records.get(i);
            stubDao.saveStub2(record.get(0), Integer.valueOf(record.get(1)));
        }
    }
}
