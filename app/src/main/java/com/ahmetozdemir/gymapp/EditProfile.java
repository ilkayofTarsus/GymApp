package com.ahmetozdemir.gymapp;

import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.os.Bundle;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import com.ahmetozdemir.gymapp.databinding.EditProfileBinding;

public class EditProfile extends AppCompatActivity
{
    private EditProfileBinding binding;

    private ActivityResultLauncher<Intent> activityResultLauncher = registerForActivityResult
            (
                new ActivityResultContracts.StartActivityForResult(),
                result ->
                {
                    if (result.getResultCode() == Activity.RESULT_OK)
                    {
                        Intent data = result.getData();

                        if (data != null)
                        {
                            // Verileri Intent'ten alıyoruz
                            String nameAndSurname = data.getStringExtra("nameAndSurname");
                            String age = data.getStringExtra("age");
                            String weight = data.getStringExtra("weight");
                        }
                    }
                }
            );

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        binding = EditProfileBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
    }

    public void saveChanges(View view)
    {
        String nameAndSurname = binding.nameAndSurname.getText().toString();
        String age = binding.age.getText().toString();
        String height = binding.height.getText().toString();

        Intent resultIntent = new Intent();

        resultIntent.putExtra("nameAndSurname", nameAndSurname);
        resultIntent.putExtra("age", age);
        resultIntent.putExtra("height", height);

        setResult(Activity.RESULT_OK, resultIntent);

        // Burada activityResultLauncher'ı çağırmak gerekmiyor, çünkü setResult zaten sonucu iletecek
        finish();
    }

    public void goProfile(View view)
    {
        Intent intent = new Intent(EditProfile.this, ProfilePage.class);
        startActivity(intent);
    }

    public void goHome(View view)
    {
        Intent intent = new Intent(EditProfile.this, BasePage.class);
        startActivity(intent);
    }
}
