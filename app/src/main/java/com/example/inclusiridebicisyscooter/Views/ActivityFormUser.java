package com.example.inclusiridebicisyscooter.Views;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.DialogFragment;

import com.example.inclusiridebicisyscooter.DAO.UserDBHelper;
import com.example.inclusiridebicisyscooter.Models.User;
import com.example.inclusiridebicisyscooter.R;
import com.example.inclusiridebicisyscooter.Shared.DatePickerFragment;

import java.text.DateFormat;
import java.util.Calendar;

public class ActivityFormUser extends AppCompatActivity implements DatePickerDialog.OnDateSetListener {

    private EditText txtNombres, txtApellidos, txtUsername, txtPassword;
    public String fechaNac = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_formuser);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        txtNombres = findViewById(R.id.txtNombres);
        txtApellidos = findViewById(R.id.txtApellidos);
        txtUsername = findViewById(R.id.txtUsername);
        txtPassword = findViewById(R.id.txtPassword);
    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        Calendar c = Calendar.getInstance();
        c.set(Calendar.YEAR, year);
        c.set(Calendar.MONTH, month);
        c.set(Calendar.DAY_OF_MONTH, dayOfMonth);
        String currentDateString = DateFormat.getDateInstance(DateFormat.FULL).format(c.getTime());
        fechaNac = currentDateString;
    }

    public void dialogFragment(View view) {
        DialogFragment datePicker = new DatePickerFragment();
        datePicker.show(getSupportFragmentManager(), "date picker");
    }

    public void onSubmit(View view) {
        String nombres = txtNombres.getText().toString().trim();
        String apellidos = txtApellidos.getText().toString().trim();
        String username = txtUsername.getText().toString().trim();
        String password = txtPassword.getText().toString().trim();

        if (TextUtils.isEmpty(nombres) || TextUtils.isEmpty(apellidos) || TextUtils.isEmpty(fechaNac) || TextUtils.isEmpty(username) || TextUtils.isEmpty(password) || TextUtils.isEmpty(fechaNac)) {
            Toast.makeText(this, "Por favor, complete todos los campos", Toast.LENGTH_SHORT).show();
            return;
        }

        try {
            UserDBHelper dbHelper = new UserDBHelper(this);
            User nuevoUsuario = new User(nombres, apellidos, username, password, fechaNac);
            long id = dbHelper.addUsuario(nuevoUsuario);

            if (id != -1) {
                Toast.makeText(this, "Cuenta creada con éxito", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(this, ActivityLogin.class);
                startActivity(intent);
                finish();
            } else {
                Toast.makeText(this, "Error al crear la cuenta", Toast.LENGTH_SHORT).show();
            }
        } catch (Exception e) {
            Toast.makeText(this, "Se produjo un error: " + e.getMessage(), Toast.LENGTH_LONG).show();
            e.printStackTrace();
        }
    }


}