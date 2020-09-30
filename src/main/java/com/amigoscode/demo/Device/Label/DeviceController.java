package com.amigoscode.demo.Device.Label;

import com.amigoscode.demo.DemoApplication;
import com.amigoscode.demo.advertisement.AdvertisementService;
import org.apache.tomcat.util.json.JSONParser;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("api/device")
@CrossOrigin(origins = "*")
public class DeviceController {

    private final AdvertisementService advertisementService;

    @Autowired
    public DeviceController(AdvertisementService advertisementService) {
        this.advertisementService = advertisementService;
    }

    @GetMapping(value = "/esl/query")
    public ResponseEntity<String> queryEslLabelData() {

        final String uri = "http://esl.ylwlesl.com:9191/V2/pub/label/query?page=1&size=1000&storeUuid=0108";

        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        Map<String, String> headerContent = new HashMap<>();
        headerContent.put("Authorization", DemoApplication.token);
        headers.setAll(headerContent);

        HttpEntity<Map<String, Object>> entity = new HttpEntity<>(new HashMap<>(), headers);

        ResponseEntity<String> response = restTemplate.postForEntity(uri, entity, String.class);

        try{
            JSONObject resonseBody = new JSONObject(response.getBody().toString());
            System.out.println(resonseBody);
            JSONArray deviceArray = resonseBody.getJSONObject("body").getJSONArray("list");
            for(int i = 0; i < deviceArray.length(); i ++){
                String deviceMac = deviceArray.getJSONObject(i).optString("mac");
                if(!advertisementService.isDeviceInDB(deviceMac)){
                    advertisementService.recordNewDevice(deviceMac);
                }
            }
        }catch(JSONException e){
            e.printStackTrace();
        }

//        return response;
        return new ResponseEntity<>(response.getBody(), HttpStatus.OK);
    }

    @GetMapping(value = "/esl/query-binding")
    public ResponseEntity<String> queryBindingData(@RequestParam("mac") String mac)  {

        final String uri = "http://esl.ylwlesl.com:9191/V2/label/query?mac=" + mac;

        RestTemplate restTemplate = new RestTemplate();

        MultiValueMap<String, String> headers = new LinkedMultiValueMap<>();
        headers.add("Authorization", DemoApplication.token);

        ResponseEntity<String> response = restTemplate.exchange(uri, HttpMethod.GET, new HttpEntity<Object>(headers), String.class);
//        return response;
        return new ResponseEntity<>(response.getBody(), HttpStatus.OK);
    }

    @PostMapping(value = "/esl/update-binding")
    public ResponseEntity<String> updateDeviceBinding(@RequestBody UpdateBindingRequest updateBindingRequest) throws JSONException {

        final String uri = "http://esl.ylwlesl.com:9191/V2/pub/binding/update";

        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();
        Map<String, String> headerContent = new HashMap<>();
        headerContent.put("Authorization", DemoApplication.token);
        headers.setAll(headerContent);

        Map<String, Object> body = new HashMap<>();
        body.put("mac", updateBindingRequest.getMac());
        body.put("demoId", updateBindingRequest.getDemoId());
        body.put("information", updateBindingRequest.getInformation());
        body.put("storeUuid", "0108");

        HttpEntity<Map<String, Object>> entity = new HttpEntity<>(body, headers);

        ResponseEntity<String> response = restTemplate.postForEntity(uri, entity, String.class);
//        return response;
        return new ResponseEntity<>(response.getBody(), HttpStatus.OK);
    }



}
