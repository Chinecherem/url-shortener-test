package com.indicina.urlshortenertest.urlservice;

import com.indicina.urlshortenertest.model.repo.UrlRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.*;

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
    void encodeUrl() {
    }

    @Test
    void decodeUrl() {
    }

    @Test
    void getStatistics() {
    }
}