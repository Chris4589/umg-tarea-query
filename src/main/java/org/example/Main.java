package org.example;

import org.example.configs.MysqlConfig;
import org.example.domain.Persona;
import org.example.repository.PersonaRepository;

public class Main {
    public static void main(String[] args) {
        try {
            var connection = MysqlConfig.getConnection();
            System.out.println("Connection established!");

            Persona persona = new Persona("John Doe", 30);
            System.out.println("Saving persona: " + persona);
            PersonaRepository repository = new PersonaRepository();
            repository.save(persona);
            repository.readAll().forEach(System.out::println);
            System.out.println();
            repository.update(new Persona("John Doe", 100));
            repository.readAll().forEach(System.out::println);
            System.out.println();
            repository.delete(persona);
            repository.readAll().forEach(System.out::println);
            System.out.println();

            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}