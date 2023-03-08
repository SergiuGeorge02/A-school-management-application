package com.example.project;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;
import java.util.TreeMap;

public class DepartmentsFileController {


   @FXML
    private Button backButton;
   @FXML
   private Button restoreButton;
   @FXML
   private Button artButton;

   @FXML
   private Button computersButton;
    @FXML
    private Button scienceButton;

    @FXML
    private Button literatureButton;

    @FXML
    private Label tax;
    @FXML
    private Label course1;
    @FXML
    private Label course2;
    @FXML
    private Label course3;

    @FXML

    private Label course4;




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
                FXMLLoader loader = new FXMLLoader(getClass().getResource("departmentsfile.fxml"));
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
    private void art(ActionEvent event) {
        artButton.setOnAction(e -> {
            int tax2;
          Art art=new Art(new ArrayList<>());
          if(art instanceof unPaid)
              tax2=0;
          course1.setText("Graphic design");
          course2.setText("3D Design");
          course3.setText("Drawing");
          course4.setText("Painting");
          tax.setText("There is no tax for this course");



        });
    }
    @FXML
    private void computers(ActionEvent event) {
        computersButton.setOnAction(e -> {
            int tax2;
            Computer computer=new Computer(new ArrayList<>());
            if(computer instanceof unPaid)
                tax2=0;
            else
                tax2=computer.gettax();
            course1.setText("Algorithms");
            course2.setText("Operating systems");
            course3.setText("Programming");
            course4.setText("Computer Architecture");
            tax.setText("The tax for this course is: "+tax2);



        });
    }
    @FXML
    private void science(ActionEvent event) {
        scienceButton.setOnAction(e -> {
            int tax2;
            Science science=new Science(new ArrayList<>());
            if(science instanceof unPaid)
                tax2=0;
            else
                tax2=science.gettax();

            course1.setText("Biology");
            course2.setText("Geography");
            course3.setText("Physics");
            course4.setText("Chemistry");
            tax.setText("There is no tax for this course");



        });
    }
    @FXML
    private void literature(ActionEvent event) {
        literatureButton.setOnAction(e -> {
            int tax2;
            Literature literature=new Literature(new ArrayList<>());
            if(literature instanceof unPaid)
                tax2=0;


            course1.setText("How to read a book");
            course2.setText("The beautiful part of reading");
            course3.setText("The correct way to read a book");
            tax.setText("There is no tax for this course");



        });
    }
}
