package com.indicina.urlshortenertest.model.repo;

import com.indicina.urlshortenertest.model.UrlDetails;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class UrlRepositoryTest {

    @Autowired
    private UrlRepository urlRepoTest;

    @AfterEach
    void tearDown() {
        urlRepoTest.deleteAll();
    }

    @Test
    void checkIfProvidedLongUrlExists() {
        //given
        UrlDetails urlDetails = new UrlDetails();
        urlDetails.setLongUrl("https://www.google.com");
        urlDetails.setShortUrl("http://bo.com/4908895f");
        urlDetails.setUrlCode("4908895f");
        urlRepoTest.save(urlDetails);
        //when
        Optional<UrlDetails> exists = urlRepoTest.findByLongUrl(urlDetails.getLongUrl());
        //then
        assertThat(exists).isNotEmpty();
        assertThat(exists.get().getLongUrl()).isEqualTo("https://www.google.com");
    }
    @Test
    void checkIfProvidedLongUrlDoesNotExists() {
        //given
        UrlDetails urlDetails = new UrlDetails();
        urlDetails.setLongUrl("https://www.google.com");
        urlDetails.setShortUrl("http://bo.com/4908895f");
        urlDetails.setUrlCode("4908895f");
        urlRepoTest.save(urlDetails);
        //when
        Optional<UrlDetails> exists = urlRepoTest.findByLongUrl("https://www.twitter.com");
        //then
        assertThat(exists).isEmpty();
    }

    @Test
    void checkIfProvidedShortUrlExists() {
        //given
        UrlDetails urlDetails = new UrlDetails();
        urlDetails.setLongUrl("https://www.google.com");
        urlDetails.setShortUrl("http://bo.com/4908895f");
        urlDetails.setUrlCode("4908895f");
        urlRepoTest.save(urlDetails);
        //when
        Optional<UrlDetails> exists = urlRepoTest.findByShortUrl(urlDetails.getShortUrl());
        //then
        assertThat(exists).isNotEmpty();
        assertThat(exists.get().getShortUrl()).isEqualTo("http://bo.com/4908895f");
    }
    @Test
    void checkIfProvidedShortUrlDoesNotExists() {

        //when
        Optional<UrlDetails> exists = urlRepoTest.findByShortUrl("https://www.youTube.com");
        //then
        assertThat(exists).isEmpty();
    }

    @Test
    void CheckProvidedUrlCodeExists() {
        //given
        UrlDetails urlDetails = new UrlDetails();
        urlDetails.setLongUrl("https://www.google.com");
        urlDetails.setShortUrl("http://bo.com/4908895f");
        urlDetails.setUrlCode("4908895f");
        urlRepoTest.save(urlDetails);
        //when
        Optional<UrlDetails> exists = urlRepoTest.findByUrlCode(urlDetails.getUrlCode());
        //then
        assertThat(exists).isNotEmpty();
        assertThat(exists.get().getUrlCode()).isEqualTo("4908895f");
    }

    @Test
    void CheckProvidedUrlCodeDoesNotExists() {

        //when
        Optional<UrlDetails> exists = urlRepoTest.findByUrlCode("5908895b");
        //then
        assertThat(exists).isEmpty();
    }
}