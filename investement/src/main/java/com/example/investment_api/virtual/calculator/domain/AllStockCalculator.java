package com.example.investment_api.virtual.calculator.domain;

import com.example.investment_api.virtual.account.controller.dto.AccountStockData;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class AllStockCalculator {

    private final StockCalculator stockCalculator;

    public AllStockCalculator() {
        this.stockCalculator = new StockCalculator();
    }

    public double calculateTotalEvaluationProfit(List<AccountStockData> dtoList) {
        double totalEvaluationProfit = 0.0;
        for (AccountStockData calculationDTO : dtoList) {
            int currentPrice = calculationDTO.currentPrice();
            int buyPrice = calculationDTO.buyPrice();
            int stockCount = calculationDTO.stockCount();

            totalEvaluationProfit += stockCalculator.calculateEvaluationProfit(buyPrice, currentPrice, stockCount);
        }
        return totalEvaluationProfit; //평가 손익
    }

    public double calculateTotalPurchaseAmount(List<AccountStockData> dtoList) {
        int totalPurchaseAmount = 0;
        for (AccountStockData calculationDTO : dtoList) {
            int buyPrice = calculationDTO.buyPrice();
            int stockCount = calculationDTO.stockCount();
            totalPurchaseAmount += stockCalculator.calculatePurchaseAmount(buyPrice, stockCount);
        }
        return totalPurchaseAmount; //매입 금액
    }

    public int calculateTotalEvaluationAmount(List<AccountStockData> dtolist) {
        int totalEvaluationAmount = 0;
        for (AccountStockData calculationDTO : dtolist) {
            int currentPrice = calculationDTO.currentPrice();
            int stockCount = calculationDTO.stockCount();
            totalEvaluationAmount += stockCalculator.calculateEvaluationAmount(currentPrice, stockCount);
        }
        return totalEvaluationAmount; //평가 금액
    }

}
