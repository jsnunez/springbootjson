package com.listasbr.demons.controllers;

import com.listasbr.demons.model.User;
import org.springframework.web.bind.annotation.*;

import java.io.*;
import java.util.Hashtable;
import java.util.Map;
import java.util.Set;

@RestController
@RequestMapping("/api")
public class UserRestController {

    private Hashtable<String, User> usuarios = new Hashtable<>();

    @PostMapping("/crearusuario")
    public User crearusuario(@RequestBody User user) {
        usuarios.put(user.getCedula(), user);
        saveUsersToFile();
        System.out.println(usuarios.get(user.getCedula()).getNombre());
        return user;
    }

    @GetMapping("/buscarusuario")
    public User buscarUsuario(@RequestParam String cedula) {
        return usuarios.get(cedula);
    }

    @GetMapping("/verusuarios")
    public Map<String, User> verUsuarios() {
        return usuarios;
    }

    private void saveUsersToFile() {
        try (PrintWriter writer = new PrintWriter(new FileWriter("usuarios.json"))) {
            writer.println("{");
            Set<Map.Entry<String, User>> entrySet = usuarios.entrySet();
            for (Map.Entry<String, User> entry : entrySet) {
                User user = entry.getValue();
                writer.println("  \"" + entry.getKey() + "\": {");
                writer.println("    \"nombre\": \"" + user.getNombre() + "\",");
                writer.println("    \"apellido\": \"" + user.getApellido() + "\",");
                writer.println("    \"edad\": \"" + user.getEdad() + "\",");
                writer.println("    \"cedula\": \"" + user.getCedula() + "\"");
                writer.println("  },");
            }
            writer.println("}");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
