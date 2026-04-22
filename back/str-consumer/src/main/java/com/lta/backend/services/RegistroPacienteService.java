package com.lta.backend.services;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

@Service
@Log4j2
public class RegistroPacienteService {
    public void RegistroPaciente(String message){
        log.info("Registro de paciente", message);
    }
}
