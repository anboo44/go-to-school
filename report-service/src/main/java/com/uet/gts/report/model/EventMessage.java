package com.uet.gts.report.model;

import org.springframework.context.ApplicationEvent;

public class EventMessage<T> extends ApplicationEvent {
    private final T data;

    public EventMessage(Object source, T data) {
        super(source);
        this.data = data;
    }

    public T getData() {
        return data;
    }
}
