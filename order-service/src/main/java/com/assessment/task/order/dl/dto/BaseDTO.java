package com.assessment.task.order.dl.dto;

import java.io.IOException;
import java.io.Serializable;
import java.time.ZonedDateTime;

public abstract class BaseDTO<D, E> implements Serializable {
    public Long id;
    public ZonedDateTime createdOn;
    public ZonedDateTime updatedOn;

    public abstract E convertToEntity() throws IOException;

    public abstract void convertToDTO(E entity, boolean partialFill);

    public abstract D convertToNewDTO(E entity, boolean partialFill);

}
