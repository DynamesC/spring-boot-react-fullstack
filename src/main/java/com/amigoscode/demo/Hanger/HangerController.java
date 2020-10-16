package com.amigoscode.demo.Hanger;

import com.amigoscode.demo.Hanger.DAO.HangerProduct;
import com.amigoscode.demo.Hanger.DAO.HangerTemplate;
import com.amigoscode.demo.Hanger.Request.*;
import com.amigoscode.demo.Hanger.Response.TotalCountsResponse;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("api/hanger")
@CrossOrigin(origins = "*")
public class HangerController {

    private final HangerService hangerService;
    private static final String sign = "esl5f839a3d27451";
    private static final String storeCode = "018800";

    @Autowired
    public HangerController(HangerService hangerService) {
        this.hangerService = hangerService;
    }

    @Scheduled(fixedRate = 300000)
    public void syncProducts(){

        int page = 1;
        int size = 10000;

        List<HangerProduct> localProducts = queryProducts(size, page);
        List<String> localProductCodes = new ArrayList<>();

        for (int i = 0; i < localProducts.size(); i++) {
            localProductCodes.add(localProducts.get(i).getBarcode());
            createOrUpdateProductOnWolink(localProducts.get(i));
        }

        final String uri = String.format("http://n1.wolink.com.cn/api/HZADP/product/query?store_code=%s&f1=%d&f2=%d&sign=%s", storeCode, page, size, sign);

        RestTemplate restTemplate = new RestTemplate();
        MultiValueMap<String, String> headers = new LinkedMultiValueMap<>();
        ResponseEntity<String> response = restTemplate.exchange(uri, HttpMethod.GET, new HttpEntity<Object>(headers), String.class);

//        HttpHeaders headers = new HttpHeaders();
//        Map<String, String> headerContent = new HashMap<>();
//        headers.setAll(headerContent);
//        Map<String, Object> body = new HashMap<>();
//        HttpEntity<Map<String, Object>> entity = new HttpEntity<>(body, headers);
//
//        ResponseEntity<String> response = restTemplate.postForEntity(uri, entity, String.class);

        List<String> wolinkProductCodes = new ArrayList<>();

        try{
            JSONArray responseBody = new JSONArray(response.getBody().toString());
            System.out.println(responseBody);
            for (int i = 0; i < responseBody.length(); i++) {
                JSONObject labelInfo = (JSONObject) responseBody.get(i);
                String productCode = labelInfo.getString("product_code");
                wolinkProductCodes.add(productCode);
            }

            List<String> barcodesToBeRemoved = new ArrayList<>();

            for(String productCode: wolinkProductCodes){
                if(!localProductCodes.contains(productCode)){
                    barcodesToBeRemoved.add(productCode);
                }
            }

            if(barcodesToBeRemoved.size() > 0){
                removeProductOnWolink(new RemoveProductsRequest(barcodesToBeRemoved));
            }

        }catch(JSONException e){
            e.printStackTrace();
        }
    }



    @GetMapping(value = "/product/query")
    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    @ResponseBody
    public List<HangerProduct> queryProducts(@RequestParam("size") int size,
                                         @RequestParam("page") int page) {
        return hangerService.queryProducts(size, page);
    }

    @GetMapping(value = "/query_total_counts")
    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    @ResponseBody
    TotalCountsResponse queryTotalCounts() {
        TotalCountsResponse result =  hangerService.queryTotalCount();

        final String uri = String.format("http://n1.wolink.com.cn/api/HZADP/esl/query_count?store_code=%s&sign=%s", storeCode, sign);

        RestTemplate restTemplate = new RestTemplate();
        MultiValueMap<String, String> headers = new LinkedMultiValueMap<>();
        ResponseEntity<String> response = restTemplate.exchange(uri, HttpMethod.GET, new HttpEntity<Object>(headers), String.class);

        try{
            JSONObject body = new JSONObject(response.getBody().toString());
            int online_count = body.getInt("online_count");
            int offline_count = body.getInt("offline_count");
            result.setLabelCount(online_count + offline_count);
        }catch(JSONException e){
            e.printStackTrace();
        }
//        return response;

        //Handle 'UNKNOWN' product and template
        result.setProductCount(result.getProductCount() -1);
        result.setTemplateCount(result.getTemplateCount() -1);
        return result;
    }

