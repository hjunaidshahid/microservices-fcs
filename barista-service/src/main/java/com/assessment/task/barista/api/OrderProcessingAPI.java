package com.assessment.task.barista.api;


import com.assessment.task.barista.dl.dto.OrderDTO;
import com.assessment.task.barista.service.OrderProcessingService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Log4j2
@RestController
@RequestMapping("/barista")
public class OrderProcessingAPI {

    @Autowired
    private OrderProcessingService orderProcessingService;

    /**
     * this thread call by order service
     */
    @RequestMapping(value = "/new-order", method = RequestMethod.POST)
    public ResponseEntity<?> newOrderIsPlaced(HttpServletRequest request, @RequestBody OrderDTO orderDTO) {
        log.info("New order is placed for processing ---> "+orderDTO);
        return new ResponseEntity<>("Thanks for updating us", HttpStatus.OK);
    }

    /**
     * update order state
     */
    @RequestMapping(value = "/update-order-state/{id}/{state}", method = RequestMethod.PUT)
    public ResponseEntity<?> updateOrderState(HttpServletRequest request, @PathVariable(name = "id") Long id,
                                              @PathVariable(name = "state") String state) {
        OrderDTO orderDTO = null;
        log.info("Update order state");
        try {
            orderDTO = orderProcessingService.updateOrderState(id, state);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<>(orderDTO, HttpStatus.OK);
    }
}
