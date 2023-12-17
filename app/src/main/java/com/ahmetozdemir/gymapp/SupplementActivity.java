package com.ahmetozdemir.gymapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.ahmetozdemir.gymapp.databinding.ActivityMainBinding;

import java.util.ArrayList;

public class SupplementActivity extends AppCompatActivity
{
    int spantCount = 2;
    int oriantation = RecyclerView.VERTICAL;
    boolean reverseLayout = false;

    GridLayoutManager gridLayoutManager = new GridLayoutManager(this,spantCount,oriantation,reverseLayout);
    ArrayList<Supplement> supplementArrayList;
    private ActivityMainBinding binding;
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        supplementArrayList = new ArrayList<>();

        Supplement proteinPowder = new Supplement("PROTEİN TOZU\n","Ucuz yoldan protein eldesi.Ucuz yoldan protein eldesi.Ucuz yoldan protein eldesi.Ucuz yoldan protein eldesi.Ucuz yoldan protein eldesi.Ucuz yoldan protein eldesi.Ucuz yoldan protein eldesi.Ucuz yoldan protein eldesi.Ucuz yoldan protein eldesi.Ucuz yoldan protein eldesi.Ucuz yoldan protein eldesiv.Ucuz yoldan protein eldesi.Ucuz yoldan protein eldesi.Ucuz yoldan protein eldesi.Ucuz yoldan protein eldesi.Ucuz yoldan protein eldesi.Ucuz yoldan protein eldesi.Ucuz yoldan protein eldesi.Ucuz yoldan protein eldesi.Ucuz yoldan protein eldesi.Ucuz yoldan protein eldesiv.Ucuz yoldan protein eldesi.Ucuz yoldan protein eldesi.Ucuz yoldan protein eldesi.Ucuz yoldan protein eldesi.Ucuz yoldan protein eldesi.Ucuz yoldan protein eldesiv.Ucuz yoldan protein eldesi.Ucuz yoldan protein eldesi.Ucuz yoldan protein eldesi.Ucuz yoldan protein eldesi.Ucuz yoldan protein eldesi.Ucuz yoldan protein eldesi.Ucuz yoldan protein eldesi.Ucuz yoldan protein eldesi",R.drawable.sp1);
        Supplement kreatin = new Supplement("KREATİN\n","Enerji verir",R.drawable.sp2);
        Supplement beverageBox = new Supplement("SPOR İÇECEK KUTUSU\n","Spor ürünlerinizi bu kutu içinde tüketebilirsiniz.",R.drawable.sp3);
        Supplement twoProtein = new Supplement("İKİLİ SET PROTEİN\n","Birden fazla protein", R.drawable.sp4);

        supplementArrayList.add(proteinPowder);
        supplementArrayList.add(kreatin);
        supplementArrayList.add(beverageBox);
        supplementArrayList.add(twoProtein);
        supplementArrayList.add(proteinPowder);
        supplementArrayList.add(kreatin);
        supplementArrayList.add(beverageBox);
        supplementArrayList.add(twoProtein);
        supplementArrayList.add(proteinPowder);
        supplementArrayList.add(kreatin);
        supplementArrayList.add(beverageBox);
        supplementArrayList.add(twoProtein);
        supplementArrayList.add(proteinPowder);

        binding.recyclerView.setLayoutManager(gridLayoutManager);

        SupplementAdapter supplementAdapter = new SupplementAdapter(supplementArrayList);

        binding.recyclerView.setAdapter(supplementAdapter);

        //Sol üste home butonu ekler..
        //getSupportActionBar().setTitle("SUPPLEMENT  CATALAOG"); //Burası action barda yazılacak yazıyı ayarlar
        //getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        //getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_home);

    }

    public void goProfile(View view)
    {
        Intent intent = new Intent(SupplementActivity.this, ProfilePage.class);
        startActivity(intent);
    }

    public void goHome(View view)
    {
        Intent intent = new Intent(SupplementActivity.this, BasePage.class);
        startActivity(intent);
    }
}