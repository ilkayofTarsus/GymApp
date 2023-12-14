package com.ahmetozdemir.gymapp;

import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.ahmetozdemir.gymapp.databinding.ForgotPasswordPageBinding;

public class ForgotPasswordPage extends AppCompatActivity
{
    private ForgotPasswordPageBinding binding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        binding = ForgotPasswordPageBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        ImageView passwordToggle1 = binding.imageButtonShowPassword;
        ImageView passwordToggle2 = binding.imageButtonShowPassword2;
        EditText passwordEditText1 = binding.editTextNumberPassword2;
        EditText passwordEditText2 = binding.editTextNumberPassword3;

        passwordToggle1.setOnClickListener(new View.OnClickListener()
        {
            boolean passwordVisible = true;
            @Override
            public void onClick(View v)
            {
                passwordVisible = !passwordVisible;

                if (passwordVisible)
                {
                    // Şifreyi göster
                    passwordEditText1.setInputType(InputType.TYPE_CLASS_TEXT);
                }

                else
                {
                    // Şifreyi gizle
                    passwordEditText1.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                }
                // Göz ikonuna tıklandığında imleç sağ tarafta kalsın diye aşağıdaki satır eklendi
                passwordEditText1.setSelection(passwordEditText1.getText().length());
            }
        });

        passwordToggle2.setOnClickListener(new View.OnClickListener()
        {
            boolean passwordVisible = true;
            @Override
            public void onClick(View v)
            {
                passwordVisible = !passwordVisible;

                if (passwordVisible)
                {
                    // Şifreyi göster
                    passwordEditText2.setInputType(InputType.TYPE_CLASS_TEXT);
                }

                else
                {
                    // Şifreyi gizle
                    passwordEditText2.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                }
                // Göz ikonuna tıklandığında imleç sağ tarafta kalsın diye aşağıdaki satır eklendi
                passwordEditText2.setSelection(passwordEditText2.getText().length());
            }
        });
    }
}
