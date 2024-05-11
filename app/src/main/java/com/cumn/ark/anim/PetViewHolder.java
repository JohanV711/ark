package com.cumn.ark.anim;

import android.annotation.SuppressLint;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.cumn.ark.R;

public class PetViewHolder extends RecyclerView.ViewHolder {
    private final TextView nombre;
    private final TextView tipo;
    private final TextView raza;
    private final TextView genero;
    private final TextView peso;
    //private final TextView image;

    @SuppressLint("WrongViewCast")
    public PetViewHolder(@NonNull View itemView) {
        super(itemView);
        nombre = itemView.findViewById(R.id.name);
        tipo = itemView.findViewById(R.id.tipo);
        raza = itemView.findViewById(R.id.raza);
        genero = itemView.findViewById(R.id.genero);
        peso = itemView.findViewById(R.id.peso);
        //image = itemView.findViewById(R.id.imageView2);
    }
    public void bind(Pet pet){
        nombre.setText(pet.getNombre());
        tipo.setText(pet.getTipo());
        raza.setText(pet.getRaza());
        genero.setText(pet.getGenero());
        peso.setText(String.valueOf(pet.getPeso()));
    }
}
