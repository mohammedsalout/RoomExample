package com.mas.roomexample.ui.main;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.mas.roomexample.adapters.EmployeeAdapter;
import com.mas.roomexample.databinding.ActivityMainBinding;
import com.mas.roomexample.models.Employee;
import com.mas.roomexample.ui.add.AddActivity;
import com.mas.roomexample.ui.add.AddSalaryActivity;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    /**
     * Step's to Room database
     * 1- Create entity's & annotations
     * 2- Create the DAO (Data access objects)
     * 3- use the database
     ***/

    EmployeeAdapter employeeAdapter;
    List<Employee> employeesList = new ArrayList<>();

    ActivityMainBinding binding;
    MainViewModel mainViewModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

         mainViewModel = new ViewModelProvider(this).get(MainViewModel.class);


        employeeAdapter = new EmployeeAdapter(employeesList, mainViewModel);
        binding.rvTable.setLayoutManager(new GridLayoutManager(this , 1));
        binding.rvTable.setAdapter(employeeAdapter);



        binding.btnAddEmploy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext() , AddActivity.class));
            }
        });

        binding.btnAddSalary.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext() , AddSalaryActivity.class));
            }
        });

    }

    @Override
    protected void onResume() {
        super.onResume();
        mainViewModel.getAllEmployees().observe(this, new Observer<List<Employee>>() {
            @Override
            public void onChanged(List<Employee> employees) {
                employeesList.clear();
                employeesList.addAll(employees);
                employeeAdapter.notifyDataSetChanged();
            }
        });
    }
}