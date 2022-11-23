package com.assessment.task.barista.dl.dto;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
public class OrderDTO implements Serializable {
    private Long id;
    private String orderNumber;
    private String typeOfDrink;
    private String typeOfMilk;
    private String orderType;
    private BigDecimal price;
    private String status;

}
