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

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class LoginController {
    @FXML
    private Button login;
    @FXML
    private Button reset;
    @FXML
    private TextField username;
    @FXML
    private TextField password;
    @FXML
    private Label label;
    @FXML
    private void reset(ActionEvent event) {
        reset.setOnAction(e -> {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("login.fxml"));
                Parent root = loader.load();
                Scene scene = new Scene(root);
                Stage stage = (Stage) reset.getScene().getWindow();
                stage.setScene(scene);
                stage.show();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        });
    }
    public void submit(ActionEvent event) throws SQLException, IOException {
        login.setOnAction(e -> {
            int ok = 1;
            String name = username.getText();
            String pass = password.getText();
            DatabaseConnectionlogin connect = new DatabaseConnectionlogin();
            Connection connectdb = connect.getConnection();
            Statement statement = null;
            String verify = "SELECT count(1) FROM login WHERE name='" + name + "' and password='" + pass + "'";
            try {
                statement = connectdb.createStatement();
                ResultSet queryResult = statement.executeQuery(verify);
                while (queryResult.next()) {
                    if (queryResult.getInt(1) == 1) {
                        ok = 0;

                    }
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }

            if (ok == 1)
                label.setText("Invalid credentials for login");
            else {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("hello-view.fxml"));
                Parent root = null;
                try {
                    root = loader.load();
                    Scene scene = new Scene(root);
                    Stage stage = (Stage) login.getScene().getWindow();
                    stage.setScene(scene);
                    stage.show();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }

            }


        });
    }


    }
