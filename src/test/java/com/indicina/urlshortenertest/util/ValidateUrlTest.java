package com.indicina.urlshortenertest.util;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class ValidateUrlTest {

    @Test
    void isValidUrl() {
        String url = "https://www.google.com";

        boolean isValid = ValidateUrl.isValidUrl(url);


        assertThat(isValid).isTrue();

    }

    @Test
    void isNotValidUrl() {
        String url = "email.google.com";

        boolean isNotValid = ValidateUrl.isValidUrl(url);

        assertThat(isNotValid).isFalse();
    }
}