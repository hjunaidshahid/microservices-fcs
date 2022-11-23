package com.assessment.task.order.service;

import com.assessment.task.order.dl.dto.OrderDTO;
import com.assessment.task.order.dl.entity.Order;
import com.assessment.task.order.dl.enums.OrderStatus;
import com.assessment.task.order.repository.OrderRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Log4j2
@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private HttpService httpService;

    public List<OrderDTO> getAllPendingOrders() {
        List<OrderDTO> OrderDTOList = new ArrayList<>();
        List<Order> OrderList = orderRepository.findByStatusIsNot(OrderStatus.FINISHED);
        for (Order order : OrderList) {
            OrderDTO dto = new OrderDTO();
            dto.convertToDTO(order, false);
            OrderDTOList.add(dto);
        }
        return OrderDTOList;
    }

    public String cancelOrderById(Long id) {
        OrderDTO orderDTO = new OrderDTO();
        Optional<Order> order = orderRepository.findById(id);
        if(order.isPresent()) {
            orderDTO.convertToDTO(order.get(), false);
            if(orderDTO.getStatus().equals(OrderStatus.WAITING)) {
                orderRepository.deleteById(id);
                return "Order deleted successfully.";
            }
            return "Sorry, Your order is already "+orderDTO.getStatus();
        }
        return "No Record Found";
    }

    public OrderDTO newOrder(OrderDTO orderDTO) {
        Order order = orderRepository.save(orderDTO.convertToEntity());
        orderDTO.convertToDTO(order, false);
        httpService.notifyToBaristaService(orderDTO);
        return orderDTO;
    }

    public OrderDTO updateOrderStatusById(Long id, OrderStatus status) {
        OrderDTO orderDTO = new OrderDTO();
        Optional<Order> order = orderRepository.findById(id);
        if(order.isPresent()) {
            orderDTO.convertToDTO(order.get(), false);
            orderDTO.setStatus(status);
            if(orderDTO.getStatus().equals(OrderStatus.PICKED_UP)) {
                orderRepository.deleteById(id);
            } else {
                orderRepository.save(orderDTO.convertToEntity());
            }
        }
        return orderDTO;
    }

}

