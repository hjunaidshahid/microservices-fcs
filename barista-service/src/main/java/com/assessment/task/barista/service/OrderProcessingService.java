package com.assessment.task.barista.service;

import com.assessment.task.barista.dl.dto.OrderDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
@Service
public class OrderProcessingService {

    @Autowired
    private HttpService httpService;

    public List<OrderDTO> getAllOrders() {
        return Arrays.asList(httpService.getAllOrders());
    }


    public OrderDTO updateOrderState(Long id, String state) {
        return httpService.updateOrderState(id, state);
    }
}
