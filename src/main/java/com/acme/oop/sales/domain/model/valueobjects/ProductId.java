package com.acme.oop.sales.domain.model.valueobjects;

import java.util.UUID;
import java.util.Objects;

/**
 * Value Object que representa el ID de un producto en el bounded context de ventas.
 * @param value
 *
 * @author Open-Source Application Development Team
 * @version 1.0
 */

public record ProductId(UUID value) {

    // Validaciones:
    public ProductId {
        if(Objects.isNull(value))
            throw new IllegalArgumentException("El ID del producto no puede ser nulo");
    }

    // Version del constructor sin parametros para que genere una UID:
    public ProductId(){
        this(UUID.randomUUID());
    }

}
