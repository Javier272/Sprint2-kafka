package com.lta.backend.services;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

@Service
@Log4j2
public class EliminacionRegistroService {
    public void EliminacionRegistro(String message){
        log.info("Eliminacion de registro", message);
    }
}
