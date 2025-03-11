package com.alfredo.batch_prueba;

import org.springframework.batch.item.file.FlatFileItemWriter;
import org.springframework.core.io.FileSystemResource;
import org.springframework.stereotype.Component;

@Component
public class FileWriter {

    public FlatFileItemWriter<String> writer() {
        FlatFileItemWriter<String> writer = new FlatFileItemWriter<>();

        writer.setResource(new FileSystemResource("src/main/resources/nombres_bien.txt"));
        writer.setAppendAllowed(false);
        writer.setLineAggregator(item -> {
            if (!item.trim().isEmpty()) {
                return item;
            }
            return "[Item vacío en la línea]"; // Si el item está vacío, se marca
        });

        return writer;
    }
}
