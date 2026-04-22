package com.lta.backend.services;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

@Service
@Log4j2
public class ReprogramacionCitaService {
    public void ReprogramacionCita(String message){
        log.info("Reprogramacion de cita", message);
    }
}
