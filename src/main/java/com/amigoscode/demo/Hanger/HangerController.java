package com.amigoscode.demo.Hanger;

import com.amigoscode.demo.Hanger.DAO.HangerProduct;
import com.amigoscode.demo.Hanger.DAO.HangerTemplate;
import com.amigoscode.demo.Hanger.Request.RemoveProductsRequest;
import com.amigoscode.demo.Hanger.Request.UpdateHangerProductRequest;
import com.amigoscode.demo.Hanger.Response.TotalCountsResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

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

    @GetMapping(value = "/label/query")
    @ResponseBody
    public TotalCountsResponse queryLabels() {
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



}
