//package org.example.demo1;
//
//import javafx.application.Application;
//import javafx.fxml.FXMLLoader;
//import javafx.scene.Parent;
//import javafx.scene.Scene;
//import javafx.stage.Stage;
//
//import java.io.IOException;
//
//public class Display extends Application {
//    @Override
//    public void start(Stage stage) {
//        try {
//            FXMLLoader fxmlLoader = new FXMLLoader(Display.class.getResource("emplyee-view.fxml"));
//            Parent employeeRoot = fxmlLoader.load(); // Tutaj używamy metody load() na fxmlLoader
//            Scene scene = new Scene(employeeRoot, 800, 600);
//            stage.setTitle("Employee Management System");
//            stage.setScene(scene);
//            stage.show();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
//
//    public static void main(String[] args) {
//        launch();
//    }
//}
