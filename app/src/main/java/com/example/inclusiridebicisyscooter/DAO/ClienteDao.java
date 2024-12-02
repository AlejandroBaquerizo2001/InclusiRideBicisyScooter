package com.example.inclusiridebicisyscooter.DAO;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;
import androidx.room.Delete;

import com.example.inclusiridebicisyscooter.Models.Cliente;

import java.util.List;
@Dao
public interface ClienteDao {
    @Insert
    void insertarCliente(Cliente cliente);

    @Query("SELECT * FROM clientes")
    List<Cliente> obtenerTodosLosClientes();

    @Update
    void actualizarCliente(Cliente cliente);

    @Delete
    void eliminarCliente(Cliente cliente);
}
