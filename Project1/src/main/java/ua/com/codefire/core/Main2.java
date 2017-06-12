package ua.com.codefire.core;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import ua.com.codefire.configuration.SpringConfigs;
import ua.com.codefire.multithreding.ThreadForSaveByNativeQuery;

/**
 * Created by gleb on 01.06.17.
 */
public class Main2 {
    public static void main(String[] args) throws InterruptedException {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        applicationContext.register(SpringConfigs.class);
        applicationContext.refresh();
        int poolSize = 10;
        int part = applicationContext.getBean("sizeRecords", Integer.class) / poolSize;
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
