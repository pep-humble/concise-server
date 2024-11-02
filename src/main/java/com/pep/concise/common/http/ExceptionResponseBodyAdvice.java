package com.pep.concise.common.http;

import com.pep.concise.common.enums.ResultErrorStatus;
import com.pep.concise.common.vo.ResponseResult;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.NoHandlerFoundException;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * 异常处理器
 *
 * @author gang.liu
 */
@Component
@RestControllerAdvice
public class ExceptionResponseBodyAdvice {

    @Value("${business.exception.exception-code-response:false}")
    private boolean exceptionCodeResponse;

    /**
     * 404异常
     *
     * @param exception 异常信息
     * @return 响应结果
     */
    @ExceptionHandler(NoHandlerFoundException.class)
    public ResponseEntity<ResponseResult<String>> handlerNoHandlerFoundException(NoHandlerFoundException exception) {
        ResponseResult<String> failure = ResponseResult.failure(
                String.format("%s %s %s", exception.getHttpMethod(), exception.getRequestURL(), ResultErrorStatus.NO_RESOURCES.getMessage()),
                ResultErrorStatus.NO_RESOURCES
        );
        return this.wrap(failure);
    }

    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<ResponseResult<String>> handlerAccessDeniedException(AccessDeniedException accessDeniedException) {
        ResponseResult<String> failure = ResponseResult.failure(ResultErrorStatus.UN_PERMISSION);
        return this.wrap(failure);
    }

    /**
     * 参数检验异常
     *
     * @param exception 异常信息
     * @return 响应结果
     */
    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<ResponseResult<String>> handlerConstraintViolationException(ConstraintViolationException exception) {
        Set<ConstraintViolation<?>> constraintViolations = exception.getConstraintViolations();
        ResponseResult<String> failure = ResponseResult.failure(
                constraintViolations.iterator().next().getMessage(),
                ResultErrorStatus.BAD_REQUEST
        );
        return this.wrap(failure);
    }

    /**
     * 参数检验异常
     *
     * @param exception 异常信息
     * @return 响应结果
     */
    @ExceptionHandler(BindException.class)
    public ResponseEntity<ResponseResult<String>> handlerBindException(BindException exception) {
        List<ObjectError> allErrors = exception.getAllErrors();
        String collect = allErrors.stream()
                .map(error -> {
                    if (error instanceof FieldError) {
                        FieldError fieldError = (FieldError) error;
                        return String.join(StringUtils.SPACE, fieldError.getField(), fieldError.getDefaultMessage());
                    }
                    return error.getDefaultMessage();
                })
                .filter(StringUtils::isNotBlank)
                .map(String::trim)
                .distinct()
                .collect(Collectors.joining(","));
        ResponseResult<String> failure = ResponseResult.failure(
                collect,
                ResultErrorStatus.BAD_REQUEST
        );
        return this.wrap(failure);
    }

    private ResponseEntity<ResponseResult<String>> wrap(ResponseResult<String> failure) {
        if (exceptionCodeResponse) {
            return ResponseEntity
                    .status(failure.getCode())
                    .body(failure);
        }
        return ResponseEntity
                .status(HttpStatus.OK.value())
                .body(failure);
    }
}
