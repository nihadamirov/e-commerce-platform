package com.euphoria_ecommerce.dto;

import com.euphoria_ecommerce.enums.OrderStatus;
import com.euphoria_ecommerce.enums.PaymentMethod;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Date;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderDto {
    @Schema(accessMode = Schema.AccessMode.READ_ONLY)
    private Integer id;
    private Integer orderNumber;
    private Integer quantity;
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private LocalDateTime orderDate;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private Date deliveryDate;

    @Schema(accessMode = Schema.AccessMode.READ_ONLY)
    private Double totalPrice;
    private OrderStatus orderStatus;
    private PaymentMethod paymentMethod;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss")
    @Schema(accessMode = Schema.AccessMode.READ_ONLY)
    private LocalDateTime updateDate;
    private Long productId;
    private Long userId;
}
