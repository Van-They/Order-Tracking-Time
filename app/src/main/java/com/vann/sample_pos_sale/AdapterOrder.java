package com.vann.sample_pos_sale;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.vann.sample_pos_sale.databinding.ItemOrderBinding;

import java.util.List;

public class AdapterOrder extends RecyclerView.Adapter<AdapterOrder.ViewHolder> {

    ItemOrderBinding binding;
    List<OrderModel> orderModels;
    Context context;
    LayoutInflater inflater;
    EventHandler eventHandler;

    String s = "";

    public AdapterOrder(List<OrderModel> orderModels, Context context, EventHandler eventHandler) {
        this.orderModels = orderModels;
        this.context = context;
        this.eventHandler = eventHandler;
        this.inflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        binding = ItemOrderBinding.inflate(inflater, parent, false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        OrderModel orderModel = orderModels.get(position);

        holder.setText(orderModel.getId(), orderModel.getOrderStatus(), orderModel.getLate());

        holder.itemOrderBinding.getRoot().setOnClickListener(view -> eventHandler.getIndex(orderModel, position, ""));

        holder.itemOrderBinding.checkReceive.setOnCheckedChangeListener((compoundButton, b) -> {
            if (compoundButton.isChecked()) {
                eventHandler.getIndex(orderModel, position, "receive");
            }
        });

    }

    @Override
    public int getItemCount() {
        return orderModels.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        ItemOrderBinding itemOrderBinding;

        public ViewHolder(@NonNull ItemOrderBinding itemOrderBinding) {
            super(itemOrderBinding.getRoot());
            this.itemOrderBinding = itemOrderBinding;
        }

        void setText(String id, String status, String late) {
            itemOrderBinding.orderNumber.setText(id);
            itemOrderBinding.orderStatus.setText(status);
            itemOrderBinding.orderLate.setText(late);
        }

    }
}
