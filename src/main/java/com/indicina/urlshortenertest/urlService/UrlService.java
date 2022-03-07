package com.indicina.urlshortenertest.urlService;

import com.indicina.urlshortenertest.model.UrlDetails;
import com.indicina.urlshortenertest.response.UrlResponse;
//import org.springframework.stereotype.Service;


public interface UrlService {
    UrlResponse encodeUrl(String shortUrl);
    UrlResponse decodeUrl(String urlCode);
}
