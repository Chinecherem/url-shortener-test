package com.indicina.urlshortenertest.urlController;

import com.indicina.urlshortenertest.request.ShortUrlRequest;
import com.indicina.urlshortenertest.response.UrlResponse;
import com.indicina.urlshortenertest.urlService.UrlServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.logging.Logger;

@RestController("api")
public class UrlOperation {

    @Autowired
    private UrlServiceImpl urlService;

    //Logger LOG = new Logger(loUrlOperation.class);

    @PostMapping("/encodeUrl")
    @ResponseBody
    public UrlResponse encodeUrl(@Validated @RequestBody ShortUrlRequest request){

        UrlResponse response = urlService.encodeUrl(request.getUrl());

        return response;
    }
}
