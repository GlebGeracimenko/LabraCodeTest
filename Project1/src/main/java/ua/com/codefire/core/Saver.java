package ua.com.codefire.core;

import org.apache.commons.csv.CSVRecord;
import ua.com.codefire.dao.StubDao;
import ua.com.codefire.dao.entity.StubEntity;

import java.util.List;

import static ua.com.codefire.dao.impl.StubDaoImpl.getInstance;

/**
 * Created by gleb on 31.05.17.
 */
public class Saver extends Thread {

    private List<CSVRecord> records;
    private int start;
    private int end;
    private StubDao stubDao;

    public Saver(List<CSVRecord> records, int start, int end) {
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
            StubEntity stubEntity = new StubEntity();
            stubEntity.setStubValue(record.get(0));
            stubEntity.setFirstValue(Integer.valueOf(record.get(1)));
            stubEntity.setSecondValue(Integer.valueOf(record.get(2)));
            stubDao.saveStub(stubEntity);
        }
    }
}
