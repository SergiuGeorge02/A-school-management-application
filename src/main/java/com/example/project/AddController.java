package com.example.project;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class AddController {
    @FXML
    private Button Backbutton;
    @FXML
    private Button resetbutton;
    @FXML
    private Button submitButton;

    @FXML
    private Label genderlabel;
    @FXML
    private Label departmentlabel;
    @FXML
    private RadioButton maleradio;
    @FXML
    private RadioButton femaleradio;
    @FXML
    private RadioButton artradio;
    @FXML
    private RadioButton computersradio;
    @FXML
    private RadioButton literatureradio;
    @FXML
    private RadioButton scienceradio;
    @FXML
    private TextField studentname;
    @FXML
    private TextField studentage;
    @FXML
    private Label validateadd;
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
    private String check_departement(){
        String department="not";

        if(artradio.isSelected() && computersradio.isSelected()==false && scienceradio.isSelected()==false && literatureradio.isSelected()==false)
            department="Art";
        else if(artradio.isSelected()==false && computersradio.isSelected() && scienceradio.isSelected()==false && literatureradio.isSelected()==false)
            department="Computers";
        else if(artradio.isSelected()==false && computersradio.isSelected()==false && scienceradio.isSelected() && literatureradio.isSelected()==false)
            department="Science";
        else if(artradio.isSelected()==false && computersradio.isSelected()==false && scienceradio.isSelected()==false && literatureradio.isSelected())
            department="Literature";
        else if(artradio.isSelected()==false && computersradio.isSelected()==false && scienceradio.isSelected()==false && literatureradio.isSelected()==false)
        { departmentlabel.setText("At least one option must be selected by the user");
            department="nooption";
        }
        if(department.equals("not")){
            departmentlabel.setText("Only one option has to be selected");
        }
        return department;
    }
    public void submit(ActionEvent event)
    {

        submitButton.setOnAction(e->{
            String gender="gender";
            String department="";
            String name="";
            String age="";
            int age_int=0;
            int id=0;
            if(maleradio.isSelected() && femaleradio.isSelected())
               gender="both";
            else if(maleradio.isSelected()==false && femaleradio.isSelected()==false)
                gender="none";
            else if(maleradio.isSelected())
                gender="Male";
            else
                gender="Female";

               department=check_departement();

               name=studentname.getText();
               age=studentage.getText();
               if(gender.equals("both"))
                   genderlabel.setText("Only one option has to be selected");
               if(gender.equals("none"))
                   genderlabel.setText("At least one option must be selected by the user");
                if(gender.equals("both")==false && gender.equals("none")==false && department.equals("nooption")==false && department.equals("not")==false){
                    validateAddition(name,age,gender,department);
                }
                else
                    validateadd.setText("There was a problem adding the student ");





        });
    }
   private void validateAddition(String name, String age, String gender, String department){
       DatabaseConnection connect=new DatabaseConnection();
       Connection connectdb= connect.getConnection();
       String verify="SELECT count(1) FROM student WHERE name='"+name+"' and age='"+age+"' and gender='"+gender+"' and department='"+department+"'";
       try {
           Statement statement=connectdb.createStatement();
           ResultSet queryResult=statement.executeQuery(verify);
            while(queryResult.next()){
                if(queryResult.getInt(1)==1) {
                    validateadd.setText("Student you are trying to add is already in the database");
                    return;
                }
                else{
                    Statement statement1=connectdb.createStatement();
                    String getidquery="SELECT MAX(COALESCE(id,0)) FROM student";
                    ResultSet queryidResult=statement1.executeQuery(getidquery);
                    int id=0;
                    while(queryidResult.next()){
                         id=queryidResult.getInt(1);
                    }
                        id+=1;
                        Statement statement2=connectdb.createStatement();
                        String sqlinsert=" INSERT INTO student (id, name, age, gender,department) VALUES ("+id+", '"+name+"','"+age+"','"+gender+"','"+department+"');";
                        statement2.executeUpdate(sqlinsert);
                        validateadd.setText("Student was successfully added");

                }
            }
       } catch (SQLException e) {
           e.printStackTrace();
       }
   }
   @FXML
    private void reset(ActionEvent event) {
        resetbutton.setOnAction(e -> {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("add.fxml"));
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


}
