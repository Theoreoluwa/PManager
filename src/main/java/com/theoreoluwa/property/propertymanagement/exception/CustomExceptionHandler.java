package com.theoreoluwa.property.propertymanagement.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.ArrayList;
import java.util.List;

@ControllerAdvice
public class CustomExceptionHandler {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<List<ErrorModel>> handleFieldValidation(MethodArgumentNotValidException ex) {
        List<ErrorModel> errorModelList = new ArrayList<>();
        ErrorModel errorModel;
        List<FieldError> fieldErrorList = ex.getBindingResult().getFieldErrors();
        for(FieldError fieldError : fieldErrorList){
            logger.debug("Inside field validation: {} - {}",
                    fieldError.getField(), fieldError.getDefaultMessage());
            logger.info("Inside field validation: {} - {}",
                    fieldError.getField(), fieldError.getDefaultMessage());
            errorModel = new ErrorModel();
            errorModel.setErrorCode(fieldError.getField());
            errorModel.setErrorMessage(fieldError.getDefaultMessage());
            errorModelList.add(errorModel);
        }


        return new ResponseEntity<List<ErrorModel>>(errorModelList,HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<List<ErrorModel>> handleBusinessException(BusinessException businessException) {
        for (ErrorModel errorModel : businessException.getErrors()){
        logger.debug("Business Exception has been thrown - level - debug: {} - {}", errorModel.getErrorCode(), errorModel.getErrorMessage());
        logger.info("Business Exception has been thrown - level - info: {} - {}", errorModel.getErrorMessage(), errorModel.getErrorMessage());
        logger.warn("Business Exception has been thrown - level - warn: {} - {}", errorModel.getErrorMessage(), errorModel.getErrorMessage());
        logger.error("Business Exception has been thrown - level - error: {} - {}", errorModel.getErrorMessage(), errorModel.getErrorMessage());
        }
        return new ResponseEntity<List<ErrorModel>>(businessException.getErrors(),HttpStatus.BAD_REQUEST);
    }
}
