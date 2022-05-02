package com.uet.gts.report.log;

import com.uet.gts.report.model.EventMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

@Component
public class ActionLogPublisher {

    @Autowired
    private ApplicationEventPublisher applicationEventPublisher;

    public <T> void sendMessage(T event) {
        applicationEventPublisher.publishEvent(new EventMessage<T>(this, event));
    }
}
