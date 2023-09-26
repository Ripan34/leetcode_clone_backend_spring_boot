package com.leetcode.leetcode_clone;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.HashMap;
import java.util.Map;

@Service
public class CodeSubmissionService {

    @Value("${judge0.api.key}")
    private String judge0ApiKey;

    @Value("${judge0.api.url}")
    private String judge0ApiUrl;

    public ResponseEntity<String> submitJavaScriptCode(String code) throws JsonProcessingException {
        HttpHeaders headers = new HttpHeaders();
        headers.set("content-type", "application/json");
        headers.set("Content-Type", "application/json");
        headers.set("X-RapidAPI-Key", judge0ApiKey);
        headers.set("X-RapidAPI-Host", "judge0-ce.p.rapidapi.com");

        Map<String, String> body = new HashMap<>();
        body.put("source_code", code);
        body.put("language_id", "63");

        String json = new ObjectMapper().writeValueAsString(body);
        HttpEntity<String> entity = new HttpEntity<>(json, headers);

        // Create a RestTemplate for making the request
        RestTemplate restTemplate = new RestTemplate();

        // Make the POST request to Judge0 API
        ResponseEntity<String> response = restTemplate.exchange(judge0ApiUrl, HttpMethod.POST, entity, String.class);
        return response;
    }
}