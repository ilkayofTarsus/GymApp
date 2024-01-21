package com.ahmetozdemir.gymapp;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.ahmetozdemir.gymapp.databinding.ActivityMainBinding;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

public class SupplementActivity extends AppCompatActivity
{
    SupplementAdapter supplementAdapter;
    private static final String API_URL = "http://212.20.147.47:7238/api/Supplement/getallsupplements";
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

       // Supplement proteinPowder = new Supplement("PROTEİN TOZU\n","Ucuz yoldan protein eldesi.Ucuz yoldan protein eldesi.Ucuz yoldan protein eldesi.Ucuz yoldan protein eldesi.Ucuz yoldan protein eldesi.Ucuz yoldan protein eldesi.Ucuz yoldan protein eldesi.Ucuz yoldan protein eldesi.Ucuz yoldan protein eldesi.Ucuz yoldan protein eldesi.Ucuz yoldan protein eldesiv.Ucuz yoldan protein eldesi.Ucuz yoldan protein eldesi.Ucuz yoldan protein eldesi.Ucuz yoldan protein eldesi.Ucuz yoldan protein eldesi.Ucuz yoldan protein eldesi.Ucuz yoldan protein eldesi.Ucuz yoldan protein eldesi.Ucuz yoldan protein eldesi.Ucuz yoldan protein eldesiv.Ucuz yoldan protein eldesi.Ucuz yoldan protein eldesi.Ucuz yoldan protein eldesi.Ucuz yoldan protein eldesi.Ucuz yoldan protein eldesi.Ucuz yoldan protein eldesiv.Ucuz yoldan protein eldesi.Ucuz yoldan protein eldesi.Ucuz yoldan protein eldesi.Ucuz yoldan protein eldesi.Ucuz yoldan protein eldesi.Ucuz yoldan protein eldesi.Ucuz yoldan protein eldesi.Ucuz yoldan protein eldesi",R.drawable.sp1,99);
        //Supplement kreatin = new Supplement("KREATİN\n","Enerji verir",R.drawable.sp2,98);
      //  Supplement beverageBox = new Supplement("SPOR İÇECEK KUTUSU\n","Spor ürünlerinizi bu kutu içinde tüketebilirsiniz.",R.drawable.sp3,97);
    //    Supplement twoProtein = new Supplement("İKİLİ SET PROTEİN\n","Birden fazla protein", R.drawable.sp4,96);

  //      supplementArrayList.add(proteinPowder);
//        supplementArrayList.add(kreatin);
      /*supplementArrayList.add(beverageBox);
        supplementArrayList.add(twoProtein);
        supplementArrayList.add(proteinPowder);
        supplementArrayList.add(kreatin);
        supplementArrayList.add(beverageBox);
        supplementArrayList.add(twoProtein);
        supplementArrayList.add(proteinPowder);
        supplementArrayList.add(kreatin);
        supplementArrayList.add(beverageBox);
        supplementArrayList.add(twoProtein);
        supplementArrayList.add(proteinPowder);*/
        fetchDataFromApi();
        binding.recyclerView.setLayoutManager(gridLayoutManager);
        supplementAdapter = new SupplementAdapter(supplementArrayList);

        Log.d("JSONObject", "Tarsuslu " + supplementArrayList.size() );


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

    private void fetchDataFromApi() {
        Log.d("JSONObject", "Tarsuslu 0 " + supplementArrayList.size());

        @SuppressLint("NotifyDataSetChanged") StringRequest stringRequest = new StringRequest(Request.Method.GET, API_URL,
                response -> {
                    try {
                        JSONArray jsonArray = new JSONArray(response);

                        Toast.makeText(this, "Fetch data supp " + jsonArray.length(), Toast.LENGTH_SHORT).show();

                        for (int i = 0; i < jsonArray.length(); i++) {
                            JSONObject jsonObject = jsonArray.getJSONObject(i);
                            Log.d("JSONObject", "Key: " + ", Value: " + jsonObject);
                            String supplementName = jsonObject.getString("title");
                            String supplementDescription = jsonObject.getString("description");
                            String photoLink = jsonObject.getString("image");
                            String supplementIDString = jsonObject.getString("id");
                            int supplementID = Integer.parseInt(supplementIDString);

                            /*

                                    Glide.with(holder.itemView.getContext())
                                        .load(photoLink)
                                        .placeholder(R.drawable.ic_launcher) // Opsiyonel: Yükleme sırasında gösterilecek resim
                                        .error(R.drawable.ic_launcher) // Opsiyonel: Yükleme hatası durumunda gösterilecek resim
                                        .diskCacheStrategy(DiskCacheStrategy.ALL)
                                        .into(holder.binding.imageView1);

                                    int photoId = supplementArrayList.get(position).supplementImage;

                                    if (photoId != 0)
                                    {
                                        holder.binding.imageView1.setImageResource(photoId);
                                    }

                                    else
                                    {
                                        holder.binding.imageView1.setImageResource(R.drawable.ic_launcher);
                                    }

                            */

                            Supplement supplement = new Supplement(supplementName, supplementDescription, photoLink,supplementID);
                            supplementArrayList.add(supplement);
                            Log.d("JSONObject", "Tarsuslu 1 " + supplementArrayList.size());
                        }
                        supplementAdapter.notifyDataSetChanged();
                        Log.d("JSONObject", "Tarsuslu 2 " + supplementArrayList.size());

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                },

                error -> {
                    Toast.makeText(this, "Error fetching data from API", Toast.LENGTH_SHORT).show();
                    Log.e("ExerciseActivity", "Volley Error: " + error.toString());

                    if (error.networkResponse != null) {
                        Log.e("ExerciseActivity", "Error Response Code: " + error.networkResponse.statusCode);

                        try {
                            String responseBody = new String(error.networkResponse.data, StandardCharsets.UTF_8);
                            Log.e("ExerciseActivity", "Error Response Body: " + responseBody);
                        } catch (Exception e) {
                            Log.e("ExerciseActivity", "Error parsing response body: " + e.getMessage());
                        }
                    }
                });

        // RequestQueue işlemi
        Log.d("JSONObject", "Tarsuslu 3 " + supplementArrayList.size());
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }

}