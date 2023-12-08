package com.ahmetozdemir.gymapp;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ahmetozdemir.gymapp.databinding.RecyclerRowBinding;

import java.util.ArrayList;

public class SupplementAdapter extends RecyclerView.Adapter< SupplementAdapter.SupplementHolder> // Bu sınıf recyclerView içinde görülen supplementlerin düzenini yönetir.
{
    ArrayList <Supplement> supplementArrayList; // Supplement öğelerimizi burada depoluyoruz

    public  SupplementAdapter(ArrayList<Supplement> supplementArrayList) // constructor
    {
        this.supplementArrayList = supplementArrayList;
    }

    /* ViewHolder recyclerView gibi tasarımlarda çoklu nesnelere fazla hafıza kaplamadan erişebilmeyi sağlayan bir tasarım desenidir.*/
    @NonNull
    @Override
    public SupplementHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) //viewHolder oluşturur.
    {
        RecyclerRowBinding recyclerRowBinding = RecyclerRowBinding.inflate(LayoutInflater.from(parent.getContext()),parent,false);
        return new SupplementHolder(recyclerRowBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull SupplementHolder holder, int position) // Belirli bir pozisyondaki öğenin içeriğini atar.
    {
        holder.binding.recyclerViewTextView.setText(supplementArrayList.get(position).supplementName);

        int photoId = supplementArrayList.get(position).supplementImage;

        if (photoId != 0)
        {
            holder.binding.imageView1.setImageResource(photoId);
        }

        else
        {
            // Eğer photoId yoksa, varsayılan görüntüyü kullan.
            holder.binding.imageView1.setImageResource(R.drawable.ic_launcher_foreground);
        }

        holder.itemView.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent(holder.itemView.getContext(), DetailsActivity.class);
                intent.putExtra("landmark",supplementArrayList.get(position));
                holder.itemView.getContext().startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() // Veri kümesindeki eleman sayısını tutar.
    {
        return supplementArrayList.size();
    }

    public class SupplementHolder extends RecyclerView.ViewHolder // Her bir öğenin görünümünü tutar
    {
        private RecyclerRowBinding binding;
        public SupplementHolder(RecyclerRowBinding binding)
        {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
