package com.indicina.urlshortenertest.urlservice;

import com.indicina.urlshortenertest.model.UrlDetails;
import com.indicina.urlshortenertest.model.repo.UrlRepository;
import com.indicina.urlshortenertest.response.UrlResponse;
import com.indicina.urlshortenertest.response.UrlStatisticResponse;
import com.indicina.urlshortenertest.util.GenerateUrlCode;
import com.indicina.urlshortenertest.util.ValidateUrl;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;

@AllArgsConstructor
@Service
public class UrlServiceImpl implements UrlService{

    @Autowired
    private UrlRepository urlRepository;


    @Value("${baseUrl}")
    private String baseUrl;


    public UrlServiceImpl(UrlRepository urlRepository) {
    }


    @Override
    public UrlResponse encodeUrl(String longUrl) {
        UrlResponse response = new UrlResponse();

        if (longUrl == null || longUrl.isEmpty()){
            return new UrlResponse("Provide a url to shorten");
        }
        if (!ValidateUrl.isValidUrl(longUrl)){
            return new UrlResponse("Provided URL is not Valid.");
        }
        Optional<UrlDetails> existingUrl = urlRepository.findByLongUrl(longUrl);
        if (existingUrl.isPresent()){
            String shortUrl = existingUrl.get().getShortUrl();
            response.setUrl(shortUrl);
            return response;
        }
        String urlCode = GenerateUrlCode.generateCode(longUrl);
        UrlDetails urlDetails = new UrlDetails();
        urlDetails.setLongUrl(longUrl);
        urlDetails.setShortUrl(baseUrl.concat(urlCode));
        urlDetails.setUrlCode(urlCode);
        urlDetails.setDateCreated(new Date());
        urlRepository.save(urlDetails);


        response.setUrl(baseUrl.concat(urlCode));


        return response;
    }

    @Override
    public UrlResponse decodeUrl(String shortUrl)  {
        UrlResponse response = new UrlResponse();
        if (shortUrl == null || shortUrl.isEmpty()){
            return new UrlResponse("Provide url to decode");
        }

        Optional<UrlDetails> urlDetails = urlRepository.findByShortUrl(shortUrl);
        if (urlDetails.isEmpty()){
            return new UrlResponse("No record found for Url");
        }
        String longUrl = urlDetails.get().getLongUrl();
        response.setUrl(longUrl);
        return response;
    }

    @Override
    public UrlStatisticResponse getStatistics(String urlCode) {
        UrlStatisticResponse response = new UrlStatisticResponse();
        if (urlCode == null || urlCode.isEmpty()){
            return new UrlStatisticResponse("Can't get statistics for an empty path. Provide a url path");
        }
        Optional<UrlDetails> urlDetails = urlRepository.findByUrlCode(urlCode);
        if (urlDetails.isEmpty()){
            return new UrlStatisticResponse("No record for provided url path. Kindly check and retry");
        }
        response.setOriginalUrl(urlDetails.get().getLongUrl());
        response.setShortUrl(urlDetails.get().getShortUrl());
        response.setUrlCode(urlDetails.get().getUrlCode());
        response.setDateCreated(urlDetails.get().getDateCreated());
        return response;
    }


}
