package com.uet.gts.core.common.exception;

import com.uet.gts.core.model.dto.ErrorDTO;
import com.uet.gts.core.model.dto.ResponseDTO;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.persistence.EntityNotFoundException;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(EntityNotFoundException.class)
    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    public @ResponseBody ResponseDTO handleNotFound(EntityNotFoundException e) {
        return new ResponseDTO(
          new ErrorDTO(e.getMessage())
        );
    }

    @ExceptionHandler(IllegalArgumentException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public @ResponseBody ResponseDTO handleBadRequest(IllegalArgumentException e) {
        return new ResponseDTO(
                new ErrorDTO(e.getMessage())
        );
    }
}
