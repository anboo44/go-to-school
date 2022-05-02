package com.uet.gts.report.exception;

import com.uet.gts.common.dto.ErrorDTO;
import com.uet.gts.common.dto.ResponseDTO;
import com.uet.gts.common.exception.ExternalApiCallException;
import com.uet.gts.common.exception.BusinessException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import static com.uet.gts.common.constant.ErrorList.UNKNOWN_ERROR;

@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler({ BusinessException.class })
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public @ResponseBody ResponseDTO handleBadRequest(Exception e) {
        return new ResponseDTO(
                new ErrorDTO(e.getMessage())
        );
    }

    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public @ResponseBody ResponseDTO handleMethodNotSupported(HttpRequestMethodNotSupportedException e) {
        return new ResponseDTO(
                new ErrorDTO(e.getMessage())
        );
    }

    @ExceptionHandler({ ExternalApiCallException.class })
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    public @ResponseBody ResponseDTO handleExternalApiError(ExternalApiCallException e) {
        return new ResponseDTO(
                new ErrorDTO(e.getMessage())
        );
    }

    @ExceptionHandler({ Exception.class })
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    public @ResponseBody ResponseDTO handleUnknownError(Exception e) {
        e.printStackTrace();
        return new ResponseDTO(
                new ErrorDTO(UNKNOWN_ERROR)
        );
    }
}
