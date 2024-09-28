package com.api.payload;

import java.util.Date;

public class ErrorDto {
    private String Error;
    private Date date;
    private String uri;

    public ErrorDto(String error, Date date, String uri) {
        Error = error;
        this.date = date;
        this.uri = uri;
    }

    public String getError() {
        return Error;
    }

    public Date getDate() {
        return date;
    }

    public String getUri() {
        return uri;
    }
}
