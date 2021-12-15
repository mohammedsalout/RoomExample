package com.mas.roomexample.models;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;

import com.mas.roomexample.utils.Utils;

import java.util.Date;


// to link tables with other we using the @ForeignKey annotations
// with the child table
@Entity(foreignKeys = {@ForeignKey(entity = Employee.class,
        parentColumns = {"id"},
        childColumns = {"empId"},
        onDelete = ForeignKey.CASCADE,
        onUpdate = ForeignKey.CASCADE)})
@TypeConverters(Utils.class)
public class Salary {

    @PrimaryKey(autoGenerate = true)
    private int id;
    private double amount;
    private long empId;
    private Date date;


    public Salary(double amount, long empId, Date date) {
        this.amount = amount;
        this.empId = empId;
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public long getEmpId() {
        return empId;
    }

    public void setEmpId(long empId) {
        this.empId = empId;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
