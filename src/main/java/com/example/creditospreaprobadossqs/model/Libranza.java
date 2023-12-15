package com.example.creditospreaprobadossqs.model;

import io.swagger.v3.oas.annotations.media.Schema;

public record Libranza(
    String prestamo,
    String consumido,
    String documento,
    @Schema(description = "Fecha de vencimiento", example = "2023-12-31")
    String vencimiento
) {
}
