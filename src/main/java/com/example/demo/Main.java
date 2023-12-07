package com.example.demo;
import java.util.List;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TableView;
import javafx.scene.control.TableColumn;


public class Main extends Application {

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Prisijungimas");

        GridPane grid = new GridPane();
        grid.setPadding(new Insets(20, 20, 20, 20));
        grid.setVgap(8);
        grid.setHgap(10);

        Label usernameLabel = new Label("Vartotojo vardas:");
        GridPane.setConstraints(usernameLabel, 0, 0);

        TextField usernameInput = new TextField();
        usernameInput.setPromptText("Įveskite vartotojo vardą");
        GridPane.setConstraints(usernameInput, 1, 0);

        Label passwordLabel = new Label("Slaptažodis:");
        GridPane.setConstraints(passwordLabel, 0, 1);

        PasswordField passwordInput = new PasswordField();
        passwordInput.setPromptText("Įveskite slaptažodį");
        GridPane.setConstraints(passwordInput, 1, 1);

        Button loginButton = new Button("Prisijungti");
        GridPane.setConstraints(loginButton, 1, 2);

        loginButton.setOnAction(e -> {
            String vartotojoVardas = usernameInput.getText();
            String slaptazodis = passwordInput.getText();

            if (Prisijungimas.patikrintiPrisijungima(vartotojoVardas, slaptazodis)) {
                System.out.println("Prisijungimas sėkmingas!");
                TableView<Pazymiai> tableViewPazymiai;
                tableViewPazymiai = new TableView<>();

                // Sukuriame Alert langą su pranešimu apie sėkmingą prisijungimą
                Alert alert = new Alert(AlertType.INFORMATION);
                alert.setTitle("Sėkmingas prisijungimas");
                alert.setHeaderText(null);
                alert.setContentText("Sveiki atvykę! Jūs sėkmingai prisijungėte.");

                // Parodome Alert langą
                alert.showAndWait();

                String role = Autentifikavimas.gautiVartotojoRole(vartotojoVardas, slaptazodis);
                System.out.println("Gauta vartotojo rolė: " + role);

                if (role.equals("studentas")) {
                    // Atidaro naują langą su pažymiais
                    Stage studentasStage = new Stage();
                    studentasStage.setTitle("Pazymiai");

                    List<Pazymiai> pazymiai = GautiPazymius.getPazymiaiByVartotojoVardas(vartotojoVardas);

                    ObservableList<Pazymiai> observablePazymiai = FXCollections.observableArrayList();

                    observablePazymiai.addAll(pazymiai);

                    tableViewPazymiai.setItems(observablePazymiai);

                    // Sukuria lentelę, kurioje bus rodomi pažymiai
                    TableView<Pazymiai> pazymiaiLentele = new TableView<>();
                    pazymiaiLentele.setItems((ObservableList<Pazymiai>) pazymiai);

                    TableColumn<Pazymiai, String> dalykasStulpelis = new TableColumn<>("Dalykas");
                    TableColumn<Pazymiai, Double> pazymysStulpelis = new TableColumn<>("Pazymis");

                    pazymiaiLentele.getColumns().addAll(dalykasStulpelis, pazymysStulpelis);

                    dalykasStulpelis.setCellValueFactory(new PropertyValueFactory<>("dalykas"));
                    pazymysStulpelis.setCellValueFactory(new PropertyValueFactory<>("pazymys"));

                    // Pridėti papildomą stulptelį, kuriame bus rodomi pažymių vidurkis
                    TableColumn<Pazymiai, Double> vidurkisStulpelis = new TableColumn<>("Vidurkis");
                    pazymiaiLentele.getColumns().add(vidurkisStulpelis);

                    // Apskaičiuo vidurkį
                    Double vidurkis = 0.0;
                    for (Pazymiai pazymys : pazymiai) {
                        vidurkis += pazymys.getPazymys();
                    }
                    vidurkis = vidurkis / pazymiai.size();
                    // Nustatykite vidurkio reikšmę
                    vidurkisStulpelis.setCellValueFactory(new PropertyValueFactory<>("vidurkis"));

                    studentasStage.setScene(new Scene(pazymiaiLentele, 600, 400));
                    studentasStage.show();
                }
            } else {
                System.out.println("Prisijungimas nepavyko!");
                // Sukuriame Alert langą su pranešimu apie prisijungimo nepavykimą
                Alert alert = new Alert(AlertType.ERROR);
                alert.setTitle("Klaida");
                alert.setHeaderText("Prisijungimas nepavyko");
                alert.setContentText("Patikrinkite vartotojo vardą ir slaptažodį.");

                // Parodome Alert langą
                alert.showAndWait();
            }
        });

        grid.getChildren().addAll(usernameLabel, usernameInput, passwordLabel, passwordInput, loginButton);

        Scene scene = new Scene(grid, 300, 200);
        primaryStage.setScene(scene);
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}