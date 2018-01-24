package com.teemo.example;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.teemo.dividerItemDecoration.Divider;
import com.teemo.dividerItemDecoration.DividerItemDecoration;
import com.teemo.dividerItemDecoration.SideLine;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;

    private final List<String> items = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        for (int i = 0; i < 100; i++) {
            items.add("items_" + i);
        }
        final Divider divider = new Divider(
                null
                , null
                , null
                , new SideLine(
                getResources().getDimension(R.dimen.dividerHeight))
                .setDrawable(R.drawable.divider)
        );
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.addItemDecoration(new DividerItemDecoration(this) {
            @Override
            public Divider getDivider(int itemPosition) {
                if (itemPosition >= 0 && itemPosition < items.size() - 1) {
                    return divider;
                }
                return null;
            }
        });
        recyclerView.setAdapter(new RecyclerView.Adapter() {

            class Holder extends RecyclerView.ViewHolder {
                private TextView textView;
                private ImageView imageView;

                public Holder(View itemView) {
                    super(itemView);

                    textView = itemView.findViewById(R.id.label);
                    imageView = itemView.findViewById(R.id.icon);
                }
            }

            @Override
            public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
                return new Holder(LayoutInflater.from(MainActivity.this).inflate(R.layout.item_normal, parent, false));
            }

            @Override
            public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
                Holder h = (Holder) holder;
                h.imageView.setImageResource(R.mipmap.ic_launcher);
                h.textView.setText(items.get(position));
            }

            @Override
            public int getItemCount() {
                return items.size();
            }
        });
    }
}
