package com.indicina.urlshortenertest.util;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class GenerateUrlCodeTest {

    @Test
    void checkAlgorithmGeneratesCode() {
        String longUrl = "https://www.google.com";
        String generatedUrl = GenerateUrlCode.generateCode(longUrl);

        assertThat(generatedUrl).isNotBlank();
        assertThat(generatedUrl).isEqualTo("4908895f");
    }
}