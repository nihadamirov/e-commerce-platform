package com.euphoria_ecommerce.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RecentlyViewedProductsDto {
    @Schema(accessMode = Schema.AccessMode.READ_ONLY)
    private Integer id;

    @JsonFormat(shape = JsonFormat.Shape.STRING)
    @Schema(accessMode = Schema.AccessMode.READ_ONLY)
    private LocalDateTime viewedAt;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss")
    @Schema(accessMode = Schema.AccessMode.READ_ONLY)
    private LocalDateTime updateDate;
    private Long productId;
    private Long userId;
}
