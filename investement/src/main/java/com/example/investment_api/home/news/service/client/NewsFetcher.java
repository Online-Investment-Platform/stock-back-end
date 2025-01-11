package com.example.investment_api.home.news.service.client;

import com.example.investment_api.common.api.ApiMessage;
import com.example.investment_api.common.config.RestTemplateClient;

import org.json.JSONException;

import org.springframework.beans.factory.annotation.Value;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;

import org.springframework.stereotype.Component;

@Component
public class NewsFetcher {

    @Value("${NAVER_API_CLIENTID}")
    private String clientId;

    @Value("${NAVER_API_KEY}")
    private String clientSecret;

    private final RestTemplateClient restTemplateClient;

    public NewsFetcher(final RestTemplateClient restTemplateClient) {
        this.restTemplateClient = restTemplateClient;
    }

    public ResponseEntity<String> fetch(String keyword) throws JSONException {
        return setURL(keyword);
    }

    private ResponseEntity<String> setURL(final String keyword) {
        String url = ApiMessage.NAVER_URL.name() + keyword;
        HttpHeaders headers = setHeader();
        return get(url, headers);
    }

    public ResponseEntity<String> get(String url, HttpHeaders headers) {
        return restTemplateClient.exchange(url, HttpMethod.GET, headers, String.class);
    }

    private HttpHeaders setHeader() {
        HttpHeaders headers = new HttpHeaders();
        headers.set(ApiMessage.NAVER_CLIENT_ID.name(), clientId);
        headers.set(ApiMessage.NAVER_CLIENT_SECRET.name(), clientSecret);
        return headers;
    }
}
