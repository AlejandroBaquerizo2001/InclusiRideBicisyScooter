package com.example.inclusiridebicisyscooter;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.inclusiridebicisyscooter.Views.ActivityFormUser;
import com.example.inclusiridebicisyscooter.Views.ActivityHome;
import com.example.inclusiridebicisyscooter.Views.ActivityLogin;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        checkLoginStatus();
    }

    public void handleLoginView(View view){
        Intent intent = new Intent(this, ActivityLogin.class);
        startActivity(intent);
    }

    public void handleRegistrarUserView(View view){
        Intent intent = new Intent(this, ActivityFormUser.class);
        startActivity(intent);
    }

    private void checkLoginStatus() {
        SharedPreferences sp = getSharedPreferences("credentials", MODE_PRIVATE);
        String username = sp.getString("username", null);
        String password = sp.getString("password", null);

        if (username != null && password != null) {
            Intent intent = new Intent(this, ActivityHome.class);
            startActivity(intent);
            finish();
        }
    }

}