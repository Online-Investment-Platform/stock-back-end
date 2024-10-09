package com.example.investment_api.search.base.dashboard.service;

import com.example.investment_api.home.marketCapitalization.service.client.MarketCapitalizationFetcher;

import com.example.investment_api.search.base.dashboard.dto.DashboardData;
import com.example.investment_api.search.base.dashboard.infrastructure.DashboardDataParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
public class DashboardService {

    private final MarketCapitalizationFetcher marketCapitalizationFetcher;
<<<<<<< HEAD:investement/src/main/java/com/example/investment_api/search/base/dashboard/service/DashboardService.java
    private final DashboardDataParser dataParser;
=======

    private final StockDataParser stockDataParser;
>>>>>>> main:investement/src/main/java/com/example/investment_api/searchHome/service/StockDataService.java

    @Autowired
    public DashboardService(final MarketCapitalizationFetcher marketCapitalizationFetcher, final DashboardDataParser dataParser) {
        this.marketCapitalizationFetcher = marketCapitalizationFetcher;
        this.dataParser = dataParser;
    }

    public List<DashboardData> getDashboardData(int page, int size) throws IOException {
        ResponseEntity<String> response = getStringResponseEntity();
        List<DashboardData> allStockData = dataParser.parse(response.getBody());

        int start = (page - 1) * size;
        int end = Math.min(start + size, allStockData.size());

        return allStockData.subList(start, end);
    }

    private ResponseEntity<String> getStringResponseEntity() {
        return marketCapitalizationFetcher.marketCapitalizationData();
    }
}
