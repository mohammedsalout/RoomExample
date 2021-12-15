package com.mas.roomexample.models;

import androidx.annotation.Nullable;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;

import com.mas.roomexample.utils.Utils;

import java.util.Date;

// we can change the table name by set table name, if you don't
// add  any value then the table name will be the same of class name
// every annotations follow for the next line only
@Entity(tableName = "EmployeeTable")
/**
 * to enable room form using the type converters
**/
@TypeConverters(Utils.class)
public class Employee {
    // this is the primary key , can't be null
    @PrimaryKey
    private long id;

    // if we want to change the column name we can use the
    // Column info tag
    @ColumnInfo(name = "employee_name")
    private String name;

    private String email;

    // the SQLite data base not support the date input so we should
    // use converter to convert the date form date to long and long to date when use
    private Date birthDate;

    public Employee(long id, String name, String email, Date birthDate) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.birthDate = birthDate;
    }

    public long getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", birthDate=" + birthDate +
                '}';
    }
}
