package com.example.regionkommuner2.exception;

import java.util.Date;

public class ErrorMessage {
    //laver vores egen fejlmeddelelse, returneres til front end

    //ting til fejlkoden
    private int statusCode; //http statuskode
    private Date timeStamp; //hvornår gik det galt
    private String message; //selve beskeden
    private String description;

    public ErrorMessage(int statusCode, Date timeStamp, String message, String description) {
        this.statusCode = statusCode;
        this.timeStamp = timeStamp;
        this.message = message;
        this.description = description;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public Date getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(Date timeStamp) {
        this.timeStamp = timeStamp;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
