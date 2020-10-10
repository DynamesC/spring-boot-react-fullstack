package com.amigoscode.demo.Hanger;

import com.amigoscode.demo.DemoApplication;
import com.amigoscode.demo.Hanger.DAO.HangerProduct;
import com.amigoscode.demo.Hanger.DAO.HangerTemplate;
import com.amigoscode.demo.Hanger.Request.RemoveProductsRequest;
import com.amigoscode.demo.Hanger.Request.UpdateHangerProductRequest;
import com.amigoscode.demo.Hanger.Response.TotalCountsResponse;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("api/hanger")
@CrossOrigin(origins = "*")
public class HangerController {

    private final HangerService hangerService;

    @Autowired
    public HangerController(HangerService hangerService) {
        this.hangerService = hangerService;
    }

    @GetMapping(value = "/product/query")
    @ResponseBody
    public List<HangerProduct> queryProducts(@RequestParam("size") int size,
                                         @RequestParam("page") int page) {
        return hangerService.queryProducts(size, page);
    }

    @GetMapping(value = "/query_total_counts")
    @ResponseBody
    public TotalCountsResponse queryTotalCounts() {
        return hangerService.queryTotalCount();
    }

    @PostMapping(value = "/product/create")
    Boolean createProduct(@Valid @RequestBody HangerProduct product) {
        return hangerService.createProduct(product);
    }

    @PostMapping(value = "/product/update")
    Boolean createProduct(@Valid @RequestBody UpdateHangerProductRequest updateHangerProductRequest) {
        return hangerService.updateProduct(updateHangerProductRequest);
    }

    @PostMapping(value = "/product/remove")
    Boolean removeProduct(@Valid @RequestBody RemoveProductsRequest removeProductsRequest) {
        System.out.println(removeProductsRequest.getBarcodes());
        return hangerService.removeProduct(removeProductsRequest);
    }

    @GetMapping(value = "/template/query")
    @ResponseBody
    public List<HangerTemplate> queryTemplates() {
        return hangerService.queryTemplates();
    }

    @GetMapping(value = "/label/query")
    public ResponseEntity<String> queryWoLinkLabelData(@RequestParam("size") int size,
                                                       @RequestParam("page") int page) {
        String sign = "80805d794841f1b4";
        String storeId = "001";

        final String uri = String.format("http://new.wolink.com.cn/api/test/esl/query?store_code=%s&f1=%d&f2=%d&sign=%s", storeId, page, size, sign);

        RestTemplate restTemplate = new RestTemplate();

        MultiValueMap<String, String> headers = new LinkedMultiValueMap<>();

        ResponseEntity<String> response = restTemplate.exchange(uri, HttpMethod.GET, new HttpEntity<Object>(headers), String.class);
//        return response;
        return new ResponseEntity<>(response.getBody(), HttpStatus.OK);
    }



}
