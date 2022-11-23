package com.assessment.task.order.dl.entity;

import com.assessment.task.order.dl.enums.TypeOfMilk;
import com.assessment.task.order.dl.enums.OrderStatus;
import com.assessment.task.order.dl.enums.OrderType;
import com.assessment.task.order.dl.enums.TypeOfDrink;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.EqualsAndHashCode;
import reactor.util.annotation.Nullable;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;

@Entity
@Table(name="ORDERS")
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@EqualsAndHashCode(callSuper = false)
public class Order extends BaseEntity implements Serializable {

    @Column(name = "ORDER_NUMBER")
    private String orderNumber;

    @Enumerated(EnumType.STRING)
    @Column(name = "TYPE_OF_DRINK")
    private TypeOfDrink typeOfDrink;

    @Nullable
    @Enumerated(EnumType.STRING)
    @Column(name = "TYPE_OF_MILK")
    private TypeOfMilk typeOfMilk;

    @Enumerated(EnumType.STRING)
    @Column(name = "ORDER_TYPE")
    private OrderType orderType;

    @Column(name = "PRICE")
    private BigDecimal price;

    @Enumerated(EnumType.STRING)
    @Column(name="STATUS")
    private OrderStatus status;
}
