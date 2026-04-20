package com.lta.backend.services;


import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;


@Log4j2
@Service
public class StringProducerService {

    // Inyección automática del componente KafkaTemplate que permite enviar mensajes a Kafka.
    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

  
    public void sendMessage(String topic,int partition, String message) {
        


        /*
         - topic: el nombre del tópico al que se enviará el mensaje.
         - partition: el número de partición específica dentro del tópico.
         - key: la clave del mensaje, que Kafka puede usar para determinar a qué partición enviarlo si no se especifica explícitamente una.
         - data: el contenido del mensaje, es decir, el valor.
         */
        // Se envía el mensaje al tópico y partición seleccionados.
        kafkaTemplate.send(topic, partition, "null", message).whenComplete((result, ex) -> {
            if (ex != null) {
                // Si ocurre una excepción, se registra un error.
                log.error("Error al enviar el mensaje: {}", ex.getMessage());
                return;
            }

            // Si el mensaje se envía correctamente, se registra una confirmación.
            log.info("Mensaje enviado con éxito a [{}] - partición {}: {}", topic, partition, message);
        });
    }

}


    /* 
    public void sendMessage(String message) {
        // Lógica para decidir a qué tópico enviar el mensaje
        String topic = message.toLowerCase().contains("topico 2") ? "topico-2" : "str-topic";

        kafkaTemplate.send(topic, message).whenComplete((result, ex) -> {
            if (ex != null) {
                log.error("Error al enviar el mensaje: {}", ex.getMessage());
                return;
            }

            log.info("Mensaje enviado con éxito a [{}]: {}", topic, result.getProducerRecord().value());
            log.info("Partición {}, Offset {}", 
                     result.getRecordMetadata().partition(), 
                     result.getRecordMetadata().offset());
        });
    }*/