    @PostMapping(value = "/product/create")
    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    Boolean createProduct(@Valid @RequestBody HangerProduct product) {
        boolean createProductOnWolinkSucceeded = createOrUpdateProductOnWolink(product);

        if(createProductOnWolinkSucceeded){
            return createProductOnLocalDB(product);
        } else{
            return false;
        }

    }

    public boolean createProductOnLocalDB(HangerProduct product){
        return hangerService.createProduct(product);
    }

    public boolean createOrUpdateProductOnWolink(HangerProduct product){
        final String uri = "http://n1.wolink.com.cn/api/HZADP/product/create";

        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();
        Map<String, String> headerContent = new HashMap<>();
        headers.setAll(headerContent);

        Map<String, Object> body = product.toBodyMap();
        body.put("store_code", storeCode);
        body.put("sign", sign);

        HttpEntity<Map<String, Object>> entity = new HttpEntity<>(body, headers);

        ResponseEntity<String> response = restTemplate.postForEntity(uri, entity, String.class);

        return response.getStatusCode() == HttpStatus.OK;
    }

    @PostMapping(value = "/product/update")
    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    Boolean updateProduct(@Valid @RequestBody UpdateHangerProductRequest updateHangerProductRequest) {
        HangerProduct localProductInfo = hangerService.queryProduct(updateHangerProductRequest.getBarcode());
        if(localProductInfo == null) return false;

        boolean templateChanged = !(localProductInfo.getTemplateId().equals(updateHangerProductRequest.getTemplateId()));

        boolean localUpdateSucceeded = hangerService.updateProduct(updateHangerProductRequest);

        if(localUpdateSucceeded){
            HangerProduct product = hangerService.queryProduct(updateHangerProductRequest.getBarcode());
            if(product == null) return false;

            boolean updateTemplateSucceeded = true;

            if(templateChanged) updateTemplateSucceeded = changeLabelTemplates(updateHangerProductRequest.getBarcode());
            return updateTemplateSucceeded && createOrUpdateProductOnWolink(product);
        }else{
            return false;
        }
    }

    Boolean changeLabelTemplates(String productBarcode){
        int page = 1;
        int size = 10000;

        final String uri = String.format("http://n1.wolink.com.cn/api/HZADP/esl/query?store_code=%s&f1=%d&f2=%d&sign=%s", storeCode, page, size, sign);

        RestTemplate restTemplate = new RestTemplate();
        MultiValueMap<String, String> headers = new LinkedMultiValueMap<>();
        ResponseEntity<String> response = restTemplate.exchange(uri, HttpMethod.GET, new HttpEntity<Object>(headers), String.class);

        List<String> labelCodesToUpdate = new ArrayList<>();

        try{
            JSONArray responseBody = new JSONArray(response.getBody().toString());
            System.out.println(responseBody);
            for (int i = 0; i < responseBody.length(); i++) {
                JSONObject labelInfo = (JSONObject) responseBody.get(i);
                if(labelInfo.getString("product_code").equals(productBarcode)){
                    labelCodesToUpdate.add(labelInfo.getString("esl_code"));
                }
            }
        }catch(JSONException e){
            e.printStackTrace();
        }
//        return response;
        return bindLabels(new BindLabelRequest(labelCodesToUpdate, productBarcode));

    }

    @PostMapping(value = "/template/update")
    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    Boolean updateTemplate(@Valid @RequestBody UpdateTemplateRequest updateTemplateRequest) {
        boolean localUpdateSucceeded = hangerService.updateTemplate(updateTemplateRequest);
        if(localUpdateSucceeded){
            try {
                List<HangerProduct> products = hangerService.queryProductsByTemplateId(updateTemplateRequest.getId());
                for(HangerProduct product: products){
                    changeLabelTemplates(product.getBarcode());
                }
                return true;
            }catch (Exception e){
                return false;
            }

        }else {
            return false;
        }
    }

