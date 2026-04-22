package com.lta.backend.services;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

@Service
@Log4j2
public class ConsultaHistorialService {
    public void ConsultaHistorial(String message){
        log.info("Consulta de historial", message);
    }
}
