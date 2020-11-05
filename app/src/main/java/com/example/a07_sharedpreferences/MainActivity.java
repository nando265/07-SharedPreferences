package com.example.a07_sharedpreferences;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.a07_sharedpreferences.configuraciones.Configuracion;
import com.google.android.material.snackbar.Snackbar;

public class MainActivity extends AppCompatActivity {

    private EditText txtUser, txtPassword;
    private Button btnDoLogin;
    private SharedPreferences sharedPreferencesLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sharedPreferencesLogin = getSharedPreferences(Configuracion.LOGIN, MODE_PRIVATE);
        boolean logged = sharedPreferencesLogin.getBoolean("LOGED", false);

        if (logged){
            startActivity(new Intent(this, MainActivity2.class));
        }

        txtUser = findViewById(R.id.txtUserLogin);
        txtPassword = findViewById(R.id.txtPasswordLogin);
        btnDoLogin = findViewById(R.id.btnDoLogin);

        btnDoLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String usename = txtUser.getText().toString();
                String password = txtPassword.getText().toString();

                if (usename.equalsIgnoreCase("Edu") && password.equals("Progresa2020")){
                    Toast.makeText(MainActivity.this, "Login OK", Toast.LENGTH_SHORT).show();
                    SharedPreferences.Editor editor = sharedPreferencesLogin.edit();
                    editor.putString("USER",usename);
                    editor.putString("PASSWORD", password);
                    editor.putBoolean("LOGED", true);
                    editor.apply();
                    startActivity(new Intent(MainActivity.this, MainActivity2.class));
                }else{
                    Snackbar.make(v,"ERROR", Snackbar.LENGTH_LONG).show();
                }
            }
        });

    }
}