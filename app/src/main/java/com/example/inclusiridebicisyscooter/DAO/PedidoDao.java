package com.example.inclusiridebicisyscooter.DAO;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;
import androidx.room.Delete;

import com.example.inclusiridebicisyscooter.Models.Pedido;

import java.util.List;

@Dao
public interface PedidoDao {
    @Insert
    void insertarPedido(Pedido pedido);

    @Query("SELECT * FROM pedidos")
    List<Pedido> obtenerTodosLosPedidos();

    @Update
    void actualizarPedido(Pedido pedido);

    @Delete
    void eliminarPedido(Pedido pedido);
}