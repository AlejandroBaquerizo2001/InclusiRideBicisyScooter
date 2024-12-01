package com.example.inclusiridebicisyscooter.Models;

import java.io.Serializable;

public class User implements Serializable {
    private int id;
    private String nombres;
    private String apellidos;
    private String username;
    private String password;
    private String fechaNac;

    public User() { }

    public User(String nombres, String apellidos, String username, String password, String fechaNac) {
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.username = username;
        this.password = password;
        this.fechaNac = fechaNac;
    }

    public User(int id, String nombres, String apellidos, String username, String password, String fechaNac) {
        this.id = id;
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.username = username;
        this.password = password;
        this.fechaNac = fechaNac;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getNombres() { return nombres; }
    public void setNombres(String nombres) { this.nombres = nombres; }

    public String getApellidos() { return apellidos; }
    public void setApellidos(String apellidos) { this.apellidos = apellidos; }

    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

    public String getFechaNac() { return fechaNac; }
    public void setFechaNac(String fechaNac) { this.fechaNac = fechaNac; }

    @Override
    public String toString() {
        return "Usuario{" +
                "id=" + id +
                ", nombres='" + nombres + '\'' +
                ", apellidos='" + apellidos + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", fechaNac='" + fechaNac + '\'' +
                '}';
    }

}
