package com.example.investment_api.search.base.dashboard.dto;

public record DashboardData(
        String rank,
        String stockName,
        String stockPrice,
        String prevChangePrice, //전일 대비가
        String prevChangeRate, //전일 대비율
        String marketCapitalization,
        String tradingVolume
) {
}
