package com.example.safaricomtalenthack.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class UserNotFoundException extends RuntimeException {
    private String userName;
    private String fieldName;
    private Object fieldValue;



    public UserNotFoundException(String userName,String fieldName,Object fieldValue){
        super(String.format("%s not found with %s : %s",userName,fieldName,fieldValue));
        this.userName=userName;
        this.fieldName=fieldName;
        this.fieldValue=fieldValue;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getFieldName() {
        return fieldName;
    }

    public void setFieldName(String fieldName) {
        this.fieldName = fieldName;
    }

    public Object getFieldValue() {
        return fieldValue;
    }

    public void setFieldValue(Object fieldValue) {
        this.fieldValue = fieldValue;
    }
}

