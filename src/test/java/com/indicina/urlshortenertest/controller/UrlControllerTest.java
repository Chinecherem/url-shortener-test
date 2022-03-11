package com.indicina.urlshortenertest.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.indicina.urlshortenertest.request.ShortUrlRequest;
import com.indicina.urlshortenertest.response.UrlResponse;
import com.indicina.urlshortenertest.response.UrlStatisticResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;


import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
class UrlControllerTest {

    private WebApplicationContext webApplicationContext;

    private MockMvc mockMvc;

    @Autowired
    UrlControllerTest(WebApplicationContext webApplicationContext) {
        this.webApplicationContext = webApplicationContext;
    }

    @BeforeEach
    public void setup() throws Exception {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.webApplicationContext).build();
    }


    @Test
    void encodeUrl() throws Exception {

        String requestJson = "";
        ShortUrlRequest request = new ShortUrlRequest();
        request.setUrl("https://www.google.com");

        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
        ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
        requestJson = ow.writeValueAsString(request);

        MvcResult mvcResult = mockMvc.perform(post("http://localhost:8081/api/encode").contentType(MediaType.APPLICATION_JSON).content(requestJson))
                .andExpect(status().isOk())
                .andReturn();

        String result = mvcResult.getResponse().getContentAsString();

        ObjectMapper obj = new ObjectMapper();
        UrlResponse resp = obj.readValue(result, UrlResponse.class);

        assertEquals(resp.getUrl(), "http://bo.com/4908895f");

    }

    @Test
    void decodeUrl() throws Exception {
        mockMvc.perform(get(("http://localhost:8081/api/decode?shortUrl=http://bo.com/4908895f"))
                .contentType(String.valueOf(redirectedUrlTemplate("https://www,google.com"))))
                .andExpect(status().isFound())
                .andReturn();
    }

    @Test
    void getStatistic() throws Exception {

        MvcResult mvcResult = mockMvc.perform(get("http://localhost:8081/api/statistic/4908895f")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();

        String result = mvcResult.getResponse().getContentAsString();

        ObjectMapper obj = new ObjectMapper();
        UrlStatisticResponse resp = obj.readValue(result, UrlStatisticResponse.class);

        assertEquals(resp.getOriginalUrl(), "https://www.google.com");
        assertEquals(resp.getShortUrl(), "http://bo.com/4908895f");
        assertEquals(resp.getUrlCode(), "4908895f");
    }
}