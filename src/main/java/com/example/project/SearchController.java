package com.example.project;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class SearchController {
    @FXML
    private Button Backbutton;
    @FXML
    private Button resetbutton;
    @FXML
    private Button submitButton;
    @FXML
    private TextField studentid;
    @FXML
    private Label notindb;
    @FXML
    private Label found;
    @FXML
    private Label student;
    @FXML
    private void back(ActionEvent event) {
        Backbutton.setOnAction(e -> {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("hello-view.fxml"));
                Parent root = loader.load();
                Scene scene = new Scene(root);
                Stage stage = (Stage) Backbutton.getScene().getWindow();
                stage.setScene(scene);
                stage.show();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        });
    }
    @FXML
    private void reset(ActionEvent event) {
        resetbutton.setOnAction(e -> {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("display.fxml"));
                Parent root = loader.load();
                Scene scene = new Scene(root);
                Stage stage = (Stage) resetbutton.getScene().getWindow();
                stage.setScene(scene);
                stage.show();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        });
    }
    public void submit(ActionEvent event)
    {

        submitButton.setOnAction(e->{
            String id=studentid.getText();
            int id1= Integer.parseInt(id);
            if(check(id1)==false){
                Not_in_database();
            }
            else{
                student(id1);
            }

        });
    }
    private boolean check(int id){
        DatabaseConnection connect=new DatabaseConnection();
        Connection connectdb= connect.getConnection();
        String query="SELECT count(1) from student WHERE id= "+id;
        try {
            Statement statement=connectdb.createStatement();
            ResultSet queryResult=statement.executeQuery(query);
            while(queryResult.next()){
                if(queryResult.getInt(1)==1) {
                    return true;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
    private void Not_in_database(){
        notindb.setText("The student is not in the database");
    }
    private void student(int id){
        found.setText("Student with id: "+ id+" was found ");
        String name=get_name(id);
        String age=get_age(id);
        String gender=get_gender(id);
        String department=get_department(id);
        String s="ID: "+id + ", name: "+name+", age: "+age+", gender: "+gender+" at department: "+department;
        student.setText(s);
    }
    private String get_name(int id){
        String s="name";
        DatabaseConnection connect=new DatabaseConnection();
        Connection connectdb= connect.getConnection();
        String query="SELECT name from student WHERE id= "+id;
        try {
            Statement statement=connectdb.createStatement();
            ResultSet queryResult=statement.executeQuery(query);
            while(queryResult.next())
            s=queryResult.getString(1);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return s;
    }
    private String get_age(int id){
        String s="age";
        DatabaseConnection connect=new DatabaseConnection();
        Connection connectdb= connect.getConnection();
        String query="SELECT age from student WHERE id= "+id;
        try {
            Statement statement=connectdb.createStatement();
            ResultSet queryResult=statement.executeQuery(query);
            while(queryResult.next())
            s=queryResult.getString(1);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return s;
    }
    private String get_gender(int id){
        String s="gender";
        DatabaseConnection connect=new DatabaseConnection();
        Connection connectdb= connect.getConnection();
        String query="SELECT gender from student WHERE id= "+id;
        try {
            Statement statement=connectdb.createStatement();
            ResultSet queryResult=statement.executeQuery(query);
            while(queryResult.next())
            s=queryResult.getString(1);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return s;
    }
    private String get_department(int id){
        String s="department";
        DatabaseConnection connect=new DatabaseConnection();
        Connection connectdb= connect.getConnection();
        String query="SELECT department from student WHERE id= "+id;
        try {
            Statement statement=connectdb.createStatement();
            ResultSet queryResult=statement.executeQuery(query);
            while(queryResult.next())
            s=queryResult.getString(1);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return s;
    }


}
