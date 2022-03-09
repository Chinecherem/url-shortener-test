package com.indicina.urlshortenertest.util;

import com.google.common.hash.HashCode;
import com.google.common.hash.Hashing;

import java.nio.charset.Charset;

public class GenerateUrlCode {
    private GenerateUrlCode() {
    }

    public static String generateCode(String url){

        HashCode urlCode = Hashing.adler32().hashString(url, Charset.defaultCharset());

        return urlCode.toString();
    }
}
