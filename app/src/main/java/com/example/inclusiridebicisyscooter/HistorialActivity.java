package com.example.inclusiridebicisyscooter;

import android.os.Bundle;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.inclusiridebicisyscooter.DAO.AppDatabase;
import com.example.inclusiridebicisyscooter.Models.Cliente;
import com.example.inclusiridebicisyscooter.Models.Pedido;

import java.util.List;

public class HistorialActivity extends AppCompatActivity {
    private AppDatabase db;
    private PedidoAdapter adapter;
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_historial);

        db = AppDatabase.getDatabase(getApplicationContext());
        recyclerView = findViewById(R.id.recyclerView);
        adapter = new PedidoAdapter(null);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Ejemplo de inserción
        new Thread(() -> {
            Cliente cliente = new Cliente();
            cliente.nombre = "Juan Pérez";
            cliente.email = "juan@example.com";
            db.clienteDao().insertarCliente(cliente);

            Pedido pedido = new Pedido();
            pedido.clienteId = cliente.id; // Asumiendo que el ID se obtiene después de la inserción
            pedido.descripcion = "Pedido de prueba";
            pedido.fecha = "2024-12-01";
            db.pedidoDao().insertarPedido(pedido);

            // Obtener todos los pedidos después de la inserción
            List<Pedido> pedidos = db.pedidoDao().obtenerTodosLosPedidos();
            runOnUiThread(() -> adapter.setPedidos(pedidos)); // Actualiza la UI en el hilo principal
        }).start();
    }
}
