package id.springboot.example.example1.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "t_order_detail")
public class OrderDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id", insertable = false, updatable = false)
    @JsonIgnore
    @ToString.Exclude
    private Order order;

    @NotNull
    private Long order_id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "book_id", insertable = false, updatable = false)
    @JsonIgnore
    @ToString.Exclude
    private Book book;

    @NonNull @NotNull
    private Long book_id;

    @NonNull @NotNull
    private Integer quantity;

    @NonNull @NotNull
    private BigDecimal price;

    @NotNull
    private BigDecimal total;
}
