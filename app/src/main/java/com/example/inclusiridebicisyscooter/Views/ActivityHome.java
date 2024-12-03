package com.example.inclusiridebicisyscooter.Views;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.inclusiridebicisyscooter.AtencionClienteActivity;
import com.example.inclusiridebicisyscooter.DistritoActivity;
import com.example.inclusiridebicisyscooter.PuntosAtencionActivity;
import com.example.inclusiridebicisyscooter.R;
import com.example.inclusiridebicisyscooter.ReservaActivity;

public class ActivityHome extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_home);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        // Inicializar los botones
        Button btnReservar = findViewById(R.id.btnReservar);
        Button btnPuntosAtencion = findViewById(R.id.btnPuntosAtencion);
        Button btnDistrito = findViewById(R.id.btnDistrito);
        Button btnHistorial = findViewById(R.id.btnHistorial);

        // Establecer los listeners para cada botón
        btnReservar.setOnClickListener(v -> {
            Intent intent = new Intent(ActivityHome.this, ReservaActivity.class);
            startActivity(intent);
        });

        btnPuntosAtencion.setOnClickListener(v -> {
            Intent intent = new Intent(ActivityHome.this, PuntosAtencionActivity.class);
            startActivity(intent);
        });

        btnDistrito.setOnClickListener(v -> {
            Intent intent = new Intent(ActivityHome.this, DistritoActivity.class);
            startActivity(intent);
        });

        btnHistorial.setOnClickListener(v -> {
            Intent intent = new Intent(ActivityHome.this, AtencionClienteActivity.class);
            startActivity(intent);
        });
    }
}