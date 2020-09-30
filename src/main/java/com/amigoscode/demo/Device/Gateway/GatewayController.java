package com.amigoscode.demo.Device.Gateway;


import com.amigoscode.demo.DemoApplication;
import org.json.JSONException;
import org.springframework.http.*;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("api/gateway")
@CrossOrigin(origins = "*")
public class GatewayController {
    @GetMapping(value = "/esl/query")
    public ResponseEntity<String> queryEslLabelData() {

        final String uri = "http://esl.ylwlesl.com:9191/V2/gateway?storeUuid=0108&page=1&size=1000";

        RestTemplate restTemplate = new RestTemplate();

        MultiValueMap<String, String> headers = new LinkedMultiValueMap<>();
        headers.add("Authorization", DemoApplication.token);

        ResponseEntity<String> response = restTemplate.exchange(uri, HttpMethod.GET, new HttpEntity<Object>(headers), String.class);

//        return response;
        return new ResponseEntity<>(response.getBody(), HttpStatus.OK);
    }

}
