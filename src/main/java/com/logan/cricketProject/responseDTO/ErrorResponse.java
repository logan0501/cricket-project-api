package com.logan.cricketProject.responseDTO;

public class ErrorResponse {
    private long timeStamp = System.currentTimeMillis();
    private String message;

    public ErrorResponse(String message) {
        this.message = message;
    }

    public long getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(long timeStamp) {
        this.timeStamp = timeStamp;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
