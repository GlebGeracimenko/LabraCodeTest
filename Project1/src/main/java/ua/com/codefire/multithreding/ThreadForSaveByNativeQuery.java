package ua.com.codefire.multithreding;

import org.apache.commons.csv.CSVRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import ua.com.codefire.dao.StubDao;

import java.util.List;

import static org.springframework.beans.factory.config.ConfigurableBeanFactory.SCOPE_PROTOTYPE;

/**
 * Created by gleb on 01.06.17.
 */
@Component
@Scope(SCOPE_PROTOTYPE)
public class ThreadForSaveByNativeQuery extends ThreadForSave {

    @Autowired
    private StubDao stubDao;

    @Autowired
    private List<CSVRecord> records;

//    public ThreadForSaveByNativeQuery() {
//    }

    public ThreadForSaveByNativeQuery(int start, int end) {
        super(start, end);
    }

    @Override
    public void run() {
        System.out.println("Run thread start = " + start + ", end = " + end);
        for (int i = start; i < end; i++) {
            CSVRecord record = records.get(i);
            stubDao.saveStubByNativeQuery(record.get(0), Integer.valueOf(record.get(1)));
        }
    }
}
