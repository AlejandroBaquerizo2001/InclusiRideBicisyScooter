package com.example.inclusiridebicisyscooter.Models;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.inclusiridebicisyscooter.DAO.DatabaseHelper;

import java.util.ArrayList;
import java.util.List;
public class ClienteDAO {
    private DatabaseHelper dbHelper;

    public ClienteDAO(Context context) {
        dbHelper = new DatabaseHelper(context);
    }

    public long agregarCliente(String nombre, String apellido, String celular, String email) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(DatabaseHelper.COLUMN_NOMBRE, nombre);
        values.put(DatabaseHelper.COLUMN_APELLIDO, apellido); // Agregado
        values.put(DatabaseHelper.COLUMN_CELULAR, celular); // Agregado
        values.put(DatabaseHelper.COLUMN_EMAIL, email);
        long id = db.insert(DatabaseHelper.TABLE_CLIENTES, null, values);
        db.close();
        return id;
    }

    public List<String> obtenerClientes() {
        List<String> clientes = new ArrayList<>();
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = db.query(DatabaseHelper.TABLE_CLIENTES, null, null, null, null, null, null);

        if (cursor.moveToFirst()) {
            do {
                int nombreIndex = cursor.getColumnIndex(DatabaseHelper.COLUMN_NOMBRE);
                int apellidoIndex = cursor.getColumnIndex(DatabaseHelper.COLUMN_APELLIDO);

                // Verificar si los índices son válidos
                if (nombreIndex != -1 && apellidoIndex != -1) {
                    String cliente = cursor.getString(nombreIndex) + " " + cursor.getString(apellidoIndex);
                    clientes.add(cliente);
                } else {
                    // Manejo de error si no se encuentra la columna
                    System.err.println("Columnas no encontradas.");
                }
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return clientes;
    }
    public void actualizarCliente(int id, String nombre, String apellido, String celular, String email) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(DatabaseHelper.COLUMN_NOMBRE, nombre);
        values.put(DatabaseHelper.COLUMN_APELLIDO, apellido); // Agregado
        values.put(DatabaseHelper.COLUMN_CELULAR, celular); // Agregado
        values.put(DatabaseHelper.COLUMN_EMAIL, email);
        db.update(DatabaseHelper.TABLE_CLIENTES, values, DatabaseHelper.COLUMN_ID + " = ?", new String[]{String.valueOf(id)});
        db.close();
    }

    public void eliminarCliente(int id) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        db.delete(DatabaseHelper.TABLE_CLIENTES, DatabaseHelper.COLUMN_ID + " = ?", new String[]{String.valueOf(id)});
        db.close();
    }
}