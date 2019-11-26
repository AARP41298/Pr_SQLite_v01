package com.example.pr_sqlite_v01;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class AdaptadorClienteItem extends RecyclerView.Adapter<AdaptadorClienteItem.ExampleViewHolder> {
    private ArrayList<ClienteItem> mListaCliente;
    private OnItemClickListener mListener;

    public interface OnItemClickListener {
        void onItemClick(int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        mListener = listener;
    }

    public static class ExampleViewHolder extends RecyclerView.ViewHolder {
        public TextView tvDia, tvServicio, tvHora, tvNombre;


        public ExampleViewHolder(View itemView, final OnItemClickListener listener) {
            super(itemView);
            tvDia = itemView.findViewById(R.id.tv_ci_dia);
            tvServicio = itemView.findViewById(R.id.tv_ci_servicio);
            tvHora = itemView.findViewById(R.id.tv_ci_hora);
            tvNombre = itemView.findViewById(R.id.tv_ci_nombre);


            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (listener != null) {
                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION) {
                            listener.onItemClick(position);
                        }
                    }
                }
            });
        }
    }

    public AdaptadorItem(ArrayList<CitaItem> listaCancion) {
        mListaCita = listaCancion;
    }


    @Override
    public ExampleViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.cita_item, parent, false);
        ExampleViewHolder evh = new ExampleViewHolder(v, mListener);
        return evh;
    }

    @Override
    public void onBindViewHolder(ExampleViewHolder holder, int position) {
        CitaItem citaActual = mListaCita.get(position);
        holder.tvDia.setText(citaActual.getDia());
        holder.tvServicio.setText(citaActual.getServicio());
        holder.tvHora.setText(citaActual.getHora());
        holder.tvNombre.setText(citaActual.getNombre());


    }

    @Override
    public int getItemCount() {
        return mListaCita.size();
    }
}