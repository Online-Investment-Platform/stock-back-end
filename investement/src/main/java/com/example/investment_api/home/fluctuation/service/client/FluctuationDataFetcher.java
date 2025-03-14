package com.example.investment_api.home.fluctuation.service.client;

import com.example.investment_api.common.token.TokenService;
import org.springframework.beans.factory.annotation.Value;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;

import org.springframework.stereotype.Component;

import org.springframework.web.client.RestTemplate;

@Component
public class FluctuationDataFetcher {

    private static final String TR_ID = "tr_id";
    private static final String APPSECRET = "appsecret";
    private static final String APPKEY = "appkey";
    private static final String AUTHORIZATION = "Authorization";
    private static final String BEARER = "Bearer ";
    private static final String CONTENT_TYPE = "Content-Type";
    private static final String CONTENT_TYPE1 = "application/json";

    private final RestTemplate restTemplate;

    private final TokenService tokenService;

    @Value("${API_APP_KEY}")
    private String appKey;

    @Value("${API_APP_SECRET}")
    private String appSecret;

    @Value("${FLUCTUATION_TR_ID}")
    private String trId;

    public FluctuationDataFetcher(RestTemplate restTemplate, final TokenService tokenService) {
        this.restTemplate = restTemplate;
        this.tokenService = tokenService;
    }

    public ResponseEntity<String> fluctuationData() {
        return getStringResponseEntity();
    }

    private ResponseEntity<String> getStringResponseEntity() {
        String url = setURL();
        HttpHeaders headers = new HttpHeaders();
        setHeader(headers);

        HttpEntity<String> entity = new HttpEntity<>(headers);
        return restTemplate.exchange(url, HttpMethod.GET, entity, String.class);
    }

    private void setHeader(final HttpHeaders headers) {
        String accessToken = tokenService.getAccessToken();
        System.out.println(accessToken);
        headers.set(TR_ID, trId);
        headers.set(APPSECRET, appSecret);
        headers.set(APPKEY, appKey);
        headers.set(AUTHORIZATION, BEARER + accessToken);
        headers.set(CONTENT_TYPE, CONTENT_TYPE1);
    }

    private String setURL() {
        return "https://openapi.koreainvestment.com:9443/uapi/domestic-stock/v1/ranking/fluctuation?"
                + "fid_cond_mrkt_div_code=J&"
                + "fid_cond_scr_div_code=20170&"
                + "fid_input_iscd=0000&"
                + "fid_rank_sort_cls_code=0&"
                + "fid_input_cnt_1=0&"
                + "fid_prc_cls_code=0&"
                + "fid_input_price_1=&"
                + "fid_input_price_2=&"
                + "fid_vol_cnt=&"
                + "fid_trgt_cls_code=0&"
                + "fid_trgt_exls_cls_code=0&"
                + "fid_div_cls_code=0&"
                + "fid_rsfl_rate1=&"
                + "fid_rsfl_rate2=";
    }
}
