package com.ahmetozdemir.gymapp;

import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.ahmetozdemir.gymapp.databinding.RegisterPageBinding;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.NetworkResponse;
import com.android.volley.ParseError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.HttpHeaderParser;
import com.android.volley.toolbox.JsonRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;

public class RegisterPage extends AppCompatActivity
{
    private RegisterPageBinding binding; // xml bağlama için
    private RequestQueue requestQueue; // RequestQueue'yu sınıf düzeyinde tanımla

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        binding = RegisterPageBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        // RequestQueue'yu oluştur
        requestQueue = Volley.newRequestQueue(this);

        ImageView passwordToggle = binding.imageButtonShowPassword;
        EditText passwordEditText = binding.editTextNumberPassword2;

        //Şifreyi göster/gizle işlevselliini yöneten onClick
        passwordToggle.setOnClickListener(new View.OnClickListener()
        {
            boolean passwordVisible = true;

            @Override
            public void onClick(View v)
            {
                passwordVisible = !passwordVisible;

                if (passwordVisible)
                {
                    // Şifreyi göster
                    passwordEditText.setInputType(InputType.TYPE_CLASS_TEXT);
                }

                else
                {
                    // Şifreyi gizle
                    passwordEditText.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                }
                // Göz ikonuna tıklandığında imleç sağ tarafta kalsın diye aşağıdaki satır eklendi
                passwordEditText.setSelection(passwordEditText.getText().length());
            }
        });

        //"SIGN UP" butonuna basıldığında buraya giriyoruz
        binding.button.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                //Kullanıcı adı, şifre ve mail adresini al
                String userName = binding.editText.getText().toString();
                String password = binding.editTextNumberPassword2.getText().toString();
                String email = binding.editTextTextPassword2.getText().toString();

                //Bilgileri API'ye yollamak için bir RegisterModel objesi oluşturuyoruz
                RegisterModel registerModel = new RegisterModel(userName, email, password);
                //API'ye kayıt bilgilerini gönderiyoruz
                sendPostRequest(registerModel);
            }
        });
    }

    //API'ye POST isteği gönderen metot
    private void sendPostRequest(RegisterModel registerModel)
    {
        // JSONPlaceholder API endpoint'i
        String url = "https://jsonplaceholder.typicode.com/posts";

        // JSON request body'si oluştur (deneme amaçlı imdb api'si üzerinde test yapılmıştır.)
        JSONObject jsonBody = new JSONObject();
        try
        {
            jsonBody.put("title", registerModel.getUserName());
            jsonBody.put("body", registerModel.getEmail());
            jsonBody.put("userId", 1); // Örnek bir kullanıcı kimliği
        }

        catch (JSONException e)
        {
            e.printStackTrace();
        }

        // Create a JSON object request
        JsonRequest<JSONObject> jsonObjectRequest = new JsonRequest<JSONObject>
                (
                    Request.Method.POST, url, jsonBody.toString(),

                    response ->
                    {
                        // Successful response
                        Toast.makeText(this, "Register Succesful", Toast.LENGTH_SHORT).show();
                        Log.d("RegisterPage", "API call successful: " + response.toString());
                    },

                    error ->
                    {
                        Toast.makeText(this, "Login Failed", Toast.LENGTH_SHORT).show();
                        Log.e("RegisterPage", "API call failed: " + error.toString());

                        // Daha fazla hata ayrıntıları için error.getLocalizedMessage() ve error.networkResponse'ı loglanabilir.
                        if (error.networkResponse != null)
                        {
                            Log.e("RegisterPage", "Error Response Code: " + error.networkResponse.statusCode);

                            try
                            {
                                String responseBody = new String(error.networkResponse.data, StandardCharsets.UTF_8);
                                Log.e("RegisterPage", "Error Response Body: " + responseBody);
                            }

                            catch (Exception e)
                            {
                                Log.e("RegisterPage", "Error parsing response body: " + e.getMessage());
                            }
                        }
                    }
                )
        {
            @Override
            protected Response<JSONObject> parseNetworkResponse(NetworkResponse response)
            {
                /*
                    response.data ---> Sunucudan gelen yanıtın veri kısmını temsil eder (JSON formatındaki bu veriyi String'e çeviriyoruz.
                    HTTPHeaderParser.parseCharset(response.headers, PROTOCOL_CHARSET): ---> Sunucudan gelen yanıtın karakter setini belirlememize yardımcı olan yardımcı metod
                    (Genellikle UTF-8 formatında olur)
                    new JSONObject(jsonString) ---> JSON verisini içeren bir stringden bir 'JSONObject' oluşturuyoruz. Sunucudan gelen JSON verisini işlememize yardımcı olur
                */

                try
                {
                    //Sunucu tarafından gelen yanıtın verisini al
                    String jsonString = new String(response.data, HttpHeaderParser.parseCharset(response.headers, PROTOCOL_CHARSET));
                    //JSON verisini kullanarak bir JSONObject oluştur ve başarılı yanıtı döndür
                    return Response.success(new JSONObject(jsonString), HttpHeaderParser.parseCacheHeaders(response));
                }

                catch (UnsupportedEncodingException | JSONException e)
                {
                    //Yanıtın işlenmesinde bir hata olursa, ParseError tipinde bir hata mesajı  döndür
                    return Response.error(new ParseError(e));
                }
            }
        };

        // Set a retry policy
        jsonObjectRequest.setRetryPolicy(new DefaultRetryPolicy(3000,  /* timeout in milliseconds */ DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        // Add the request to the queue
        requestQueue.add(jsonObjectRequest);
    }
}
