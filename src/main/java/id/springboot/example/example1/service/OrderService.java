package id.springboot.example.example1.service;

import id.springboot.example.example1.model.Order;
import id.springboot.example.example1.model.OrderDetail;
import id.springboot.example.example1.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.validation.ConstraintViolationException;
import java.util.List;

@Service
public class OrderService {

    @Autowired
    OrderRepository orderRepository;

    @Autowired
    EntityManager entityManager;

    @Transactional(rollbackFor = ConstraintViolationException.class)
    public Order prosesOrder(Order order, List<OrderDetail> orderDetails){

        Order newOrder = orderRepository.save(order);

        for (OrderDetail detail : orderDetails) {
            detail.setOrder(newOrder);
            detail.setOrder_id(newOrder.getId());
        }

        newOrder.setOrderDetails(orderDetails);

        orderRepository.save(newOrder);

        return orderRepository.findById(newOrder.getId()).get();
    }


}
