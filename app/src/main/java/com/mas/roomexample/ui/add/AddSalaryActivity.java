package com.mas.roomexample.ui.add;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import com.mas.roomexample.R;
import com.mas.roomexample.databinding.ActivityAddSalaryBinding;
import com.mas.roomexample.models.Employee;
import com.mas.roomexample.models.Salary;
import com.mas.roomexample.ui.main.MainViewModel;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AddSalaryActivity extends AppCompatActivity {

    ActivityAddSalaryBinding binding;
    List<Employee> data = new ArrayList<>();
    Long empId = null;
    Map<String , Long> employeeMap = new HashMap<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAddSalaryBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        MainViewModel viewModel = new ViewModelProvider(this).get(MainViewModel.class);

        viewModel.getAllEmployees().observe(this, new Observer<List<Employee>>() {
            @Override
            public void onChanged(List<Employee> employees) {
                data.clear();
                data.addAll(employees);
                ArrayList<String> options = new ArrayList<>();
                for (Employee e : employees) {
                    options.add(e.getName());
                    employeeMap.put(e.getName() , e.getId());
                }
                ArrayAdapter<String> adapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_item, options);
                binding.spEmploy.setAdapter(adapter); // this will set list of values to spinner
            }
        });

        binding.spEmploy.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                empId = employeeMap.get(binding.spEmploy.getSelectedItem()+"");
                Log.e("MAS_DATA" , empId+" was selected");
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });


        binding.btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (empId != null) {
                    if (TextUtils.isEmpty(binding.txtSalary.getText())) {
                        Toast.makeText(AddSalaryActivity.this, "Please add salary", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    String amount = binding.txtSalary.getText().toString();
                    viewModel.insertSalary(new Salary(Double.parseDouble(amount), empId,
                            Calendar.getInstance().getTime()));
                    finish();
                    Toast.makeText(AddSalaryActivity.this, "Added", Toast.LENGTH_SHORT).show();

                } else {
                    Toast.makeText(AddSalaryActivity.this, "Select employ", Toast.LENGTH_SHORT).show();
                }

            }
        });
    }
}