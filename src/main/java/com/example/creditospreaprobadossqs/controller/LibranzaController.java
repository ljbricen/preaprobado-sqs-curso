package com.example.creditospreaprobadossqs.controller;

import com.example.creditospreaprobadossqs.model.Libranza;
import com.example.creditospreaprobadossqs.services.LibranzaSQSService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/libranza-promociones")
@AllArgsConstructor
public class LibranzaController {

    private final LibranzaSQSService service;

    private static final String QUEUE_LIBRANZA_PROMOCIONES = "libranza-promociones";

    @PostMapping
    public Mono<String> sendMessageQueue(@RequestBody Libranza libranza) {
        return Mono.just(service.publishStandardQueueMessage(QUEUE_LIBRANZA_PROMOCIONES, libranza));
    }
}
