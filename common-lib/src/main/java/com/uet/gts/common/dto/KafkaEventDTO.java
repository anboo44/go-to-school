package com.uet.gts.common.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class KafkaEventDTO {
    public enum EventType {
        STUDENT, TEACHER, CLASSROOM
    }

    private EventType eventType;
    private Object data;

    public KafkaEventDTO(EventType eventType, Object data) {
        this.eventType = eventType;
        this.data = data;
    }
}
