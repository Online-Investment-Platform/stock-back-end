package com.example.investment_api.home.news.controller;

import com.example.investment_api.home.news.controller.dto.NewsResponse;

import com.example.investment_api.home.news.service.NewsService;

import org.json.JSONException;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/home/news")
public class NewsController {

    private final NewsService newsService;

    public NewsController(final NewsService newsService) {
        this.newsService = newsService;
    }

    @GetMapping
    public List<NewsResponse> getNews() throws JSONException, IOException {
        return newsService.getNewsResponses();
    }
}
