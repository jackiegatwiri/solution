package com.jacky.innovex;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class Adapter extends RecyclerView.Adapter<Adapter.ComputeViewHolder> {
    private ArrayList<Compute> compute = new ArrayList<>();
    private Context context;

    public Adapter(ArrayList<Compute> compute, Context context) {
        this.compute = compute;
        this.context = context;
    }

    @NonNull
    @Override
    public ComputeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item2, parent, false);
        return new ComputeViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ComputeViewHolder holder, int position) {
        holder.bindCompute(compute.get(position));
    }

    @Override
    public int getItemCount() {
        return compute.size();
    }


    public class ComputeViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.month)
        TextView months;
        @BindView(R.id.initial)
        TextView initial;
        @BindView(R.id.generated)
        TextView generated;
        @BindView(R.id.initialgenerated)
        TextView initialgenerated;
        @BindView(R.id.total)
        TextView total;
        @BindView(R.id.interest)
        TextView interest;

        public ComputeViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
        public void bindCompute(Compute compute)
        {
            months.setText(String.valueOf(compute.getMonth()));
            initial.setText(compute.getInitial());
            generated.setText(compute.getGenerated());
            initialgenerated.setText(compute.getInitialgenerated());
            total.setText(compute.getTotal());
            interest.setText(compute.getInterest());

        }
    }
}
