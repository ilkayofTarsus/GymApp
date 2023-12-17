package com.ahmetozdemir.gymapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.ahmetozdemir.gymapp.databinding.BasePageBinding;
// ahmetovic tarsuslu
public class BasePage extends AppCompatActivity
{
    private BasePageBinding binding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        //Data binding ile XML dosyasını bağlıyoruz
        binding = BasePageBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

    }

    public void goProfile(View view)
    {
        Intent intent = new Intent(BasePage.this, ProfilePage.class);
        startActivity(intent);
    }

    public void goSupplements(View view)
    {
        Intent intent = new Intent(BasePage.this, SupplementActivity.class);
        startActivity(intent);
    }
}
