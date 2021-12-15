package com.mas.roomexample.database;

import android.app.Application;
import android.webkit.ValueCallback;

import androidx.lifecycle.LiveData;
import androidx.room.RoomDatabase;

import com.mas.roomexample.models.Employee;
import com.mas.roomexample.models.Salary;
import com.mas.roomexample.ui.main.MainActivity;

import java.util.List;

public class DatabaseRepository {
    EmployeeDao employeeDao;
    SalaryDao salaryDao;


    public DatabaseRepository(Application application) {
        MyDatabase database = MyDatabase.getDatabase(application);
        employeeDao = database.employeeDao();
        salaryDao = database.salaryDao();
    }

    // employee =======================================
    public void insertEmploy(Employee employee) {
        // this Executor use to run insert function in separated thread
        MyDatabase.databaseWriteExecutor.execute(new Runnable() {
            @Override
            public void run() {
                employeeDao.insertEmployee(employee);
            }
        });
    }

    public void updateEmploy(Employee employee) {
        MyDatabase.databaseWriteExecutor.execute(new Runnable() {
            @Override
            public void run() {
                employeeDao.updateEmployee(employee);
            }
        });
    }

    public void deleteEmploy(Employee employee) {
        MyDatabase.databaseWriteExecutor.execute(new Runnable() {
            @Override
            public void run() {
                employeeDao.deleteEmployee(employee);
            }
        });
    }

    public LiveData<List<Employee>> getAllEmployee(){
        return  employeeDao.getEmployee();
    }

    // Salary ==============================================
    public void insertSalary(Salary Salary) {
        // this Executor use to run insert function in separated thread
        MyDatabase.databaseWriteExecutor.execute(new Runnable() {
            @Override
            public void run() {
                salaryDao.insertSalary(Salary);
            }
        });
    }

    public void getSalaryById(long empId , SalaryValueCallback callback){
        MyDatabase.databaseWriteExecutor.execute(new Runnable() {
            @Override
            public void run() {
                callback.onSuccess(salaryDao.getSalaryFormId(empId));
            }
        });

    }

    public void updateSalary(Salary Salary) {
        MyDatabase.databaseWriteExecutor.execute(new Runnable() {
            @Override
            public void run() {
                salaryDao.updateSalary(Salary);
            }
        });
    }

    public void deleteSalary(Salary Salary) {
        MyDatabase.databaseWriteExecutor.execute(new Runnable() {
            @Override
            public void run() {
                salaryDao.deleteSalary(Salary);
            }
        });
    }

}
