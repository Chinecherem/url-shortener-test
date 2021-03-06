package com.indicina.urlshortenertest.controller;

import com.indicina.urlshortenertest.request.ShortUrlRequest;
import com.indicina.urlshortenertest.response.UrlResponse;
import com.indicina.urlshortenertest.response.UrlStatisticResponse;
import com.indicina.urlshortenertest.urlservice.UrlServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@RestController
@RequestMapping(path = "api/")
public class UrlController {


    @Autowired
    private UrlServiceImpl urlService;

    private final Logger log = LoggerFactory.getLogger(getClass());

    @PostMapping("encode")
    @ResponseBody
    public UrlResponse encodeUrl(@Validated @RequestBody ShortUrlRequest request) {

        return urlService.encodeUrl(request.getUrl());
    }

    @GetMapping("decode")
    @ResponseBody
    public void decodeUrl(@Validated @RequestParam("shortUrl") String shortUrl, HttpServletResponse response) {

        UrlResponse res = urlService.decodeUrl(shortUrl);
        try {
            response.sendRedirect(res.getUrl());
        }catch (IOException e){
            log.info("Exception processing request", e);
        }

    }

    @GetMapping("statistic/{urlCode}")
    public UrlStatisticResponse getStatistic(@Validated @PathVariable("urlCode") String urlCode){

        UrlStatisticResponse response = urlService.getStatistics(urlCode);

        return response;

    }
}