    @PostMapping(value = "/product/remove")
    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    Boolean removeProduct(@Valid @RequestBody RemoveProductsRequest removeProductsRequest) {
        System.out.println(removeProductsRequest.getBarcodes());
        boolean removeProductOnWolinkSucceeded = removeProductOnWolink(removeProductsRequest);
        if(removeProductOnWolinkSucceeded){
            return removeProductOnLocalDB(removeProductsRequest);
        }else{
            return false;
        }
    }

    Boolean removeProductOnLocalDB(RemoveProductsRequest removeProductsRequest){
        return hangerService.removeProduct(removeProductsRequest);
    }

    Boolean removeProductOnWolink(RemoveProductsRequest removeProductsRequest){
        final String uri = "http://n1.wolink.com.cn/api/HZADP/product/deleteMultiple";

        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();
        Map<String, String> headerContent = new HashMap<>();
        headers.setAll(headerContent);

        Map<String, Object> body = new HashMap<>();
        body.put("store_code", storeCode);
        body.put("sign", sign);
        body.put("f1", removeProductsRequest.getBarcodes().toArray());

        HttpEntity<Map<String, Object>> entity = new HttpEntity<>(body, headers);

        ResponseEntity<String> response = restTemplate.postForEntity(uri, entity, String.class);

        return response.getStatusCode() == HttpStatus.OK;
    }

    @GetMapping(value = "/template/query")
    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    @ResponseBody
    public List<HangerTemplate> queryTemplates() {
        return hangerService.queryTemplates();
    }

    @PostMapping(value = "/template/create")
    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    Boolean createTemplate(@Valid @RequestBody CreateTemplateRequest createTemplateRequest) {
        return hangerService.createTemplate(createTemplateRequest);
    }

    @PostMapping(value = "/product/switch_template")
    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    Boolean productSwitchTemplate(@Valid @RequestBody ProductSwitchTemplateRequest productSwitchTemplateRequest) {
        boolean localDataUpdated = hangerService.productSwitchTemplate(productSwitchTemplateRequest.getBarcodes(), productSwitchTemplateRequest.getTemplateId());
        if(localDataUpdated){
            try{
                for(String productBarcode: productSwitchTemplateRequest.getBarcodes()){
                    changeLabelTemplates(productBarcode);
                }
                return true;
            }catch (Exception e){
                return false;
            }

        }else{
            return false;
        }
    }

    @PostMapping(value = "/template/remove")
    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    Boolean removeTemplate(@Valid @RequestBody RemoveTemplatesRequest removeTemplatesRequest) {
        return hangerService.removeTemplates(removeTemplatesRequest);
    }

    @GetMapping(value = "/label/query")
    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    public ResponseEntity<String> queryWoLinkLabelData(@RequestParam("size") int size,
                                                       @RequestParam("page") int page) {

        final String uri = String.format("http://n1.wolink.com.cn/api/HZADP/esl/query?store_code=%s&f1=%d&f2=%d&sign=%s", storeCode, page, size, sign);

        RestTemplate restTemplate = new RestTemplate();
        MultiValueMap<String, String> headers = new LinkedMultiValueMap<>();
        ResponseEntity<String> response = restTemplate.exchange(uri, HttpMethod.GET, new HttpEntity<Object>(headers), String.class);

        try{
            JSONArray responseBody = new JSONArray(response.getBody().toString());
            System.out.println(responseBody);
            for (int i = 0; i < responseBody.length(); i++) {
                JSONObject labelInfo = (JSONObject) responseBody.get(i);
                hangerService.syncLabelInfo(labelInfo.getString("esl_code"),
                        labelInfo.getString("product_code"));
            }

        }catch(JSONException e){
            e.printStackTrace();
        }
//        return response;
        return new ResponseEntity<>(response.getBody(), HttpStatus.OK);
    }

