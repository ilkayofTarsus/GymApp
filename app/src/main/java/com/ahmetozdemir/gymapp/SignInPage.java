package com.ahmetozdemir.gymapp;

import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.ahmetozdemir.gymapp.databinding.SignInPageBinding;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

public class SignInPage extends AppCompatActivity
{
    private SignInPageBinding binding;
    private RequestQueue requestQueue;
    private boolean passwordVisible = true;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        binding = SignInPageBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        // RequestQueue'yu oluştur
        requestQueue = Volley.newRequestQueue(this);

        ImageView passwordToggle = binding.imageButtonShowPassword;
        EditText passwordEditText = binding.editTextNumberPassword2;

        passwordToggle.setOnClickListener(v ->
            {
                passwordVisible = !passwordVisible;

                if (passwordVisible)
                {
                    passwordEditText.setInputType(InputType.TYPE_CLASS_TEXT);
                }
                else
                {
                    passwordEditText.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                }
                passwordEditText.setSelection(passwordEditText.getText().length());
            });
    }

    public void goProfil(View view)
    {
        Intent intent = new Intent(SignInPage.this, ProfilePage.class);
        startActivity(intent);
        // Burada LoginModel kullanarak API'ye istek gönderilecek
        String email = binding.editTextTextPassword2.getText().toString();
        String password = binding.editTextNumberPassword2.getText().toString();

        LoginModel loginModel = new LoginModel(email, password);
        //sendPostRequest(loginModel);
    }

    public void forgotPassword(View view)
    {
        Intent intent = new Intent(SignInPage.this, ForgotPasswordPage.class);
        startActivity(intent);
    }

    /*
    private void sendPostRequest(LoginModel loginModel)
    {
        String url = "https://jsonplaceholder.typicode.com/posts"; // Örnek API endpoint'i

        // JSON request body'si oluştur (deneme amaçlı imdb api'si üzerinde test yapılmıştır.)
        JSONObject jsonBody = new JSONObject();
        try
        {
            jsonBody.put("email", loginModel.getEmail());
            jsonBody.put("password", loginModel.getPassword());
        }

        catch (JSONException e)
        {
            e.printStackTrace();
        }

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
                (
                    Request.Method.POST, url, jsonBody,
                    response -> {  Başarılı yanıt  Toast.makeText(this, "Login Succesful", Toast.LENGTH_SHORT).show(); },
                    error -> {  Hata durumu  Toast.makeText(this, "Login Failed", Toast.LENGTH_SHORT).show(); }
                );

        jsonObjectRequest.setRetryPolicy(new DefaultRetryPolicy
                (
                    3000,  // timeout in milliseconds
                    DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                    DefaultRetryPolicy.DEFAULT_BACKOFF_MULT
                )
        );
        requestQueue.add(jsonObjectRequest);
    }*/

}