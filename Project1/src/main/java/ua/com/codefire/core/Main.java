package ua.com.codefire.core;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import ua.com.codefire.configuration.SpringConfigs;
import ua.com.codefire.multithreding.ThreadForSave;

/**
 * Created by gleb on 31.05.17.
 */
public class Main {

    public static void main(String[] args) throws InterruptedException {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        applicationContext.register(SpringConfigs.class);
        applicationContext.refresh();

        int poolSize = 10;
        int part = applicationContext.getBean("sizeRecords", Integer.class) / poolSize;
        int start = 0;
        int end = part;
        for (int i = 0; i < poolSize; i++) {
            ThreadForSave threadForSave = (ThreadForSave) applicationContext.getBean("threadForSave", start, end);
            start = end;
            end += part;
            threadForSave.start();
        }
    }
}
