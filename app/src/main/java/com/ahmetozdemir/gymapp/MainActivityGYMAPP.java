package com.ahmetozdemir.gymapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.ahmetozdemir.gymapp.databinding.MainActivityGymappBinding;

public class MainActivityGYMAPP extends AppCompatActivity
{
    private MainActivityGymappBinding binding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        //Data binding ile XML dosyasını bağlıyoruz
        binding = MainActivityGymappBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

    }

    public void signIn(View view)
    {
        Intent intent = new Intent(MainActivityGYMAPP.this, SignInPage.class);
        startActivity(intent);
    }

    public void signUp(View view)
    {
        Intent intent = new Intent(MainActivityGYMAPP.this, RegisterPage.class);
        startActivity(intent);
    }
}
