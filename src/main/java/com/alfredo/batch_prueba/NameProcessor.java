package com.alfredo.batch_prueba;

import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

@Component
public class NameProcessor implements ItemProcessor<String, String> {

    @Override
    public String process(String name) {
        if (!name.trim().isEmpty()) {
            return name.substring(0, 1).toUpperCase() + name.substring(1);  // Convierte la primera letra en mayúscula
        }
        return name;
    }
}
