package com.example.investment.tradingVolume.service;

import com.example.investment.tradingVolume.controller.dto.TradingVolumeDTO;
import com.example.investment.tradingVolume.infrastructure.TradingVolumeParser;
import com.example.investment.tradingVolume.service.client.TradingVolumeFetcher;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import java.io.IOException;
import java.util.List;

@Service
@Transactional
public class TradingVolumeService {

    private final TradingVolumeFetcher tradingVolumeFetcher;
    private final TradingVolumeParser tradingVolumeParser;

    public TradingVolumeService(TradingVolumeFetcher tradingVolumeFetcher, TradingVolumeParser tradingVolumeParser) {
        this.tradingVolumeFetcher = tradingVolumeFetcher;
        this.tradingVolumeParser = tradingVolumeParser;
    }

    public List<TradingVolumeDTO> getTradingVolume() throws IOException {
        ResponseEntity<String> response = tradingVolumeFetcher.fetchTradingVolumeData();
        return tradingVolumeParser.getTradingVolume(response.getBody());
    }
}
