package com.mas.roomexample.database;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.mas.roomexample.models.Employee;
import com.mas.roomexample.models.Salary;

import java.util.Date;
import java.util.List;

/**
 * DAO = data access object use to handle with data base
 **/
@Dao
interface SalaryDao {

    @Insert
    void insertSalary(Salary salary);

    @Update
    void updateSalary(Salary salary);

    @Delete
    void deleteSalary(Salary salary);

//    @Query("Select * from Salary where empId =:empId AND date >=:from AND date <=:to order by date desc")
//    List<LiveData<Salary>> getEmployeeSalaries(long empId, Date from, Date to);

    @Query("select sum(amount) from Salary where empId =:empIOd")
    double getSalaryFormId(long empIOd);
}
