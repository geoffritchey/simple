package com.ritchey.simple;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.event.ApplicationFailedEvent;
import org.springframework.context.ApplicationListener;

public class ApplicationErrorListener implements 
                ApplicationListener<ApplicationFailedEvent> {

    private static final Logger LOGGER = 
    LoggerFactory.getLogger(ApplicationErrorListener.class);

    @Override
    public void onApplicationEvent(ApplicationFailedEvent event) {
       if (event.getException() != null) {
            LOGGER.error("GEOFF !!!!!!Looks like something not working as expected so stopping application.!!!!!!", event.getException());
                     event.getApplicationContext().close();
              System.exit(-1);
       } 
    }
}