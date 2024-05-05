package com.example.mylibrary;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

@Component
public class CronTask {

    // https://cloud.google.com/scheduler/docs/configuring/cron-job-schedules

    // https://www.baeldung.com/spring-scheduled-tasks

    // хотим чтобы это функция выполнялась по расписанию
    // @Scheduled(fixedRate = 3600_000) // каждый час - время по-умолчанию в милисекундах
    // @Scheduled(fixedRate = 5, timeUnit = TimeUnit.HOURS, initialDelay = 50_000)
    //                 s m h dm M dw
    @Scheduled(cron = "0 3 21 1 * *")
    public void taskToRunPeriodically()
    {
        //
    }

}
