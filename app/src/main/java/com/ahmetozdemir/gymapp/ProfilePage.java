package com.ahmetozdemir.gymapp;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.ahmetozdemir.gymapp.databinding.ProfilePageBinding;

public class ProfilePage extends AppCompatActivity
{
    private ProfilePageBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        // DataBinding kullanarak XML dosyasını bağla
        binding = ProfilePageBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        // XML dosyasındaki view'lere binding nesnesi üzerinden erişebilirsin
        // Örneğin:
        binding.textView11.setText("Ahmet Özdemir");
        binding.textView10.setText("173M");
        binding.textView2.setText("22");

        // Profil sayfasının diğer işlemleri buraya eklenecek
    }
}
