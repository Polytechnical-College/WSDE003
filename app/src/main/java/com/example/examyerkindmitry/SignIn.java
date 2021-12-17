package com.example.examyerkindmitry;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.examyerkindmitry.model.Token;
import com.google.gson.Gson;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class SignIn extends AppCompatActivity {

    private EditText loginText, passwordText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        Button signIn = findViewById(R.id.sign_in);
        TextView createANewAccount = findViewById(R.id.createNewAccount);
        loginText = findViewById(R.id.login);
        passwordText = findViewById(R.id.password);

        signIn.setOnClickListener(v -> {
            try {
                login(loginText.getText().toString(), passwordText.getText().toString());
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        createANewAccount.setOnClickListener(v -> {
            startActivity(new Intent(this, SignUp.class));
            finish();
        });
    }

    public void login(String login, String password) {
        if (!login.trim().equals("") && !password.trim().equals("")) {
            OkHttpClient client = new OkHttpClient();

            FormBody formBody = new FormBody.Builder()
                    .add("username", login)
                    .add("password", password).build();

            Request request = new Request.Builder()
                    .post(formBody)
                    .url("http://cars.areas.su/login").build();

            client.newCall(request).enqueue(new Callback() {
                @Override
                public void onFailure(@NonNull Call call, @NonNull IOException e) {
                    e.printStackTrace();
                }

                @Override
                public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                    if (response.code() == 200) {
                        Intent intent = new Intent(getApplicationContext(), StartScreen.class);
                        Token token = new Gson().fromJson(response.body().string(), Token.class);
                        System.out.println(token);
                        System.out.println(token.getToken());
//                        startActivity(intent);
//                        finish();
                    }
                }
            });
        } else {
            Toast.makeText(this, "Login and password cannot be empty", Toast.LENGTH_SHORT).show();
        }
    }
}