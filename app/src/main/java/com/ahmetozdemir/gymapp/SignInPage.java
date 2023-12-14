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

public class SignInPage extends AppCompatActivity
{
    private SignInPageBinding binding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        binding = SignInPageBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        ImageView passwordToggle = binding.imageButtonShowPassword;
        EditText passwordEditText = binding.editTextNumberPassword2;

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
    }

    public void goProfil(View view)
    {
        Intent intent = new Intent(getApplicationContext(), BasePage.class);
        startActivity(intent);
    }

    public void forgotPassword(View view)
    {
        Intent intent = new Intent(SignInPage.this, ForgotPasswordPage.class);
        startActivity(intent);
    }
}
