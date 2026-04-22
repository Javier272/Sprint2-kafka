package com.lta.backend.services;

import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

@Service
@Log4j2
public class CreacionCitaService {
    public void CreacionCita(String message){
        log.info("Creacion de cita", message);
    }
}
