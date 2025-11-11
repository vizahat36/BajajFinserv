package com.bajaj.bajajfinserv.service;

import com.bajaj.bajajfinserv.model.WebhookRequest;
import com.bajaj.bajajfinserv.model.WebhookResponse;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.bajaj.bajajfinserv.util.SQLSolver;
import java.util.HashMap;
import java.util.Map;

@Service
public class WebhookService {

    private final RestTemplate restTemplate = new RestTemplate();

    public void executeFlow() {
        try {
           
            String url = "https://bfhldevapigw.healthrx.co.in/hiring/generateWebhook/JAVA";

            WebhookRequest request = new WebhookRequest(
                    "Mohammed Vijahath",
                    "U25UV23T006068",
                    "mohammed.vijahath@campusuvce.in"
            );

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            HttpEntity<WebhookRequest> entity = new HttpEntity<>(request, headers);

            ResponseEntity<WebhookResponse> response = restTemplate.exchange(
                    url, HttpMethod.POST, entity, WebhookResponse.class);

            WebhookResponse webhookResponse = response.getBody();
            if (webhookResponse == null) {
                System.out.println("Error: No response from webhook generation");
                return;
            }

            String webhookUrl = webhookResponse.getWebhook();
            String accessToken = webhookResponse.getAccessToken();

            System.out.println("Webhook URL: " + webhookUrl);
            System.out.println("Access Token: " + accessToken);

            // Step 2: Solve SQL Problem (Question 2 - even regNo)
            String finalQuery = SQLSolver.getEvenQuestionFinalQuery();

            submitSolution(webhookUrl, accessToken, finalQuery);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void submitSolution(String webhookUrl, String token, String query) {
        Map<String, String> body = new HashMap<>();
        body.put("finalQuery", query);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("Authorization", token);

        HttpEntity<Map<String, String>> entity = new HttpEntity<>(body, headers);
        ResponseEntity<String> response = restTemplate.exchange(webhookUrl, HttpMethod.POST, entity, String.class);

        System.out.println("Response from submission: " + response.getBody());
    }

}
