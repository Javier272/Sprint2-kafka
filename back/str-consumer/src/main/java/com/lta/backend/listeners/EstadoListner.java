package com.lta.backend.listeners;

import lombok.extern.log4j.Log4j2;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.annotation.TopicPartition;
import org.springframework.stereotype.Component;

@Log4j2
@Component

public class EstadoListner {
    @KafkaListener(groupId = "group-1",
            topicPartitions = @TopicPartition(topic = "estado-paciente",partitions = {"0"})
            ,containerFactory = "validMessageContainerFactory")
    public void listenerEstado(String message){
        log.info("Consulta de estado", message);
    }

    @KafkaListener(groupId = "group-1",
            topicPartitions = @TopicPartition(topic = "estado-paciente",partitions = {"1"})
            ,containerFactory = "validMessageContainerFactory")
    public void listenerHistorial(String message){
        log.info("Consulta de historial");
    }
}
