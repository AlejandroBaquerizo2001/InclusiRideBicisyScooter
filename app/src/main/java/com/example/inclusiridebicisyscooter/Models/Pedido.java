package com.example.inclusiridebicisyscooter.Models;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "pedidos")
public class Pedido {
    @PrimaryKey(autoGenerate = true)
    public int id;
    public int clienteId; // Relación con Cliente
    public String descripcion;
    public String fecha;
}