package ua.com.codefire.configuration;

import org.apache.commons.csv.CSVRecord;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import ua.com.codefire.parser.CSVReader;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;

/**
 * Created by gleb on 03.06.17.
 */
@Configuration
@ComponentScan("ua.com.codefire")
public class SpringConfigs {

    @Bean
    public EntityManagerFactory entityManagerFactory() {
        return Persistence.createEntityManagerFactory("ua.com.codefire");
    }

    @Bean
    @Lazy
    public List<CSVRecord> recordsList() {
        return new CSVReader().getRecords();
    }

    @Bean
    @Lazy
    public Integer sizeRecords() {
        return recordsList().size();
    }
}
