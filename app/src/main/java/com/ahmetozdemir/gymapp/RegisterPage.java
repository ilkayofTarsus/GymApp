package com.ahmetozdemir.gymapp;

import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import androidx.appcompat.app.AppCompatActivity;
import com.ahmetozdemir.gymapp.databinding.RegisterPageBinding;

public class RegisterPage extends AppCompatActivity
{
    private RegisterPageBinding binding; // xml bağlama için

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        binding = RegisterPageBinding.inflate(getLayoutInflater());
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

    public void goSupplement(View view)
    {
        Intent intent = new Intent(getApplicationContext(), SupplementActivity.class);
        startActivity(intent);
    }
}