    @PostMapping(value = "/label/unbind")
    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    Boolean unbindLabels(@Valid @RequestBody UnbindLabelsRequest unbindLabelsRequest) {
        final String uri = "http://n1.wolink.com.cn/api/HZADP/esl/unbind";

        List<String> labelCodes = unbindLabelsRequest.getLabelCodes();

        boolean result = true;

        for(String labelCode: labelCodes){
            RestTemplate restTemplate = new RestTemplate();

            HttpHeaders headers = new HttpHeaders();
            Map<String, String> headerContent = new HashMap<>();
            headers.setAll(headerContent);

            Map<String, Object> body = new HashMap<>();
            body.put("store_code", storeCode);
            body.put("sign", sign);
            body.put("f1", labelCode);

            HttpEntity<Map<String, Object>> entity = new HttpEntity<>(body, headers);

            ResponseEntity<String> response = restTemplate.postForEntity(uri, entity, String.class);
            result = result && (response.getStatusCode() == HttpStatus.OK);
        }

        return result;
    }

    @PostMapping(value = "/label/sync_binding")
    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    Boolean syncLabelBindings() {
        final String uri = "http://n1.wolink.com.cn/api/HZADP/esl/bind_task";

        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();
        Map<String, String> headerContent = new HashMap<>();
        headers.setAll(headerContent);

        Map<String, Object> body = new HashMap<>();
        body.put("store_code", storeCode);
        body.put("sign", sign);

        HttpEntity<Map<String, Object>> entity = new HttpEntity<>(body, headers);

        ResponseEntity<String> response = restTemplate.postForEntity(uri, entity, String.class);
        System.out.println(response.getBody());
        return response.getStatusCode() == HttpStatus.OK ;
    }

    @PostMapping(value = "/label/bind")
    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    Boolean bindLabels(@Valid @RequestBody BindLabelRequest bindLabelRequest)  {
        final String uri = "http://n1.wolink.com.cn/api/HZADP/esl/bindMultiple";

        String productBarcode = bindLabelRequest.getProductBarcode();
        HangerProduct product = hangerService.queryProduct(productBarcode);
        if(product == null) return false;
        String templateId = product.getTemplateId();
        HangerTemplate template = hangerService.queryTemplate(templateId);
        if(template == null) return false;
        String wl_21r_id = template.getWl_21r_id();
        String wl_21b_id = template.getWl_21b_id();
        List<String> labelCodes = bindLabelRequest.getLabelCodes();

        RestTemplate restTemplate = new RestTemplate();
//        ClientHttpRequestFactory requestFactory = new
//                HttpComponentsClientHttpRequestFactory(HttpClients.createDefault());
//        RestTemplate restTemplate = new RestTemplate(requestFactory);

        HttpHeaders headers = new HttpHeaders();
        headers.add("Accept", MediaType.APPLICATION_JSON_VALUE);

        Map<String, Object> body = new HashMap<>();
        body.put("sign", sign);
        body.put("store_code", storeCode);

        List<Map<String, Object>> bindingInfos = new ArrayList<>();

        for(String labelCode: labelCodes){

            String woLinktemplateID = wl_21b_id;
            if(labelCode.charAt(2) == '3') woLinktemplateID = wl_21r_id;

            Map<String, Object> bindingInfo = new HashMap<>();
            bindingInfo.put("esl_code", labelCode);
            bindingInfo.put("product_code", productBarcode);
            bindingInfo.put("esltemplate_id", woLinktemplateID);

            bindingInfos.add(bindingInfo);
        }

        body.put("f1", bindingInfos.toArray());

        System.out.println(new JSONObject(body));

        HttpEntity<Map<String, Object>> entity = new HttpEntity<>(body, headers);
        System.out.println(entity.getBody().toString());
        ResponseEntity<String> response = restTemplate.postForEntity(uri, entity, String.class);

        System.out.println(response);

        return (response.getStatusCode() == HttpStatus.OK);
    }
}
