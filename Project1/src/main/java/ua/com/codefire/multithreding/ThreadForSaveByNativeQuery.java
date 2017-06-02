package ua.com.codefire.multithreding;

import org.apache.commons.csv.CSVRecord;
import ua.com.codefire.dao.StubDao;

import java.util.List;

import static ua.com.codefire.dao.impl.StubDaoImpl.getInstance;

/**
 * Created by gleb on 01.06.17.
 */
public class ThreadForSaveByNativeQuery extends ThreadForSave {
    public ThreadForSaveByNativeQuery(List<CSVRecord> records, int start, int end) {
        super(records, start, end);
    }

    @Override
    public void run() {
        System.out.println("Run thread start = " + start + ", end = " + end);
        StubDao stubDao = getInstance();
        for (int i = start; i < end; i++) {
            CSVRecord record = records.get(i);
            stubDao.saveStubByNativeQuery(record.get(0), Integer.valueOf(record.get(1)));
        }
    }
}
