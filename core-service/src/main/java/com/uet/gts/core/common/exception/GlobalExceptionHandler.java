package com.uet.gts.core.common.exception;

import com.uet.gts.core.common.exception.ex.BusinessException;
import com.uet.gts.core.common.exception.ex.ExistedEntityException;
import com.uet.gts.core.common.exception.ex.NotFoundEntityException;
import com.uet.gts.core.model.dto.ErrorDTO;
import com.uet.gts.core.model.dto.ResponseDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import javax.persistence.EntityNotFoundException;
import java.util.List;

import static com.uet.gts.core.common.constant.ErrorList.*;

@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(EntityNotFoundException.class)
    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    public @ResponseBody ResponseDTO handleNotFound(EntityNotFoundException e) {
        return new ResponseDTO(
          new ErrorDTO(e.getMessage())
        );
    }

    @ExceptionHandler({ IllegalArgumentException.class, ExistedEntityException.class, NotFoundEntityException.class, BusinessException.class })
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public @ResponseBody ResponseDTO handleBadRequest(Exception e) {
        return new ResponseDTO(
                new ErrorDTO(e.getMessage())
        );
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public @ResponseBody ResponseDTO handleInvalidRequestValidation(MethodArgumentNotValidException e) {
        List<ObjectError> objectErrors = e.getBindingResult().getAllErrors();
        StringBuilder msg = new StringBuilder("Validation failed: ");

        for (ObjectError objectError : objectErrors) {
            FieldError fieldError = (FieldError) objectError;
            String[] splitMsg = fieldError.getDefaultMessage().split("~");
            if (splitMsg.length == 2) {
                msg.append(splitMsg[1]);
                if (fieldError.getRejectedValue() != null) {
                    msg.append(", Original value [").append(fieldError.getRejectedValue()).append("]");
                }
                return new ResponseDTO(
                        new ErrorDTO(msg.toString())
                );
            } else {
                return new ResponseDTO(
                        new ErrorDTO(msg.append(fieldError.getDefaultMessage()).toString())
                );
            }
        }

        return null;
    }

    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public @ResponseBody ResponseDTO handleMethodNotSupported(HttpRequestMethodNotSupportedException e) {
        return new ResponseDTO(
                new ErrorDTO(e.getMessage())
        );
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    public @ResponseBody ResponseDTO handleUnknownError(Exception e) {
        e.printStackTrace();
        return new ResponseDTO(
                new ErrorDTO(UNKNOWN_ERROR)
        );
    }
}
