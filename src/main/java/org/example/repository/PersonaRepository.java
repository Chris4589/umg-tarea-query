package org.example.repository;

import org.example.configs.MysqlConfig;
import org.example.domain.Persona;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PersonaRepository {
    public void save(Persona persona) throws SQLException, IOException {
        var con = MysqlConfig.getConnection();
        var statement = con.prepareStatement("INSERT INTO persona (nombre, edad) VALUES (?, ?)");
        statement.setString(1, persona.getNombre());
        statement.setInt(2, persona.getEdad());
        statement.executeUpdate();
    }

    public void delete(Persona persona) throws SQLException, IOException {
        var con = MysqlConfig.getConnection();
        var statement = con.prepareStatement("DELETE FROM persona WHERE nombre = ?");
        statement.setString(1, persona.getNombre());
        statement.executeUpdate();

        System.out.println("Deleted: " + persona.getNombre());
    }

    public void update(Persona persona) throws SQLException, IOException {
        var con = MysqlConfig.getConnection();
        var statement = con.prepareStatement("UPDATE persona SET edad = ? WHERE nombre = ?");
        statement.setInt(1, persona.getEdad());
        statement.setString(2, persona.getNombre());
        statement.executeUpdate();

        System.out.println("Updated: " + persona.getNombre());
    }

    public void read(Persona persona) throws SQLException, IOException {
        var con = MysqlConfig.getConnection();
        var statement = con.prepareStatement("SELECT * FROM persona WHERE nombre = ?");
        statement.setString(1, persona.getNombre());
        statement.executeQuery();
    }

    public List<Persona> readAll() throws SQLException, IOException {
        var con = MysqlConfig.getConnection();
        var statement = con.prepareStatement("SELECT * FROM persona");
        var result = statement.executeQuery();

        List<Persona> personas = new ArrayList<>();

        while (result.next()) {
            personas.add(new Persona(result.getString("nombre"), result.getInt("edad")));
        }
        return personas;
    }
}
