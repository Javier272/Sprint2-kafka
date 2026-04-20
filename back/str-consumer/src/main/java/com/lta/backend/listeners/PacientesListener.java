package com.lta.backend.listeners;


import lombok.extern.log4j.Log4j2;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.annotation.TopicPartition;
import org.springframework.stereotype.Component;

@Log4j2
@Component
public class PacientesListener {
    @KafkaListener(groupId = "group-1",
            topicPartitions = @TopicPartition(topic = "gestion-pacientes",partitions = {"0"})
            ,containerFactory = "validMessageContainerFactory")
    public void ListenerRegistro(String message){
        log.info("Registro de paciente", message);
    }

    @KafkaListener(groupId = "group-1",
            topicPartitions = @TopicPartition(topic = "gestion-pacientes",partitions = {"1"})
            ,containerFactory = "validMessageContainerFactory")
    public void listenerActualizar(String message){
        log.info("Actualizacion de informacion del paciente", message);
    }

    @KafkaListener(groupId = "group-1",
            topicPartitions = @TopicPartition(topic = "gestion-pacientes",partitions = {"2"})
            ,containerFactory = "validMessageContainerFactory")
    public void listenerEliminar(String message){
        log.info("Eliminacion de registro", message);
    }
}
