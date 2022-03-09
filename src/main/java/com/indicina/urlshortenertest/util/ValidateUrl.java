package com.indicina.urlshortenertest.util;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;


public class ValidateUrl {

    private static final Logger log = LoggerFactory.getLogger(ValidateUrl.class);
    private ValidateUrl() {
    }

    public static boolean isValidUrl(String url) {
        try {
            URL uri = new URL(url);
            uri.toURI();
        } catch (MalformedURLException e){
            log.info("Exception Validating Url", e);
            return false;
        }catch (URISyntaxException ex){
            log.info("Exception Processing Url validation", ex);
            return false;
        }

        return true;

    }
}
