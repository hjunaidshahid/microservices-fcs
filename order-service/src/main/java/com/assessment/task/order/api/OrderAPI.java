package com.assessment.task.order.api;

import com.assessment.task.order.dl.dto.OrderDTO;
import com.assessment.task.order.dl.enums.OrderStatus;
import com.assessment.task.order.service.OrderService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("/order")
@Log4j2
public class OrderAPI {
    @Autowired
    private OrderService orderService;

    /**
     * Adding an order
     * @param(HttpServletRequest request, OrderDTO orderRequest)
     * @return mixed
     */
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public ResponseEntity<?> createOrder(HttpServletRequest request, @RequestBody OrderDTO orderRequest) {
        OrderDTO orderDTO;
        try{
            orderDTO = orderService.newOrder(orderRequest);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(orderDTO, HttpStatus.CREATED);
    }

    /**
     * Cancelling an order
     * @param(HttpServletRequest  request)
     * @param(Long id)
     * @return mixed
     */
    @RequestMapping(value = "/cancel/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<?> cancelOrderById(HttpServletRequest request,
                                             @PathVariable(name = "id") Long id) {
        String message;
        try{
            message = orderService.cancelOrderById(id);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>( message, HttpStatus.OK);
    }

    /**
     * List of pending orders with their current status
     * @param(HttpServletRequest request)
     * @return mixed
     */
    @RequestMapping(value = "/pending-orders", method = RequestMethod.GET)
    public ResponseEntity<?> getAllPendingOrders(HttpServletRequest request) {
        List<OrderDTO> orderDTOList;
        try{
            orderDTOList = orderService.getAllPendingOrders();
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(orderDTOList, HttpStatus.OK);
    }

    /**
     * update status by barista service
     */
    @RequestMapping(value = "/update-status/{id}/{status}", method = RequestMethod.PUT)
    public ResponseEntity<?> updateOrderStatus(HttpServletRequest request, @PathVariable(name = "id") Long id,
                                               @PathVariable(name = "status") OrderStatus status) {
        OrderDTO orderDTO;
        try{
            log.info("Call updateOrderStatus func");
            orderDTO = orderService.updateOrderStatusById(id, status);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>( orderDTO, HttpStatus.OK);
    }
}
