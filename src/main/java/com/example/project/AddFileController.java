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
import org.w3c.dom.Text;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class AddFileController {
    @FXML
    private Label InvalidAge;
    @FXML
    private Label InvalidGender;
    @FXML
    private Label InvalidDepartment;
    @FXML
    private Button backButton;

    @FXML
    private Button submitButton;

    @FXML
    private Button restoreButton;

    @FXML
    private Label operation;
    @FXML
    private TextField studentname;
    @FXML
    private TextField studentage;

    @FXML
    private Label InvalidName;
    @FXML
    private RadioButton male;
    @FXML
    private RadioButton female;
    @FXML
    private RadioButton art;

    @FXML
    private RadioButton computers;
    @FXML
    private RadioButton science;
    @FXML
    private RadioButton literature;


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
                FXMLLoader loader = new FXMLLoader(getClass().getResource("addfile.fxml"));
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
    private String check_gender() throws InvalidOption {
        String b="gender";
        if(male.isSelected()==true && female.isSelected()==true){
            b="gender";
            InvalidGender.setText("Only one option can be selected");
            throw new InvalidOption("Only one gender can be selected");

        }
       else if(male.isSelected()==false && female.isSelected()==false){
           b="gender";
            InvalidGender.setText("One option needs to be selected");
            throw new InvalidOption("At least one gender has to  be selected");

        }
       else if(male.isSelected() && female.isSelected()==false)
           b="male";
       else
           b="female";
       return b;
    }
    private String check_department(){
        String s="depart";
        if(art.isSelected()==false && literature.isSelected()==false && computers.isSelected()==false && science.isSelected()==false){
            InvalidDepartment.setText("Select one department at least");
        }
        else if(art.isSelected() && literature.isSelected() && computers.isSelected()==false && science.isSelected()==false)
            InvalidDepartment.setText("Select only one department");
        else if(art.isSelected() && literature.isSelected() && computers.isSelected() && science.isSelected()==false)
            InvalidDepartment.setText("Select only one department");
        else if(art.isSelected() && literature.isSelected() && computers.isSelected() && science.isSelected())
            InvalidDepartment.setText("Select only one department");
        else if(art.isSelected()==false && literature.isSelected() && computers.isSelected() && science.isSelected()==false)
            InvalidDepartment.setText("Select only one department");
        else if(art.isSelected()==false && literature.isSelected() && computers.isSelected() && science.isSelected())
            InvalidDepartment.setText("Select only one department");
        else if(art.isSelected()==false && literature.isSelected()==false && computers.isSelected() && science.isSelected())
            InvalidDepartment.setText("Select only one department");
        else if(art.isSelected())
            s="Art";
        else if(literature.isSelected())
            s="Literature";
        else if(science.isSelected())
            s="Science";
        else
            s="Computers";
        return s;




    }
    public void submit(ActionEvent event)
    {


        submitButton.setOnAction(e->{
            Gender gender=Gender.Female;
            String department="department";
            String name="name";
            String age="age";
            int age_int=0;
            int id=0;
            id=application.last_id();
            name=studentname.getText();
            int ok=1;
             if(name.length()>35) {
                 ok=0;
                 try {
                     InvalidAge.setText("The name introduced is not valid");
                     throw new InvalidName("Invalid age was introduced");
                 } catch (InvalidName ex) {
                     ex.printStackTrace();
                 }
             }

             String checker=name;
            String[] s=checker.split("");
            for(int i=0;i<s.length;i++)
                if(s[i].equals("0")||s[i].equals("1")||s[i].equals("2")||s[i].equals("3")||s[i].equals("4") || s[i].equals("5") || s[i].equals("6") || s[i].equals("7") || s[i].equals("8")||s[i].equals("9"))
                {
                    ok=0;
                    try {

                        InvalidName.setText("The name introduced is not valid");
                        throw new InvalidName("The name introduced is invalid");
                    } catch (com.example.project.InvalidName ex) {
                        throw new RuntimeException(ex);
                    }

                }

             age=studentage.getText();
             age_int= Integer.parseInt(age);
            if(age_int>100) {
                try {
                    InvalidAge.setText("The age indroduced is not valid");
                    throw new InvalidAge("This age is not valid");
                } catch (com.example.project.InvalidAge ex) {
                    ex.printStackTrace();
                }
            }

            String gender1="gender";
            try {
                gender1=check_gender();
            } catch (InvalidOption ex) {
                ex.printStackTrace();
            }
            Gender g=Gender.Male;
            if(gender1.equals("male"))
                g=Gender.Male;
            else if(gender1.equals("female"))
                g=Gender.Female;

            Department depart=new Department("da");
            department=check_department();
            if(department.equals("Art"))
            {
                List<String> subjects=new ArrayList<>();
                subjects.add("Graphic Design");
                subjects.add("3D Design");
                subjects.add("Drawing");
                subjects.add("Painting");
                Department a=new Art(subjects);
                depart=a;
            }
            if(department.equals("Computers")){
                List<String> subjects=new ArrayList<>();
                subjects.add("Algorithms");
                subjects.add("Operating Systems");
                subjects.add("Programming");
                subjects.add("Computer Architecture");
                Department computer=new Computer(subjects);
                depart=computer;
            }
            if(department.equals("Science")){
            List<String> subjects=new ArrayList<>();
            subjects.add("Biology");
            subjects.add("Geography");
            subjects.add("Physics");
            subjects.add("Chemistry");
            Department science=new Science(subjects);
            depart=science;
            }
            if(department.equals("Literature")){
                List<String> subjects=new ArrayList<>();
                subjects.add("How to read a book");
                subjects.add("The beautiful part of reading");
                subjects.add("The correct way to write a book");
                Department literature=new Literature(subjects);
                depart=literature;
            }
                if(ok == 0 || age_int > 100 || department.equals("depart") || gender1.equals("gender")){}

                else {
                    database = application.recover_map();
                    Person student = new Person(name, age_int, gender, id);
                    boolean bool = application.check_database1(student, database);
                    if (!bool) {
                        student.setId(id + 1);
                        database.put(student, depart);
                        application.backup1(database);
                        operation.setText("Student was successfully added");
                    } else
                        operation.setText("Student is already in the database");
                }

        });
    }
}
