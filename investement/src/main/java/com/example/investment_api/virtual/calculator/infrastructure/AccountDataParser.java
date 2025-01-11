package com.example.investment_api.virtual.calculator.infrastructure;

import com.example.investment_api.common.api.ApiMessage;
import com.example.investment_api.virtual.account.controller.dto.StockData;

import com.fasterxml.jackson.core.JsonProcessingException;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Component
public class AccountDataParser {

    private final ObjectMapper objectMapper;

    public AccountDataParser(final ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    public List<StockData> parseAll(String responseBody) throws IOException{
        JsonNode items = getJsonNode(responseBody);
        List<StockData> stockDataList = new ArrayList<>();

        for (JsonNode item : items) {
            StockData stockData = extractStockData(item);
            stockDataList.add(stockData);
        }

        return stockDataList;
    }

    private StockData extractStockData(final JsonNode item) {
        String stockName = item.path(ApiMessage.STOCK_NAME.name()).asText();  // 주식 이름
        int stockPrice = item.path(ApiMessage.STOCK_PREV.name()).asInt();        // 현재 주가
        double prevChangeRate = item.path(ApiMessage.PREV_CHANGE_PRICE.name()).asDouble(); // 전일 대비 등락률

        return new StockData(stockName, stockPrice, prevChangeRate);
    }

    private JsonNode getJsonNode(final String responseBody) throws JsonProcessingException {
        JsonNode rootNode = objectMapper.readTree(responseBody);
        return rootNode.path("output");
    }
}
