package com.example.creditospreaprobadossqs.model;

public record Libranza(
    String prestamo,
    String consumido,
    String documento,
    String vencimiento
) {
}
