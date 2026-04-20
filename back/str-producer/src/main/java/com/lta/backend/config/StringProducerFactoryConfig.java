package com.lta.backend.config;

import org.apache.kafka.clients.admin.AdminClientConfig;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;

import java.util.HashMap;

@Configuration
public class StringProducerFactoryConfig {

    // Inyección automática de las propiedades de Kafka definidas en application.properties o application.yml.
    @Autowired
    private KafkaProperties kafkaProperties;

    // Define un bean que representa la fábrica de productores de Kafka (ProducerFactory).
    @Bean
    public ProducerFactory<String, String> producerFactory() {
        // Se crea un mapa para almacenar la configuración del productor Kafka.
        var configs = new HashMap<String, Object>();

        // Se agrega la dirección del servidor Kafka (bootstrap server) desde las propiedades.
        configs.put(AdminClientConfig.BOOTSTRAP_SERVERS_CONFIG, kafkaProperties.getBootstrapServers());

        // Se especifica el serializador para las claves del mensaje (en este caso, tipo String).
        configs.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);

        // Se especifica el serializador para los valores del mensaje (también String).
        configs.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class);

        // Se crea y retorna una fábrica de productores Kafka con la configuración definida.
        return new DefaultKafkaProducerFactory<>(configs);
    }

    // Define un bean de KafkaTemplate que permite enviar mensajes a Kafka de manera sencilla.
    @Bean
    public KafkaTemplate<String, String> kafkaTemplate() {
        // Se crea un KafkaTemplate utilizando la fábrica de productores configurada previamente.
        return new KafkaTemplate<>(producerFactory());
    }
}

/*Un @Bean en Spring es una anotación que marca un método como productor de un objeto que será gestionado por el contenedor de Spring,
 es decir, Spring lo va a crear, inicializar y poner disponible para inyección de dependencias en otras partes del sistema. */