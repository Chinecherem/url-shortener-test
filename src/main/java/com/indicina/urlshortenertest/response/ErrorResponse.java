package com.indicina.urlshortenertest.response;

import lombok.Data;

@Data
public class ErrorResponse {
    private String responseMessage;

    public ErrorResponse() {
    }

    public ErrorResponse(String responseMessage) {
        this.responseMessage = responseMessage;
    }
}
