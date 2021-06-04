package com.dim.retrofitjsonlogindb.ui;

import androidx.appcompat.app.AppCompatActivity;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.dim.retrofitjsonlogindb.R;
import com.dim.retrofitjsonlogindb.model.LoginRequest;
import com.dim.retrofitjsonlogindb.model.LoginResponse;
import com.dim.retrofitjsonlogindb.network.ApiClient;
import com.google.android.material.textfield.TextInputEditText;

public class MainActivity extends AppCompatActivity {
    TextInputEditText etUsuario, etPass;
    Button bttnLogin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etUsuario = findViewById(R.id.etUsuario);
        etPass = findViewById(R.id.etPassword);
        bttnLogin = findViewById(R.id.btnLogin);

        bttnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(validaVacios(etUsuario) && validaVacios(etPass)){
                    IniciaSesion();
                }
            }
        });
    }

    private boolean validaVacios(TextInputEditText etCampo){
        if(etCampo.getText().toString().trim().length() > 0){
            return true;
        }
        etCampo.setError("Por favor ingrese su datp");
        etCampo.requestFocus();
        return false;
    }

    private void IniciaSesion(){
        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setUsuario(etUsuario.getText().toString());
        loginRequest.setPass(etPass.getText().toString());

        Call<LoginResponse> loginResponseCall = ApiClient.getUsuarioServicio()
                .userLogin(loginRequest);
        loginResponseCall.enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                if(response.isSuccessful()){
                    muestraToast("Inicio de sesión exitoso");
                    LoginResponse loginResponse = response.body();
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            startActivity(new Intent(MainActivity.this, Tablero.class)
                            .putExtra("data", loginResponse.getUsuario()));
                        }
                    }, 700);
                }else {
                    muestraToast("Inicio de sesión fallido");
                }
            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {
                muestraToast(t.getLocalizedMessage());
            }
        });
    }

    private void muestraToast(String mensaje){
        Toast.makeText(this, mensaje, Toast.LENGTH_LONG).show();
    }
}