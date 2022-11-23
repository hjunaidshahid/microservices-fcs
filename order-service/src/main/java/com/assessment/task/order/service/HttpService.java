package com.assessment.task.order.service;

import com.assessment.task.order.dl.dto.OrderDTO;
import com.assessment.task.order.util.AppUtility;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Log4j2
@Service
public class HttpService {

    @Autowired
    private WebClient.Builder webClientBuilder;

    public String notifyToBaristaService(OrderDTO orderDTO) {
        log.info("Notify to barista service that new order is placed.");
        String uri = AppUtility.getAPIUrl( "barista.service.notify.new.order.is.placed");
        return webClientBuilder.build().post()
                .uri(uri)
                .body(Mono.just(orderDTO), OrderDTO.class)
                .retrieve()
                .bodyToMono(String.class)
                .block();
    }

}
