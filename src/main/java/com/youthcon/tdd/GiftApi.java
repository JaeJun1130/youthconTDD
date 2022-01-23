package com.youthcon.tdd;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class GiftApi {
    private RestTemplate restTemplate = new RestTemplate();
    private final String URL = "http://youthcon.seok2.dev/apis/v1/send";

    private final Integer Amount = 1000;

    public Boolean send(String phoneNumber) {
        ResponseEntity<SendGiftResponseDto> response = restTemplate.postForEntity(URL,Amount,SendGiftResponseDto.class);
        return response.getStatusCode().is2xxSuccessful() ? true : false;
    }
}
