package com.indicina.urlshortenertest.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.util.Date;
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UrlStatisticResponse  extends ErrorResponse{
    private String originalUrl;
    private String shortUrl;
    private String urlCode;
    private Date dateCreated;

    public UrlStatisticResponse() {
    }

    public UrlStatisticResponse(String responseMessage) {
        super(responseMessage);
    }
}
