package com.indicina.urlshortenertest.urlService;

import com.indicina.urlshortenertest.response.UrlResponse;
import org.springframework.stereotype.Service;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;

@Service
public class ValidateUrl {

    public boolean isValidUrl(String url) {
        try {
            URL uri = new URL(url);
            uri.toURI();
        } catch (MalformedURLException e){
            //log exception here
            return false;
        }catch (URISyntaxException ex){
            return false;
        }

        return true;

    }
}
