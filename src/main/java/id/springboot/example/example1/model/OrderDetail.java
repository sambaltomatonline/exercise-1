package id.springboot.example.example1.model;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class OrderDetail {

    private Long id;
    private Order orderId;
    private Book book;
    private Integer quantity;
    private BigDecimal price;
    private BigDecimal total;
}
