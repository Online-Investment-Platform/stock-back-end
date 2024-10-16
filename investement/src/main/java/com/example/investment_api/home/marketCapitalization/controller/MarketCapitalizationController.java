package com.example.investment_api.home.marketCapitalization.controller;

import com.example.investment_api.home.marketCapitalization.controller.dto.MarketCapitalizationDTO;

import com.example.investment_api.home.marketCapitalization.service.MarketCapitalizationService;

import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/home/marketCapitalization")
public class MarketCapitalizationController {

    private final MarketCapitalizationService marketCapitalizationService;

    public MarketCapitalizationController(final MarketCapitalizationService marketCapitalizationService) {
        this.marketCapitalizationService = marketCapitalizationService;
    }

    @GetMapping
    @CrossOrigin(origins = {"http://13.209.73.178:80", "http://localhost:3000"})
    public ResponseEntity<List<MarketCapitalizationDTO>> getTradingVolume() throws IOException {
        return getListResponseEntity();
    }

    private ResponseEntity<List<MarketCapitalizationDTO>> getListResponseEntity() throws IOException {
        List<MarketCapitalizationDTO> marketCapitalizationDTOList = marketCapitalizationService.getMarketCapitalization();
        return ResponseEntity.ok(marketCapitalizationDTOList);
    }
}
