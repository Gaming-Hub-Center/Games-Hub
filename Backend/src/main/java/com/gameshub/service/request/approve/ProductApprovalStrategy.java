package com.gameshub.service.request.approve;

import com.gameshub.model.request.ProductRequestDAO;

public interface ProductApprovalStrategy {

    void approveAndCreateProduct(int requestId);

    void approvedAndUpdateProduct(int requestId, int productId);

}