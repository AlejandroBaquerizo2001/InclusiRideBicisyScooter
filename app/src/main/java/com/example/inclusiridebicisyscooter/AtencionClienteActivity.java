package com.example.inclusiridebicisyscooter;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.inclusiridebicisyscooter.Models.ClienteDAO;

import java.util.ArrayList;
import java.util.List;

public class AtencionClienteActivity extends AppCompatActivity {
    private ClienteDAO clienteDAO;
    private EditText editTextNombre, editTextApellido, editTextCelular, editTextEmail;
    private RecyclerView recyclerViewClientes;
    private ClienteAdapter clienteAdapter;
    private List<String> listaClientes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_atencion_cliente);

        // Configuración de insets
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Inicialización de ClienteDAO y elementos de UI
        clienteDAO = new ClienteDAO(this);
        editTextNombre = findViewById(R.id.editTextNombre);
        editTextApellido = findViewById(R.id.editTextApellido);
        editTextCelular = findViewById(R.id.editTextCelular);
        editTextEmail = findViewById(R.id.editTextEmail);
        Button btnAgregar = findViewById(R.id.btnAgregar);
        recyclerViewClientes = findViewById(R.id.recyclerViewClientes);

        // Inicialización de la lista y el adaptador
        listaClientes = new ArrayList<>();
        clienteAdapter = new ClienteAdapter(listaClientes);
        recyclerViewClientes.setLayoutManager(new LinearLayoutManager(this));
        recyclerViewClientes.setAdapter(clienteAdapter);

        // Configuración del botón para agregar cliente
        btnAgregar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nombre = editTextNombre.getText().toString();
                String apellido = editTextApellido.getText().toString();
                String celular = editTextCelular.getText().toString();
                String email = editTextEmail.getText().toString();

                if (nombre.isEmpty() || apellido.isEmpty() || celular.isEmpty() || email.isEmpty()) {
                    Toast.makeText(AtencionClienteActivity.this, "Por favor completa todos los campos", Toast.LENGTH_SHORT).show();
                    return;
                }

                clienteDAO.agregarCliente(nombre, apellido, celular, email);
                listaClientes.add(nombre + " " + apellido + " - " + celular + " - " + email);
                clienteAdapter.notifyItemInserted(listaClientes.size() - 1);
                Toast.makeText(AtencionClienteActivity.this, "Cliente agregado", Toast.LENGTH_SHORT).show();
                mostrarClientes();
            }
        });
    }

    private void mostrarClientes() {
        List<String> clientes = clienteDAO.obtenerClientes();
        listaClientes.clear();
        listaClientes.addAll(clientes);
        clienteAdapter.notifyDataSetChanged();
    }
}
