package com.mas.roomexample.database;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.mas.roomexample.models.Employee;

import java.util.List;
@Dao
interface EmployeeDao {

 // adding single employee
 @Insert
 void insertEmployee(Employee employee);

 // this method with (Employee... ) enable you from adding n number of employee
 @Insert
 void insertEmployee(Employee... employee);

 @Update
 void updateEmployee(Employee employee);

 @Delete
 void deleteEmployee(Employee employee);

 //delete by email
 @Query("Delete from EmployeeTable where email =:email")
 void deleteEmployee(String email);

 //get all table data
 @Query("Select * from EmployeeTable")
 LiveData<List<Employee>> getEmployee();

 //get data by name (like name)
 @Query("Select * from EmployeeTable where employee_name like '%'||:name||'%'")
 LiveData<List<Employee>> getEmployee(String name);
}
