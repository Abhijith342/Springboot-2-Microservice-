package com.app.Ordermcsrv.dto;

import java.math.BigDecimal;

// import com.app.Ordermcsrv.model.Product;
// import com.app.Ordermcsrv.model.Student;

import lombok.Data;

@Data
public class OrderRequest {
    // private Student student;
    // private Product product;
    private Integer studentId;
    private Integer productId;
    private Integer quantity;
    private BigDecimal totalAmount;


    
}
