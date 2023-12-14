package com.gameshub.service.request.approve;

public interface ProductApprovalStrategy {

    void approveAndCreateProduct(int requestId);

    void approvedAndUpdateProduct(int requestId, int productId);

}