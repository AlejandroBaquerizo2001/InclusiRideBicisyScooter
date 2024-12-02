package com.example.inclusiridebicisyscooter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.inclusiridebicisyscooter.Models.Pedido;

import java.util.List;

public class PedidoAdapter extends RecyclerView.Adapter<PedidoAdapter.PedidoViewHolder> {
    private List<Pedido> pedidos;

    public static class PedidoViewHolder extends RecyclerView.ViewHolder {
        public TextView descripcion;
        public TextView fecha;

        public PedidoViewHolder(View itemView) {
            super(itemView);
            descripcion = itemView.findViewById(R.id.descripcion);
            fecha = itemView.findViewById(R.id.fecha);
        }
    }

    public PedidoAdapter(List<Pedido> pedidos) {
        this.pedidos = pedidos;
    }

    @NonNull
    @Override
    public PedidoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_pedido, parent, false);
        return new PedidoViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PedidoViewHolder holder, int position) {
        Pedido pedido = pedidos.get(position);
        holder.descripcion.setText(pedido.descripcion);
        holder.fecha.setText(pedido.fecha);
    }

    @Override
    public int getItemCount() {
        return pedidos != null ? pedidos.size() : 0;
    }

    public void setPedidos(List<Pedido> pedidos) {
        this.pedidos = pedidos;
        notifyDataSetChanged();
    }
}

