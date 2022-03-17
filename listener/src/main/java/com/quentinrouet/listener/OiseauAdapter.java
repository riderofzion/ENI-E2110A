package com.quentinrouet.listener;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.quentinrouet.listener.databinding.OiseauLineBinding;

import java.util.ArrayList;

/**
 * Created by quentin for Androkado on 17/03/2022.
 */
public class OiseauAdapter extends RecyclerView.Adapter<OiseauAdapter.OiseauViewHolder> {
   ArrayList<Oiseau> alOiseau;
   OnOiseauClickListener listenerOiseau;

   public OiseauAdapter(ArrayList<Oiseau> alOiseau, OnOiseauClickListener listenerOiseau) {
      this.alOiseau = alOiseau;
      this.listenerOiseau = listenerOiseau;
   }

   protected class OiseauViewHolder extends RecyclerView.ViewHolder{
      OiseauLineBinding binding;
      public OiseauViewHolder(@NonNull OiseauLineBinding binding) {
         super(binding.getRoot());
         this.binding = binding;
      }
   }

   @NonNull
   @Override
   public OiseauViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
      OiseauLineBinding binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()),
              R.layout.oiseau_line,parent,false);
      return new OiseauViewHolder(binding);
   }

   @Override
   public void onBindViewHolder(@NonNull OiseauViewHolder holder, int position) {
      final Oiseau oiseauToShow = alOiseau.get(position);
      holder.binding.setOiseau(oiseauToShow);
      //Ici on détecte le clic sur un oiseau grâce à l'itemview qui est tout un item de liste
      holder.itemView.setOnClickListener((View view)->
            listenerOiseau.onOiseauClick(oiseauToShow));
   }

   @Override
   public int getItemCount() {
      return alOiseau.size();
   }

   //Interface onOiseauClick
   //La classe qui l'implémente devra dire que faire lors d'un clic sur un oiseau
   public interface OnOiseauClickListener{
      void onOiseauClick(Oiseau oiseau);
   }
}
