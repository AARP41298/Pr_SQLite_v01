package com.example.pr_sqlite_v01;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import entidades.Cliente;

public class AdaptadorClienteItem extends RecyclerView.Adapter<AdaptadorClienteItem.ExampleViewHolder> {
    private List<Cliente> mListaCliente;
    private OnItemClickListener mListener;

    public interface OnItemClickListener {
        void onItemClick(int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        mListener = listener;
    }

    public static class ExampleViewHolder extends RecyclerView.ViewHolder {
        public TextView tvNombre, tvDireccion, tvTelefono, tvCiudad, tvID;


        public ExampleViewHolder(View itemView, final OnItemClickListener listener) {
            super(itemView);
            tvNombre = itemView.findViewById(R.id.tv_ci_nombre);
            tvDireccion = itemView.findViewById(R.id.tv_ci_dir);
            tvTelefono = itemView.findViewById(R.id.tv_ci_tel);
            tvCiudad = itemView.findViewById(R.id.tv_ci_cd);
            tvID = itemView.findViewById(R.id.tv_ci_id);

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

    public AdaptadorClienteItem(List<Cliente> listaCliente) {
        mListaCliente = listaCliente;
    }

    @Override
    public ExampleViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.cliente_item, parent, false);
        ExampleViewHolder evh = new ExampleViewHolder(v, mListener);
        return evh;
    }

    @Override
    public void onBindViewHolder(ExampleViewHolder holder, int position) {
        Cliente clienteItem = mListaCliente.get(position);

        holder.tvNombre.setText(clienteItem.getNombre());
        holder.tvDireccion.setText(clienteItem.getDireccion());
        holder.tvTelefono.setText(clienteItem.getTelefono());
        holder.tvCiudad.setText(clienteItem.getCiudad());
        holder.tvID.setText(clienteItem.getIdCliente());
    }

    @Override
    public int getItemCount() {
        return mListaCliente.size();
    }
}