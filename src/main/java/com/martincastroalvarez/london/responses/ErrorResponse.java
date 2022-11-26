package com.martincastroalvarez.london;

import java.util.Date;

public class ErrorResponse {
    // --------------------------------------------------------------------
    // This class is used to translate Java errors into JSON messages
    // that can be returned by the API controllers.
    // --------------------------------------------------------------------

    private Date timestamp;
    private String code;
    private String message;

    public ErrorResponse(String code, String message) {
         super();
         this.code = code;
         this.timestamp = new Date();
         this.message = message;
    }

    public Date getTimestamp() { return timestamp; }
    public String getMessage() { return message; }
    public String getCode() { return code; }
}
