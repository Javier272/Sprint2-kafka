package com.lta.backend.listeners;


import lombok.extern.log4j.Log4j2;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.annotation.TopicPartition;
import org.springframework.stereotype.Component;

@Log4j2
@Component
public class CitasListener {
    @KafkaListener(groupId = "group-1",
            topicPartitions = @TopicPartition(topic = "gestion-citas",partitions = {"0"})
            ,containerFactory = "validMessageContainerFactory")
    public void listenerCreacion(String message){
        log.info("Creacion de cita", message);
    }

    @KafkaListener(groupId = "group-1",
            topicPartitions = @TopicPartition(topic = "gestion-citas",partitions = {"1"})
            ,containerFactory = "validMessageContainerFactory")
    public void listenerCancelacion(String message){
        log.info("Cancelacion de cita", message);
    }

    @KafkaListener(groupId = "group-1",
            topicPartitions = @TopicPartition(topic = "gestion-citas",partitions = {"2"})
            ,containerFactory = "validMessageContainerFactory")
    public void listenerReprogramacion(String message){
        log.info("Reprogramacion de cita", message);
    }
}
