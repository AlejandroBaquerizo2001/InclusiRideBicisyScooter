package com.example.inclusiridebicisyscooter;

import android.os.Bundle;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ArrayAdapter;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.Calendar;

public class ReservaActivity extends AppCompatActivity {

    private TextView tvSelectedDateTime; // TextView para mostrar la fecha y hora
    private RadioGroup vehicleTypeGroup; // Para elegir el tipo de vehículo
    private Spinner spinnerLocations; // Para elegir la ubicación

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_reserva);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;

        });
        // Inicialización de vistas
        tvSelectedDateTime = findViewById(R.id.tvSelectedDateTime);
        vehicleTypeGroup = findViewById(R.id.vehicleTypeGroup);
        spinnerLocations = findViewById(R.id.spinnerLocations);
        // El botón para seleccionar la fecha y hora
        Button btnSelectDateTime = findViewById(R.id.btnSelectDateTime);

        // Establecer el evento onClickListener para el botón
        btnSelectDateTime.setOnClickListener(view -> {
            // Mostrar el selector de fecha y hora
            showDateTimePicker();
        });

        // Configurar el Spinner con las ubicaciones
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item,
                new String[]{"Calle Ficticia 1", "Calle Ficticia 2", "Calle Ficticia 3"});
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerLocations.setAdapter(adapter);
    }

    // Método para mostrar el selector de fecha y hora
    private void showDateTimePicker() {
        // Obtener la fecha y hora actuales
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        int minute = calendar.get(Calendar.MINUTE);

        // Crear el selector de fecha
        DatePickerDialog datePickerDialog = new DatePickerDialog(this, (view, selectedYear, selectedMonth, selectedDay) -> {
            // Guardar la fecha seleccionada
            String selectedDate = selectedDay + "/" + (selectedMonth + 1) + "/" + selectedYear;

            // Crear el selector de hora
            TimePickerDialog timePickerDialog = new TimePickerDialog(this, (timeView, selectedHour, selectedMinute) -> {
                // Guardar la hora seleccionada
                String selectedTime = selectedHour + ":" + (selectedMinute < 10 ? "0" : "") + selectedMinute;

                // Mostrar la fecha y hora seleccionada en el TextView
                String selectedDateTime = "Fecha: " + selectedDate + " | Hora: " + selectedTime;

                // Obtener el tipo de vehículo seleccionado
                int selectedVehicleId = vehicleTypeGroup.getCheckedRadioButtonId();
                RadioButton selectedVehicle = findViewById(selectedVehicleId);
                String vehicle = selectedVehicle != null ? selectedVehicle.getText().toString() : "No seleccionado";

                // Obtener la ubicación seleccionada
                String location = spinnerLocations.getSelectedItem().toString();

                // Mostrar la información en un Toast
                String message = "Tipo de vehículo: " + vehicle + "\nUbicación: " + location + "\n" + selectedDateTime;
                Toast.makeText(this, message, Toast.LENGTH_SHORT).show();

                // Actualizar el TextView con los detalles completos
                tvSelectedDateTime.setText(message);
            }, hour, minute, true);

            // Mostrar el selector de hora
            timePickerDialog.show();
        }, year, month, day);

        // Mostrar el selector de fecha
        datePickerDialog.show();
    }
}