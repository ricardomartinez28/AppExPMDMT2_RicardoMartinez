package com.example.a21759217.appexpmdmt2_ricardomartinez;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.a21759217.appexpmdmt2_ricardomartinez.model.Skin;

import java.util.ArrayList;

public class AdaptadorSkin extends RecyclerView.Adapter<AdaptadorSkin.SkinViewHolder> implements View.OnClickListener {

    private ArrayList<Skin>datos;
    private View.OnClickListener listener;

    public AdaptadorSkin(ArrayList<Skin> datos) {
        this.datos = datos;
    }

    @NonNull
    @Override
    public SkinViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v= LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.skin_layout,viewGroup,false);
        v.setOnClickListener(this);
        SkinViewHolder svh= new SkinViewHolder(v);

        return svh;
    }

    @Override
    public void onBindViewHolder(@NonNull SkinViewHolder skinViewHolder, int i) {
        skinViewHolder.bindSkin(datos.get(i));
    }

    @Override
    public int getItemCount() {
        return datos.size();
    }

    @Override
    public void onClick(View v) {
        if(listener != null) {
            listener.onClick(v);
        }
    }

    public void setOnClickListener(View.OnClickListener listener) {
        this.listener = listener;
    }

    public static class SkinViewHolder extends RecyclerView.ViewHolder{

        private TextView tvNombre;
        private TextView tvRareza;

        public SkinViewHolder(@NonNull View itemView) {
            super(itemView);
            tvNombre=itemView.findViewById(R.id.tvNombre);
            tvRareza=itemView.findViewById(R.id.tvRareza);
        }

        public void bindSkin(Skin skin){
            tvNombre.setText(skin.getName());
            tvRareza.setText(skin.getRarity());
        }
    }



}
