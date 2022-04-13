package com.uet.gts.core.model.dto;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;

@Configuration
public class ForceStringDeserializer extends JsonDeserializer<String> {

    @Override
    public String deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
        if (jsonParser.getCurrentToken() == JsonToken.VALUE_NUMBER_INT) {
            throw deserializationContext.wrongTokenException(jsonParser, JsonToken.VALUE_STRING, "Attempted to parse int to string but this is forbidden");
        }
        return jsonParser.getValueAsString();
    }
}
