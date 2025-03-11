package com.alfredo.batch_prueba;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@EnableBatchProcessing
@Configuration
public class BatchConfiguration {

    @Autowired
    private JobBuilderFactory jobBuilderFactory;

    @Autowired
    private StepBuilderFactory stepBuilderFactory;

    @Autowired
    private FileReader fileReader;

    @Autowired
    private NameProcessor nameProcessor;

    @Autowired
    private FileWriter fileWriter;

    @Autowired NombreDatabaseWriter nombreDatabaseWriter;

    @Bean
    public Step stepLeeProcesaEscribeTexto() {
        return stepBuilderFactory.get("stepLeeYProcesa")
                .<String, String>chunk(10)  // Procesar 10 registros a la vez
                .reader(fileReader.reader())  // Lector del archivo
                .processor(nameProcessor)  // Procesador para modificar el nombre
                .writer(fileWriter.writer())  // Escritor que imprime el resultado
                .build();
    }

    @Bean
    public Step stepLeeProcesaEscribeBBDD() {
        return stepBuilderFactory.get("stepLeeYProcesa")
                .<String, String>chunk(10)  // Procesar 10 registros a la vez
                .reader(fileReader.reader())  // Lector del archivo
                .processor(nameProcessor)  // Procesador para modificar el nombre
                .writer(nombreDatabaseWriter)  // Escritor que imprime el resultado
                .build();
    }

    @Bean
    public Step stepFinish() {
        return stepBuilderFactory.get("stepFinish")
                .tasklet((contribution, chunkContext) -> {
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");

                    LocalDateTime localDateTime = LocalDateTime.now();
                    String formattedDate = localDateTime.format(formatter);

                    System.out.println("Proceso finalizado: " + formattedDate);
                    return RepeatStatus.FINISHED;
                })
                .build();
    }




    // Definimos el Job con los step que correspondan
    @Bean
    public Job simpleJob() {
        return jobBuilderFactory.get("simpleJob")
                .start(stepLeeProcesaEscribeTexto())    // Step Texto
                .next(stepLeeProcesaEscribeBBDD())      // Step BBDD
                .next(stepFinish())                     // Step Tasklet hora
                .build();
    }
}
