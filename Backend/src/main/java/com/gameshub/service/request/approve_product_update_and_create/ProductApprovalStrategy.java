package com.gameshub.service.request.approve_product_update_and_create;

import com.gameshub.model.request.ProductRequestDAO;

public interface ProductApprovalStrategy {

    void approveAndCreateProduct(int requestId);

    void approvedAndUpdateProduct(int requestId, int productId);

}
