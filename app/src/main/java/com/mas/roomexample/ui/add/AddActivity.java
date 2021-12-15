package com.mas.roomexample.ui.add;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.DatePicker;
import android.widget.Toast;

import com.mas.roomexample.R;
import com.mas.roomexample.databinding.ActivityAddBinding;
import com.mas.roomexample.models.Employee;
import com.mas.roomexample.ui.main.MainViewModel;

import java.util.Calendar;
import java.util.Date;

public class AddActivity extends AppCompatActivity {

    ActivityAddBinding binding;
    Employee employee;
    Calendar selectedDate = null;
    MainViewModel mainViewModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAddBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        mainViewModel = new ViewModelProvider(this ).get(MainViewModel.class);

        binding.btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //int id, String name, String email, Date birthDate
                String id = binding.txtId.getText().toString();
                String name = binding.txtName.getText().toString();
                String email = binding.txtEmail.getText().toString();
                if (selectedDate ==null || !isValidInput(new String[]{id,  name , email})){
                    Toast.makeText(AddActivity.this, "All fields required", Toast.LENGTH_SHORT).show();
                    return;
                }
                mainViewModel.insertEmploy(new Employee(Long.parseLong(id),  name , email ,selectedDate.getTime()));
                finish();
                Toast.makeText(AddActivity.this, "Added", Toast.LENGTH_SHORT).show();

            }
        });


        // add date
        binding.txtDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar calendar = Calendar.getInstance();
                // Launch Date Picker Dialog
                DatePickerDialog dialog = new DatePickerDialog(AddActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int y, int m, int d) {
                        selectedDate = Calendar.getInstance();
                        selectedDate.set(y,m,d);
                        binding.txtDate.setText(y+"/"+m+"/"+d);
                    }
                },calendar.get(Calendar.YEAR),calendar.get(Calendar.MONTH) ,calendar.get(Calendar.DAY_OF_MONTH));
                dialog.show();
            }
        });

    }

    private boolean isValidInput(String[] inputs){
        for (String s : inputs){
            if (TextUtils.isEmpty(s))
                return false;
        }
        return true;
    }
}