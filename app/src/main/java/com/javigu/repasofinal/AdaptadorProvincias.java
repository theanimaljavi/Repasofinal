package com.javigu.repasofinal;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class AdaptadorProvincias extends RecyclerView.Adapter<AdaptadorProvincias.MyViewHolder> {
    //crear array del objeto a cargar
    ArrayList<Provincias> listaProvincias;
    //sacar la url
    String url;

    public AdaptadorProvincias(ArrayList<Provincias> listaProvincias) {
        this.listaProvincias = listaProvincias;
    }

    @NonNull
    @org.jetbrains.annotations.NotNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull @org.jetbrains.annotations.NotNull ViewGroup parent, int viewType) {
        //inflar la vista del item list
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list,null,false);
        //devuelve la vista (imagen + titulo)
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull @org.jetbrains.annotations.NotNull AdaptadorProvincias.MyViewHolder holder, int position) {
        //aquí se usan los datos creados en la clase "MyViewHolder"
        //le decimos la posicion y lo q vamos a recuperar para ese dato
            //se usa primero el tvNombre para sacar la url, despues se pasará al titulo
        holder.tvNombre.setText(listaProvincias.get(position).getImagen());
        url = (String) holder.tvNombre.getText();
            //cambiar el tvNombre al titulo
        holder.tvNombre.setText(listaProvincias.get(position).getNombre());
        //poner la imagen con el Picasso
        Picasso.with(holder.ivProvincia.getContext())
                .load(url)
                .into(holder.ivProvincia);
    }

    @Override
    public int getItemCount() {
        return listaProvincias.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        //en esta clase se crea y se referencia las cosas creadas en el item list
        TextView tvNombre;
        ImageView ivProvincia;

        public MyViewHolder(@NonNull @org.jetbrains.annotations.NotNull View itemView) {
            super(itemView);
            tvNombre = itemView.findViewById(R.id.tvTitulo);
            ivProvincia = itemView.findViewById(R.id.ivProvincia);
        }
    }
}
