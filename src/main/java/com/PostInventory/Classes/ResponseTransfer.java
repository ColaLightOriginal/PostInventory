package com.PostInventory.Classes;

public class ResponseTransfer {
    private String httpStatus;
    private String httpCode;
    private String message;

    public ResponseTransfer(String httpStatus, String httpCode, String message ){
        this.httpStatus=httpStatus;
        this.httpCode=httpCode;
        this.message=message;
    }

    public String getHttpStatus() {
        return httpStatus;
    }

    public void setHttpStatus(String httpStatus) {
        this.httpStatus = httpStatus;
    }

    public String getHttpCode() {
        return httpCode;
    }

    public void setHttpCode(String httpCode) {
        this.httpCode = httpCode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }


}
