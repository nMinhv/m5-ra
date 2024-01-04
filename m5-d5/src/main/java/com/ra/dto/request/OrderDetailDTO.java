package com.ra.dto.request;

public class OrderDetailDTO {
    private Integer orderId;
    private Integer productId;
    private Integer quantity;

    public OrderDetailDTO() {
    }

    public OrderDetailDTO(Integer orderId, Integer productId, Integer quantity) {
        this.orderId = orderId;
        this.productId = productId;
        this.quantity = quantity;
    }

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
}
