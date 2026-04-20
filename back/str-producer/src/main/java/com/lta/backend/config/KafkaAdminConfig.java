package com.lta.backend.config;
import org.apache.kafka.clients.admin.AdminClientConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;
import org.springframework.kafka.core.KafkaAdmin;

//Importante ejecutar docker-compose up -d


import java.util.HashMap;

// Anotación que indica que esta clase contiene definiciones de beans para el contexto de Spring.
@Configuration
public class KafkaAdminConfig {

    // Inyección automática de las propiedades de configuración de Kafka definidas en application.properties o application.yml
    @Autowired
    private KafkaProperties kafkaProperties;

    // Define un bean KafkaAdmin que configura el cliente de administración de Kafka.
    @Bean
    public KafkaAdmin kafkaAdmin(){
        // Se crea un mapa de configuración para Kafka Admin Client.
        var configs = new HashMap<String, Object>();

        // Se establece la dirección del servidor de Kafka a partir de las propiedades.
        configs.put(AdminClientConfig.BOOTSTRAP_SERVERS_CONFIG, kafkaProperties.getBootstrapServers());

        // Se retorna un objeto KafkaAdmin con la configuración especificada.
        return new KafkaAdmin(configs);
    }

    // Define un bean que crea nuevos tópicos en el servidor de Kafka si no existen.
    @Bean
    public KafkaAdmin.NewTopics topics(){
        return new KafkaAdmin.NewTopics(
            // Se construye un tópico llamado "str-topic" con 2 particiones y 1 réplica.
            TopicBuilder.name("gestion-pacientes").partitions(3).replicas(1).build(),
            TopicBuilder.name("gestion-citas").partitions(3).replicas(1).build(),
            TopicBuilder.name("estado-paciente").partitions(2).replicas(1).build()
        );
    }
}

//TopicBuilder.name("topico-2").partitions(2).replicas(1).build() // nuevo tópico

