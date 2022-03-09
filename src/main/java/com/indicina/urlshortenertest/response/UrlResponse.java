package com.indicina.urlshortenertest.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UrlResponse extends ErrorResponse{
    private String url;

    public UrlResponse() {
    }

    public UrlResponse(String responseMessage) {
        super(responseMessage);
    }
}
