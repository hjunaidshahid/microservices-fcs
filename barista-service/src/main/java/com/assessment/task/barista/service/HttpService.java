package com.assessment.task.barista.service;

import com.assessment.task.barista.dl.dto.OrderDTO;
import com.assessment.task.barista.util.AppUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;

@Service
public class HttpService {

    @Autowired
    private WebClient.Builder webClientBuilder;

    public OrderDTO[] getAllOrders() {
        String uri = AppUtility.getAPIUrl( "order.service.get.orders");
        return webClientBuilder.build().get()
                .uri(uri)
                .retrieve()
                .bodyToMono(OrderDTO[].class)
                .block();
    }

    public OrderDTO updateOrderState(Long id, String state) {
        String uri = AppUtility.getAPIUrl( "order.service.update.status")+id+"/"+state;
        return webClientBuilder.build().put()
                .uri(uri)
                .retrieve()
                .bodyToMono(OrderDTO.class)
                .block();
    }

}