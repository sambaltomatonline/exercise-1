package id.springboot.example.example1.controller;

import id.springboot.example.example1.model.*;
import id.springboot.example.example1.repository.AuthorRepository;
import id.springboot.example.example1.repository.BookRepository;
import id.springboot.example.example1.repository.CategoryRepository;
import id.springboot.example.example1.repository.OrderRepository;
import id.springboot.example.example1.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Slf4j
@RestController
@RequestMapping(path = "/order-api")
public class OrderController {

    @Autowired
    OrderService orderService;

    @Autowired
    OrderRepository orderRepository;

    @Autowired
    CategoryRepository categoryRepository;

    @Autowired
    AuthorRepository authorRepository;

    @Autowired
    BookRepository bookRepository;

    private void init(){

        log.info("init called");

        Category kategori1 = new Category("Fiksi");
        Category kategori2 = new Category("Non Fiksi");

        kategori1 = categoryRepository.save(kategori1);
        kategori2 = categoryRepository.save(kategori2);

        Author author1 = new Author("Author 1","email.author1@gmail.com","M");
        Author author2 = new Author("Author 2","email.author2@gmail.com","F");

        author1 = authorRepository.save(author1);
        author2 = authorRepository.save(author2);

        Book book1 = new Book("Book 1","Book 1 Description", kategori1.getId(), author1.getId());
        Book book2 = new Book("Book 2","Book 2 Description", kategori2.getId(), author1.getId());
        Book book3 = new Book("Book 3","Book 3 Description", kategori2.getId(), author2.getId());

        bookRepository.save(book1);
        bookRepository.save(book2);
        bookRepository.save(book3);

    }

    @GetMapping(path = "/demoOrder")
    public ResponseEntity<?> demoOrder(){

        List<OrderDetail> orderList = new ArrayList<>();

        Date now = Calendar.getInstance().getTime();

        OrderDetail orderDetail1 = new OrderDetail(1l,5,new BigDecimal("10000"));
        BigDecimal totalPerItem1 = orderDetail1.getPrice().multiply(new BigDecimal(orderDetail1.getQuantity()));
        orderDetail1.setTotal(totalPerItem1);

        OrderDetail orderDetail2 = new OrderDetail(1l,10,new BigDecimal("7000"));
        BigDecimal totalPerItem2 = orderDetail2.getPrice().multiply(new BigDecimal(orderDetail2.getQuantity()));
        orderDetail2.setTotal(totalPerItem2);

        orderList.add(orderDetail1);
        orderList.add(orderDetail2);

        BigDecimal totalOrder = BigDecimal.ZERO;
        Order order = new Order();

        for (OrderDetail detail : orderList) {
            totalOrder = totalOrder.add(detail.getTotal());
        }

        order.setCreatedDate(now);
        order.setTotal(totalOrder);

        Order newOrder;

        try {
            newOrder = orderService.prosesOrder(order, orderList);
        } catch (Exception e) {
            log.error("Gagal Order : "+e.getMessage());
            e.printStackTrace();
            return new ResponseEntity<>("Gagal Order : "+e.getMessage(), HttpStatus.CREATED);
        }

        return new ResponseEntity<>(newOrder, HttpStatus.CREATED);
    }

}
