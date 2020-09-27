package com.amigoscode.demo.Device;

import com.amigoscode.demo.DemoApplication;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("api/device")
@CrossOrigin(origins = "*")
public class DeviceController {

    @GetMapping(value = "/esl/query")
    public ResponseEntity<String> queryEslLabelData() throws JSONException {

        final String uri = "http://esl.ylwlesl.com:9191/V2/pub/label/query?page=1&size=1000&storeUuid=0108";

        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        Map<String, String> headerContent = new HashMap<>();
        headerContent.put("Authorization", DemoApplication.token);
        headers.setAll(headerContent);

        HttpEntity<Map<String, Object>> entity = new HttpEntity<>(new HashMap<>(), headers);

        ResponseEntity<String> response = restTemplate.postForEntity(uri, entity, String.class);
        return response;
//
//        String result = restTemplate.postForObject(uri, entity, String.class);
//
//        return result;

        // convert your result into json

//        JSONObject jsonResponse = null;
//        try {
//            jsonResponse = new JSONObject(result);
//            return jsonResponse;
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }
//
//        return jsonResponse;
    }
}
