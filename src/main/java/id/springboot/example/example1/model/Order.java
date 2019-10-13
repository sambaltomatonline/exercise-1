package id.springboot.example.example1.model;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
public class Order {

    private Long id;
    private Date createdDate;
    private BigDecimal total;

}
