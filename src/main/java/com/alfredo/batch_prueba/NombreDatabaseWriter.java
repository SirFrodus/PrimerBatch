package com.alfredo.batch_prueba;

import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class NombreDatabaseWriter implements ItemWriter<String> {

    @Autowired
    private NombreRepository nombreRepository; // Repositorio JPA

    @Override
    public void write(List<? extends String> items) throws Exception {
        for (String item : items) {
            // Creamos un nuevo objeto Nombre y lo guardamos en la base de datos
            if (!item.trim().isEmpty()) {
                Nombre nombre = new Nombre();
                nombre.setNombre(item);
                nombreRepository.save(nombre);
            }
        }
    }


}
