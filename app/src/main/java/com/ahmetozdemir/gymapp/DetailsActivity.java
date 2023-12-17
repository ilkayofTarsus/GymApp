package com.ahmetozdemir.gymapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.ahmetozdemir.gymapp.databinding.ActivityDetailsBinding;

public class DetailsActivity extends AppCompatActivity
{
    private ActivityDetailsBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        binding = ActivityDetailsBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        Intent intent = getIntent();

        Supplement selectedSupplement = (Supplement) intent.getSerializableExtra("landmark");

        binding.nameText.setText(selectedSupplement.supplementName);
        binding.countryText.setText(selectedSupplement.supplementInfo);
        binding.imageView4.setImageResource(selectedSupplement.supplementImage);
        //getSupportActionBar().setTitle("SUPPLEMENT CATALOG"); //Burası action barda yazılacak yazıyı ayarlar
    }

    public void goProfile(View view)
    {
        Intent intent = new Intent(DetailsActivity.this, ProfilePage.class);
        startActivity(intent);
    }

    public void goHome(View view)
    {
        Intent intent = new Intent(DetailsActivity.this, BasePage.class);
        startActivity(intent);
    }
}