package com.mas.roomexample.adapters;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.mas.roomexample.R;
import com.mas.roomexample.database.SalaryValueCallback;
import com.mas.roomexample.models.Employee;
import com.mas.roomexample.ui.main.MainViewModel;

import java.util.List;

public class EmployeeAdapter extends RecyclerView.Adapter<EmployeeAdapter.myViewHolder> {
    List<Employee> data;
    MainViewModel mainViewModel;

    public EmployeeAdapter(List<Employee> data, MainViewModel mainViewModel) {
        this.data = data;
        this.mainViewModel = mainViewModel;
    }

    @NonNull
    @Override
    public myViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.data_items, null, false);
        return new myViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull myViewHolder holder, int position) {
        holder.tvName.setText(data.get(position).getName());
        mainViewModel.getSalaryById(data.get(position).getId(), new SalaryValueCallback() {
            @Override
            public void onSuccess(double salaryFormId) {
                holder.tvSalary.setText(salaryFormId+"");
            }
        });
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public static class myViewHolder extends RecyclerView.ViewHolder {
        TextView tvName, tvSalary;

        public myViewHolder(@NonNull View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.tvName);
            tvSalary = itemView.findViewById(R.id.tvSalary);
        }

    }
}
