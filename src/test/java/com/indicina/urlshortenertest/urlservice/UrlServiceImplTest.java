package com.indicina.urlshortenertest.urlservice;

import com.indicina.urlshortenertest.model.UrlDetails;
import com.indicina.urlshortenertest.model.repo.UrlRepository;
import com.indicina.urlshortenertest.response.UrlResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

//@ExtendWith(MockitoExtension.class)
@SpringBootTest
class UrlServiceImplTest {

    @Mock
    private UrlRepository urlRepository;

    @Autowired
    private UrlServiceImpl urlServiceTest;

    @BeforeEach
    void setUp() {
        urlServiceTest = new UrlServiceImpl(urlRepository);
    }

    @Test
    void whenLongUrlIsEmpty() {
        //Given
        String longUrl = "";

        //when
        UrlResponse urlResponse = urlServiceTest.encodeUrl(longUrl);

        //then
        assertThat(urlResponse).withFailMessage("Provide a url to shorten");

    }

    @Test
    void whenLongUrlIsNotValid() {
        //Given
        String longUrl = "email.google.com";

        //when
        UrlResponse urlResponse = urlServiceTest.encodeUrl(longUrl);

        //then
        assertThat(urlResponse).withFailMessage("Provided URL is not Valid.");

    }


    @Test
    void whenShortUrlIsEmpty() {

        //Given
        String shortUrl = "";

        //when
        UrlResponse urlResponse = urlServiceTest.decodeUrl(shortUrl);

        //then
        assertThat(urlResponse).withFailMessage("Provide a url to decode");

    }



    @Test
    void getStatistics() {
        //Given
        String urlCode = "";

        //when
        UrlResponse urlResponse = urlServiceTest.encodeUrl(urlCode);

        //then
        assertThat(urlResponse).withFailMessage("Can't get statistics for an empty path. Provide a url path");

    }

    }

