package com.assessment.task.order.repository;

import com.assessment.task.order.dl.entity.Order;
import com.assessment.task.order.dl.enums.OrderStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {

    List<Order> findByStatusIsNot(OrderStatus status);

}
