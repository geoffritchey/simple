package com.ritchey.simple;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextClosedEvent;
import org.springframework.stereotype.Component;

@Component
public class ApplicationShutdown implements ApplicationListener<ContextClosedEvent> {
    private static final Logger LOGGER = LoggerFactory.getLogger(ApplicationErrorListener.class);
    
     @Override
     public void onApplicationEvent(ContextClosedEvent event) {
    	 LOGGER.info("GEOFF !!!!!!ShutDown.!!!!!!");
     }
}