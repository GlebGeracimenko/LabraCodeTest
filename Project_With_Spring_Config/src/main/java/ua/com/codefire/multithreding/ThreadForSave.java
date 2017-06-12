package ua.com.codefire.multithreding;

import org.apache.commons.csv.CSVRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import ua.com.codefire.dao.StubDao;
import ua.com.codefire.dao.entity.StubEntity;

import java.util.List;

import static org.springframework.beans.factory.config.ConfigurableBeanFactory.SCOPE_PROTOTYPE;

/**
 * Created by gleb on 31.05.17.
 */
@Component
@Scope(SCOPE_PROTOTYPE)
public class ThreadForSave extends Thread {

    @Autowired
    private StubDao stubDao;

    @Autowired
    private List<CSVRecord> records;
    protected int start;
    protected int end;

    public ThreadForSave(int start, int end) {
        this.start = start;
        this.end = end;
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
