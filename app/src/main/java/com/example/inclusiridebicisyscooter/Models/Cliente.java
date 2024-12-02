package com.example.inclusiridebicisyscooter.Models;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
@Entity(tableName = "clientes")
public class Cliente {
    @PrimaryKey(autoGenerate = true)
    public int id;
    public String nombre;
    public String email;
}
