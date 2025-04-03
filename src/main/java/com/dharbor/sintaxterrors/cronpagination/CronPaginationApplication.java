package com.dharbor.sintaxterrors.cronpagination;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class CronPaginationApplication {

    public static void main(String[] args) {
        SpringApplication.run(CronPaginationApplication.class, args);
    }

}
