package com.mas.roomexample.ui.main;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.mas.roomexample.database.DatabaseRepository;
import com.mas.roomexample.database.MyDatabase;
import com.mas.roomexample.database.SalaryValueCallback;
import com.mas.roomexample.models.Employee;
import com.mas.roomexample.models.Salary;

import java.util.List;

public class MainViewModel extends AndroidViewModel {
    DatabaseRepository repository;

    public MainViewModel(@NonNull Application application) {
        super(application);
        repository = new DatabaseRepository(application);
    }

    public void insertEmploy(Employee employee){
        repository.insertEmploy(employee);
    }

    public void updateEmploy(Employee employee){
        repository.updateEmploy(employee);
    }

    public void deleteEmploy(Employee employee){
        repository.deleteEmploy(employee);
    }

    public LiveData<List<Employee>> getAllEmployees(){
        return repository.getAllEmployee();
    }
    // Salary ==============================================
    public void insertSalary(Salary salary){
        repository.insertSalary(salary);
    }

    public void updateSalary(Salary salary){
        repository.updateSalary(salary);
    }

    public void deleteSalary(Salary salary){
        repository.deleteSalary(salary);
    }

    public void getSalaryById(long empId , SalaryValueCallback callback){
         repository.getSalaryById(empId , callback );
    }
}
