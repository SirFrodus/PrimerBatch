package com.alfredo.batch_prueba;

import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.mapping.PassThroughLineMapper;
import org.springframework.core.io.FileSystemResource;
import org.springframework.stereotype.Component;

@Component
public class FileReader {

    public FlatFileItemReader<String> reader() {
        FlatFileItemReader<String> reader = new FlatFileItemReader<>();
        reader.setResource(new FileSystemResource("src/main/resources/nombres.txt"));
        reader.setLineMapper(new PassThroughLineMapper());
        return reader;
    }
}
