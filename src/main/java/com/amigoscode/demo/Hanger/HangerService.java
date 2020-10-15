package com.amigoscode.demo.Hanger;

import com.amigoscode.demo.Hanger.DAO.HangerProduct;
import com.amigoscode.demo.Hanger.DAO.HangerTemplate;
import com.amigoscode.demo.Hanger.Request.*;
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

    List<HangerProduct> queryProductsByTemplateId(String templateId){
        return hangerDataAccessService.queryProductsByTemplateId(templateId);
    }

    HangerProduct queryProduct(String barcode){
        return this.hangerDataAccessService.queryProduct(barcode);
    }

    List<HangerTemplate> queryTemplates(){
        return this.hangerDataAccessService.queryTemplates();
    }

    HangerTemplate queryTemplate(String templateId){return hangerDataAccessService.queryTemplate(templateId);}

    Boolean createProduct(HangerProduct product){
        return this.hangerDataAccessService.createProduct(product);
    }

    Boolean updateProduct(UpdateHangerProductRequest updateHangerProductRequest){
        return this.hangerDataAccessService.updateProduct(updateHangerProductRequest);
    }

    Boolean removeProduct(RemoveProductsRequest removeProductsRequest){
        return this.hangerDataAccessService.removeProduct(removeProductsRequest);
    }

    Boolean createTemplate(CreateTemplateRequest createTemplateRequest){
        return this.hangerDataAccessService.createTemplate(createTemplateRequest);
    }

    Boolean updateTemplate(UpdateTemplateRequest updateTemplateRequest){
        return this.hangerDataAccessService.updateTemplate(updateTemplateRequest);
    }

    Boolean removeTemplates(RemoveTemplatesRequest removeTemplatesRequest){
        List<String> templateIds = removeTemplatesRequest.getTemplateIds();
        return this.hangerDataAccessService.removeTemplates(templateIds);
    }

    Boolean productSwitchTemplate(List<String> barcodes, String templateId){
        return this.hangerDataAccessService.productSwitchTemplate(barcodes, templateId);
    }

    void syncLabelInfo(String eslCode, String productCode){
        String productCodeToRecord = productCode;
        String labelType = "21B";
        if(eslCode.charAt(2) == '3') labelType = "21R";

        if(!hangerDataAccessService.containsProduct(productCode)){
            productCodeToRecord = "UNKNOWN";
        }

        hangerDataAccessService.insertOrUpdateLabel(eslCode, productCodeToRecord, labelType);
    }


}
