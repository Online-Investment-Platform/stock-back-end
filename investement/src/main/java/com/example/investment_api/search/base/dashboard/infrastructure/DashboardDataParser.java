package com.example.investment_api.search.base.dashboard.infrastructure;

import com.example.investment_api.search.base.dashboard.dto.DashboardData;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Component
public class DashboardDataParser {

    private final ObjectMapper objectMapper;

    public DashboardDataParser(final ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    public List<DashboardData> parse(String responseBody) throws IOException {
        JsonNode items = getJsonNode(responseBody);
        return extractStockData(items);
    }

    private List<DashboardData> extractStockData(final JsonNode items) {
        List<DashboardData> stockDataList = new ArrayList<>();
        Iterator<JsonNode> elements = items.elements();

        buildDataList(stockDataList, elements);
        return stockDataList;
    }

    private void buildDataList(List<DashboardData> marketCapitalizationDTOList, Iterator<JsonNode> elements) {
        while (elements.hasNext()) {
            JsonNode marketCapitalizationOutput = elements.next();
            String rank = marketCapitalizationOutput.path("data_rank").asText();
            String stockPrice = marketCapitalizationOutput.path("stck_prpr").asText();
            String stockName = marketCapitalizationOutput.path("hts_kor_isnm").asText();
            String prevChangePrice = marketCapitalizationOutput.path("prdy_vrss").asText();
            String prevChangeRate = marketCapitalizationOutput.path("prdy_ctrt").asText();
            String tradingVolume = marketCapitalizationOutput.path("acml_vol").asText();
            String marketCapitalization = marketCapitalizationOutput.path("stck_avls").asText();

            marketCapitalizationDTOList.add(new DashboardData(rank, stockName, stockPrice, prevChangePrice, prevChangeRate, marketCapitalization, tradingVolume));
        }
    }

    private JsonNode getJsonNode(final String responseBody) throws JsonProcessingException {
        JsonNode rootNode = objectMapper.readTree(responseBody);
        return rootNode.path("output");
    }
}
