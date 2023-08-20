package com.example.demoquery.consumer;

import com.example.demoquery.event.TransferCreated;
import com.example.demoquery.repository.TransferSummaryRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
public class TransferConsumerTopic {

    private final ModelMapper modelMapper;
    private final TransferSummaryRepository repository;


    private final JdbcTemplate jdbcTemplate;

    public TransferConsumerTopic(ModelMapper modelMapper, TransferSummaryRepository repository, JdbcTemplate jdbcTemplate) {
        this.modelMapper = modelMapper;
        this.repository = repository;
        this.jdbcTemplate = jdbcTemplate;
    }


    @KafkaListener(topics = "transfer-topic", groupId = "group_id")
    @Transactional
    public void consume(String mensagem) throws JsonProcessingException {
        System.out.println("Mensagem RECEBIDA: " + mensagem);
        var obj = new ObjectMapper();
        obj.registerModule(new JavaTimeModule());
        var messageConverterToObj = obj.readValue(mensagem, TransferCreated.class);

        // Faça o processamento de Sincronização de tabelas, para o seu contexto
        // Como estou usando uma view materializada irei apenas fazer o refresh dela
        //Há várias formas de fazer isso, não é possível inserir itens em uma view materializada


        // Execute o comando SQL personalizado para atualizar a view materializada
        String sql = "SELECT * FROM refresh_materialized_view()";
        jdbcTemplate.execute(sql);
    }
}
