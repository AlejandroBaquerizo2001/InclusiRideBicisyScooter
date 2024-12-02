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

public class PuntosAtencionActivity extends AppCompatActivity {

    private PreferencesManager preferencesManager;
    private EditText editTextKey, editTextValue;
    private Button buttonSave, buttonRetrieve, buttonRemove, buttonClear;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_puntos_atencion);

        // Inicializar Views
        editTextKey = findViewById(R.id.editTextKey);
        editTextValue = findViewById(R.id.editTextValue);
        buttonSave = findViewById(R.id.buttonSave);
        buttonRetrieve = findViewById(R.id.buttonRetrieve);
        buttonRemove = findViewById(R.id.buttonRemove);
        buttonClear = findViewById(R.id.buttonClear);

        // Inicializar PreferencesManager
        preferencesManager = new PreferencesManager(this);

        // Configurar OnApplyWindowInsetsListener
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Configurar botones
        buttonSave.setOnClickListener(this::saveData);
        buttonRetrieve.setOnClickListener(this::retrieveData);
        buttonRemove.setOnClickListener(this::removeData);
        buttonClear.setOnClickListener(this::clearData);
    }

    private void saveData(View view) {
        String key = editTextKey.getText().toString();
        String value = editTextValue.getText().toString();
        preferencesManager.saveData(key, value);
        Toast.makeText(this, "Data saved", Toast.LENGTH_SHORT).show();
    }

    private void retrieveData(View view) {
        String key = editTextKey.getText().toString();
        String value = preferencesManager.getData(key);
        if (value != null) {
            editTextValue.setText(value);
            Toast.makeText(this, "Data retrieved", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "No data found", Toast.LENGTH_SHORT).show();
        }
    }

    private void removeData(View view) {
        String key = editTextKey.getText().toString();
        preferencesManager.removeData(key);
        Toast.makeText(this, "Data removed", Toast.LENGTH_SHORT).show();
    }

    private void clearData(View view) {
        preferencesManager.clear();
        Toast.makeText(this, "All data cleared", Toast.LENGTH_SHORT).show();
    }
}
