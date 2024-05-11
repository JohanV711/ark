package com.cumn.ark.anim;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.cumn.ark.R;

import java.util.List;


public class PetAdapter extends RecyclerView.Adapter<PetViewHolder> {
    private final List<Pet> petList;
    private final Context context;

    public PetAdapter(List<Pet> petList, Context context) {
        this.petList = petList;
        this.context = context;
    }

    @NonNull
    @Override
    public PetViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_mascota, parent, false);
        return new PetViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PetViewHolder holder, int position) {
        Pet pet = petList.get(position);
        holder.bind(pet);
    }

    @Override
    public int getItemCount() {
        return petList.size();
    }
}
