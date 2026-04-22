package com.lta.backend.listeners;


import lombok.extern.log4j.Log4j2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.annotation.TopicPartition;
import org.springframework.stereotype.Component;

import com.lta.backend.services.ActualizacionPacienteService;
import com.lta.backend.services.EliminacionRegistroService;
import com.lta.backend.services.RegistroPacienteService;

@Log4j2
@Component
public class PacientesListener {
    @Autowired
    private RegistroPacienteService registroPacienteService;

    @Autowired
    private ActualizacionPacienteService actualizacionPacienteService;

    @Autowired
    private EliminacionRegistroService eliminacionRegistroService;


    @KafkaListener(groupId = "group-1",
            topicPartitions = @TopicPartition(topic = "gestion-pacientes",partitions = {"0"})
            ,containerFactory = "validMessageContainerFactory")
    public void ListenerRegistro(String message){
        log.info("Registro de paciente", message);
        registroPacienteService.RegistroPaciente(message);
    }

    @KafkaListener(groupId = "group-1",
            topicPartitions = @TopicPartition(topic = "gestion-pacientes",partitions = {"1"})
            ,containerFactory = "validMessageContainerFactory")
    public void listenerActualizar(String message){
        log.info("Actualizacion de informacion del paciente", message);
        actualizacionPacienteService.ActualizacionPaciente(message);
    }

    @KafkaListener(groupId = "group-1",
            topicPartitions = @TopicPartition(topic = "gestion-pacientes",partitions = {"2"})
            ,containerFactory = "validMessageContainerFactory")
    public void listenerEliminar(String message){
        log.info("Eliminacion de registro", message);
        eliminacionRegistroService.EliminacionRegistro(message);
    }
}
