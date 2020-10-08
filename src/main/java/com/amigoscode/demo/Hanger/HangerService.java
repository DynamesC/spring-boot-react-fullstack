package com.amigoscode.demo.Hanger;

import com.amigoscode.demo.Hanger.DAO.HangerProduct;
import com.amigoscode.demo.Hanger.DAO.HangerTemplate;
import com.amigoscode.demo.Hanger.Request.RemoveProductsRequest;
import com.amigoscode.demo.Hanger.Request.UpdateHangerProductRequest;
import com.amigoscode.demo.Hanger.Response.TotalCountsResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HangerService {
    private final HangerDataAccessService hangerDataAccessService;

    @Autowired
    public HangerService(HangerDataAccessService hangerDataAccessService) {
        this.hangerDataAccessService = hangerDataAccessService;
    }

    TotalCountsResponse queryTotalCount(){
        return hangerDataAccessService.queryTotalCount();
    }

    List<HangerProduct> queryProducts(int size, int page){
        int start = (page - 1) * size;
        if( start < 0 ) start = 0;
        int end = size * page;
        return this.hangerDataAccessService.queryProducts(start, end);
    }

    List<HangerTemplate> queryTemplates(){
        return this.hangerDataAccessService.queryTemplates();
    }

    Boolean createProduct(HangerProduct product){
        return this.hangerDataAccessService.createProduct(product);
    }

    Boolean updateProduct(UpdateHangerProductRequest updateHangerProductRequest){
        return this.hangerDataAccessService.updateProduct(updateHangerProductRequest);
    }

    Boolean removeProduct(RemoveProductsRequest removeProductsRequest){
        return this.hangerDataAccessService.removeProduct(removeProductsRequest);
    }
}
