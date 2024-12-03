package com.example.inclusiridebicisyscooter;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

import com.example.inclusiridebicisyscooter.DAO.DBHelper;

import java.util.ArrayList;

public class PuntosAtencionActivity extends AppCompatActivity {

    private DBHelper dbHelper;
    private EditText editTextNombre;
    private Button buttonAgregar, buttonLeer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_puntos_atencion);

        dbHelper = new DBHelper(this);
        editTextNombre = findViewById(R.id.editTextNombre);
        buttonAgregar = findViewById(R.id.buttonAgregar);
        buttonLeer = findViewById(R.id.buttonLeer);

        buttonAgregar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                agregarPuntoAtencion(editTextNombre.getText().toString());
            }
        });

        buttonLeer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                leerPuntosAtencion();
            }
        });
    }

    private void agregarPuntoAtencion(String nombre) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(DBHelper.COLUMN_NAME, nombre);
        long newRowId = db.insert(DBHelper.TABLE_NAME, null, values);
        if (newRowId != -1) {
            Toast.makeText(this, "Punto de atención agregado", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Error al agregar punto de atención", Toast.LENGTH_SHORT).show();
        }
    }

    private void leerPuntosAtencion() {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = db.query(DBHelper.TABLE_NAME, null, null, null, null, null, null);
        ArrayList<String> puntos = new ArrayList<>();

        // Verifica si el cursor no es nulo y tiene al menos una fila
        if (cursor != null && cursor.moveToFirst()) {
            do {
                int columnIndex = cursor.getColumnIndex(DBHelper.COLUMN_NAME);
                // Verifica que el índice de columna sea válido
                if (columnIndex != -1) {
                    String nombre = cursor.getString(columnIndex);
                    puntos.add(nombre);
                }
            } while (cursor.moveToNext());
        } else {
            Toast.makeText(this, "No se encontraron puntos de atención", Toast.LENGTH_SHORT).show();
        }

        cursor.close();

        // Aquí puedes mostrar los puntos en un TextView o similar
        Toast.makeText(this, "Puntos de atención: " + puntos, Toast.LENGTH_LONG).show();
    }

}

