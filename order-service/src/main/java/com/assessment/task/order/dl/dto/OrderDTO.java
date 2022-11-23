package com.assessment.task.order.dl.dto;

import com.assessment.task.order.dl.entity.Order;
import com.assessment.task.order.dl.enums.OrderStatus;
import com.assessment.task.order.dl.enums.OrderType;
import com.assessment.task.order.dl.enums.TypeOfDrink;
import com.assessment.task.order.dl.enums.TypeOfMilk;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.ZonedDateTime;

@Data
public class OrderDTO extends BaseDTO<OrderDTO, Order> implements Serializable {

    private String orderNumber;
    private TypeOfDrink typeOfDrink;
    private TypeOfMilk typeOfMilk;
    private OrderType orderType;
    private BigDecimal price;
    private OrderStatus status;

    @Override
    public Order convertToEntity() {
        Order order = new Order();
        order.setId(this.id);
        order.setOrderNumber(this.orderNumber);
        order.setOrderType(this.orderType);
        order.setPrice(this.price);
        order.setTypeOfDrink(this.typeOfDrink);
        order.setTypeOfMilk(this.typeOfMilk == null ? TypeOfMilk.NULL : this.typeOfMilk);
        order.setStatus(this.status == null ? OrderStatus.WAITING : this.status);
        order.setCreatedOn(ZonedDateTime.now());
        order.setUpdatedOn(ZonedDateTime.now());
        return order;
    }

    @Override
    public void convertToDTO(Order entity, boolean partialFill) {
        this.id = entity.getId();
        this.orderNumber = entity.getOrderNumber();
        this.orderType = entity.getOrderType();
        this.price = entity.getPrice();
        this.typeOfDrink = entity.getTypeOfDrink();
        this.typeOfMilk = entity.getTypeOfMilk();
        this.status = entity.getStatus();
        this.createdOn = entity.getCreatedOn();
        this.updatedOn = entity.getUpdatedOn();
    }

    @Override
    public OrderDTO convertToNewDTO(Order entity, boolean partialFill) {
        OrderDTO orderDTO = new OrderDTO();
        orderDTO.convertToDTO(entity, partialFill);
        return orderDTO;
    }
}
