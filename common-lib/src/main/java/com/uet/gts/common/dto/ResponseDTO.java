package com.uet.gts.common.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import lombok.*;

import java.io.Serializable;
import java.util.Date;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ResponseDTO<T> implements Serializable {

    private static final long serialVersionUID = 4777498908126431387L;

    @JsonInclude(value = Include.NON_NULL)
    private T data;

    @JsonInclude(value = Include.NON_NULL)
    private T error;

    @JsonInclude(value = Include.NON_NULL)
    private MetaDTO meta;

    public ResponseDTO(T data) {
        if (data instanceof ErrorDTO) {
            this.error = data;
        } else {
            this.data = data;
        }
    }

    @JsonFormat(pattern = "yyyy/MM/dd HH:mm:ss")
    public Date getTime() {
        return new Date();
    }
}