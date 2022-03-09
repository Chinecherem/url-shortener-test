package com.indicina.urlshortenertest.urlservice;


import com.indicina.urlshortenertest.response.UrlResponse;
import com.indicina.urlshortenertest.response.UrlStatisticResponse;

import java.io.IOException;


public interface UrlService {
    UrlResponse encodeUrl(String longUrl);

    UrlResponse decodeUrl(String shortUrl) throws IOException;

    UrlStatisticResponse getStatistics(String urlCode);
}
