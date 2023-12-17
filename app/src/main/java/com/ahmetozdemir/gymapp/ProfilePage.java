package com.ahmetozdemir.gymapp;

import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.os.Bundle;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import com.ahmetozdemir.gymapp.databinding.ProfilePageBinding;

public class ProfilePage extends AppCompatActivity
{
    private ProfilePageBinding binding;

    private ActivityResultLauncher<Intent> activityResultLauncher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            result -> {
                if (result.getResultCode() == Activity.RESULT_OK) {
                    Intent data = result.getData();
                    if (data != null) {
                        // Verileri Intent'ten alın
                        String nameAndSurname = data.getStringExtra("nameAndSurname");
                        String age = data.getStringExtra("age");
                        String weight = data.getStringExtra("height");

                        // Verileri XML dosyasındaki view'lere yerleştirin
                        binding.textView11.setText(nameAndSurname);
                        binding.textView10.setText(weight);
                        binding.textView2.setText(age);
                    }
                }
            }
    );

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        binding = ProfilePageBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
    }

    public void editProfile(View view)
    {
        Intent intent = new Intent(ProfilePage.this, EditProfile.class);
        activityResultLauncher.launch(intent);
    }

    public void goHome(View view)
    {
        Intent intent = new Intent(ProfilePage.this, BasePage.class);
        startActivity(intent);
    }
}
