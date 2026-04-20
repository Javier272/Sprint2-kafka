package com.lta.backend.Controllers;

import com.lta.backend.services.StringProducerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;



@RestController@RequestMapping("/producer")
public class StringProducerController {

    // Inyecta automáticamente una instancia del servicio que se encargará de enviar el mensaje a Kafka.
    @Autowired
    private StringProducerService stringProducerService;

    // Maneja las peticiones HTTP POST al endpoint "/producer".
    @PostMapping
    public ResponseEntity<?> sendMessage(@RequestBody String message) {
        // Llama al servicio para enviar el mensaje recibido en el cuerpo de la petición.
        stringProducerService.sendMessage(message);

        // Devuelve una respuesta HTTP.
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}

