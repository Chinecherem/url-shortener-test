package com.indicina.urlshortenertest.urlService;

import com.indicina.urlshortenertest.model.UrlDetails;
import com.indicina.urlshortenertest.model.repo.UrlRepository;
import com.indicina.urlshortenertest.response.UrlResponse;
import com.indicina.urlshortenertest.util.GenerateUrlCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Date;
@Service
public class UrlServiceImpl implements UrlService{

    @Autowired
    private ValidateUrl validateUrl;

    @Autowired
    private UrlRepository urlRepository;


    @Value("${baseUrl}")
    private String BASE_URL;

    @Override
    public UrlResponse encodeUrl(String longUrl) {
        UrlResponse response = new UrlResponse();

        if (longUrl == null || longUrl.isEmpty()){
            return new UrlResponse("Provide a url to shorten");
        }
        if (!validateUrl.isValidUrl(longUrl)){
            return new UrlResponse("Provided URL is not Valid.");
        }
        UrlDetails existingUrl = urlRepository.findByLongUrl(longUrl);
        if (existingUrl != null){
            String shortUrl = existingUrl.getShortUrl();
            response.setShortUrl(shortUrl);
            return response;
        }
        String urlCode = GenerateUrlCode.generateCode(longUrl);
        UrlDetails urlDetails = new UrlDetails();
        urlDetails.setLongUrl(longUrl);
        urlDetails.setShortUrl(BASE_URL.concat(urlCode));
        urlDetails.setUrlCode(urlCode);
        urlDetails.setDateCreated(new Date());
        urlRepository.save(urlDetails);


        response.setShortUrl(BASE_URL.concat(urlCode));


        return response;
    }

    @Override
    public UrlResponse decodeUrl(String urlCode) {
        return null;
    }
}
