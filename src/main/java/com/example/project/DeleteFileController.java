package com.example.project;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

public class DeleteFileController {
    @FXML
    private Button backButton;

    @FXML
    private Button submitButton;

    @FXML
    private Button restoreButton;
    @FXML
    private Label found;
    @FXML
    private Label notfound;

    @FXML
    private TextField studentid;



    private ConsoleApplication application=new ConsoleApplication();
    private Map<Person,Department> database=new TreeMap<>();

    @FXML
    private void back(ActionEvent event) {
        backButton.setOnAction(e -> {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("fileapplication.fxml"));
                Parent root = loader.load();
                Scene scene = new Scene(root);
                Stage stage = (Stage) backButton.getScene().getWindow();
                stage.setScene(scene);
                stage.show();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        });
    }
    @FXML
    private void reset(ActionEvent event) {
        restoreButton.setOnAction(e -> {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("deletefile.fxml"));
                Parent root = loader.load();
                Scene scene = new Scene(root);
                Stage stage = (Stage) restoreButton.getScene().getWindow();
                stage.setScene(scene);
                stage.show();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        });
    }
    @FXML
    private void submit(ActionEvent event) {
        submitButton.setOnAction(e -> {
            int id=0;
            String name="";
            String age="";
            String gender="";
            String id1=studentid.getText();
            try {

                BufferedReader reader=new BufferedReader(new FileReader("databasebackup.txt"));
                String read;
                while((read=reader.readLine())!=null){
                    String y=read.replace("ID: ","");
                    String s=y.replace("Student: ","");
                    String f=s.replace("has age: ","");
                    String x=f.replace("gender: ","");
                    String finals=x.replace(" and is enrolled at department:","");
                    String[] person=new String[finals.length()];
                    person=finals.split(" ");
                    if(id1.equals(person[0])){
                        name=person[1]+" "+person[2];
                        age=person[3];
                        gender=person[4];
                    }
                }
            } catch (FileNotFoundException ex) {
                ex.printStackTrace();
            } catch (IOException ex) {
                ex.printStackTrace();
            }

            if(name.equals(""))
                id= 0;
            else{
                id= Integer.parseInt(id1);
            }

            if(id==0){
               notfound.setText("Student with id: "+id1+" is not part of the database");
            }
            if(id!=0){
                database=application.recover_map();
                int age_int= Integer.parseInt(age);

                Gender b=Gender.Male;
                if(gender.equals("Male")){
                    b=Gender.Male;
                }
                else{
                    b=Gender.Female;
                }

                Person student=new Person(name,age_int,b,id);
                if(application.check_database1(student,database)){
                    Department d=database.get(student);
                    Map<Person,Department> delete=new TreeMap<>();
                    for(Map.Entry<Person,Department> entry:database.entrySet())
                        if(student.compareTo(entry.getKey())!=0)
                            delete.put(entry.getKey(),entry.getValue());
                            database=delete;
                            found.setText("Student was successfully deleted");
                }
                else{
                    notfound.setText("The student you are trying to delete is not part of the database");

                }
               application.backup1(database);
            }
        });
    }




}
