package com.vann.sample_pos_sale;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.widget.Toast;

import com.vann.sample_pos_sale.databinding.ActivityMainBinding;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    ActivityMainBinding binding;
    List<OrderModel> orderModelList;
    AdapterOrder adapterOrder;

    Handler handler;

    Map<OrderModel, Runnable> modelRunnableMap;

    List<Runnable> runnableList;

    OrderModel model;
    int index;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        modelRunnableMap = new HashMap<>();

        handler = new Handler();
        runnableList = new ArrayList<>();

        orderModelList = new ArrayList<>(List.of(
                new OrderModel("1", "", "", 0, false),
                new OrderModel("2", "", "", 0, false),
                new OrderModel("3", "", "", 0, false),
                new OrderModel("4", "", "", 0, false),
                new OrderModel("5", "", "", 0, false),
                new OrderModel("6", "", "", 0, false),
                new OrderModel("7", "", "", 0, false),
                new OrderModel("8", "", "", 0, false),
                new OrderModel("9", "", "", 0, false),
                new OrderModel("10", "", "", 0, false),
                new OrderModel("11", "", "", 0, false),
                new OrderModel("12", "", "", 0, false),
                new OrderModel("13", "", "", 0, false),
                new OrderModel("14", "", "", 0, false),
                new OrderModel("15", "", "", 0, false),
                new OrderModel("16", "", "", 0, false),
                new OrderModel("17", "", "", 0, false),
                new OrderModel("18", "", "", 0, false),
                new OrderModel("19", "", "", 0, false),
                new OrderModel("20", "", "", 0, false)
        ));

        adapterOrder = new AdapterOrder(orderModelList, this, onClick());

        binding.recyclerview.setAdapter(adapterOrder);


    }

    private EventHandler onClick() {
        return (orderModel, position, type) -> {
            if (type.equals("")) {
                if (orderModel.isStart) {
                    Toast.makeText(this, "Time Order is Tracking", Toast.LENGTH_SHORT).show();
                } else {
                    orderModel.setStart(true);
                    onStartTrackOrder(orderModel, position);
                    adapterOrder.notifyItemChanged(position, orderModel);
                }
            } else {
                onReceiveOrder(orderModel, position);
            }
        };
    }

    //

    public void onReceiveOrder(OrderModel orderModel, int position) {
        modelRunnableMap.forEach((o, r) -> {
            if (o.getId() == orderModel.getId()) {
                handler.removeCallbacks(r);
                orderModel.setOrderStatus("Order is Ready");
                orderModel.setLate("");
                orderModel.setLateM(0);
                orderModel.setStart(false);
                adapterOrder.notifyItemChanged(position, orderModel);
            }
        });
        modelRunnableMap.remove(orderModel);
    }

    private void onStartTrackOrder(OrderModel orderModel, int position) {

        Runnable runnable = new Runnable() {

            @Override
            public void run() {

                int l = orderModel.getLateM();

                handler.postDelayed(this, 5000);

                orderModel.setLateM(l += 5);

                orderModel.setLate((orderModel.getLateM()) + "m");

                orderModel.setOrderStatus("Order is Late");

                adapterOrder.notifyItemChanged(position, orderModel);
                Log.d(TAG, "run: " + orderModel);
            }
        };
        modelRunnableMap.put(orderModel, runnable);
        handler.postDelayed(runnable, 5000);

        //        modelRunnableMap.forEach((o, r) -> {
        //            if (o.getId() == orderModel.getId()) {
        //                Toast.makeText(this, "This Order is tracking", Toast.LENGTH_SHORT).show();
        //            } else {
        //                handler.postDelayed(runnable, 5000);
        //            }
        //        });


    }
}