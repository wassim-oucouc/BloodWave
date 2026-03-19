package org.example.bloodwave.application.service;

import org.example.bloodwave.application.dto.response.AdminAnalyticsResponse;
import org.springframework.stereotype.Service;

@Service
public interface AnalyticsService {

    AdminAnalyticsResponse getGlobalAnalytics();
}
