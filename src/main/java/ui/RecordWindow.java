package ui;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.PasswordRecord;

public class RecordWindow {

    public void show() {

        Stage stage = new Stage();

        TableView<PasswordRecord> table
                = new TableView<>();

        TableColumn<PasswordRecord, Integer> idColumn
                = new TableColumn<>("ID");

        idColumn.setCellValueFactory(
                new PropertyValueFactory<>("id")
        );
        idColumn.setPrefWidth(80);

        TableColumn<PasswordRecord, Integer> scoreColumn
                = new TableColumn<>("Score");

        scoreColumn.setCellValueFactory(
                new PropertyValueFactory<>("score")
        );
        scoreColumn.setPrefWidth(100);

        TableColumn<PasswordRecord, String> strengthColumn
                = new TableColumn<>("Strength");

        strengthColumn.setCellValueFactory(
                new PropertyValueFactory<>("strength")
        );
        strengthColumn.setPrefWidth(150);
        TableColumn<PasswordRecord, Double> entropyColumn
                = new TableColumn<>("Entropy");

        entropyColumn.setCellValueFactory(
                new PropertyValueFactory<>("entropy")
        );
        entropyColumn.setPrefWidth(150);

        table.getColumns().addAll(
                idColumn,
                scoreColumn,
                strengthColumn,
                entropyColumn
        );

        ObservableList<PasswordRecord> data
                = FXCollections.observableArrayList();

        try {

            Connection conn
                    = DriverManager.getConnection(
                            "jdbc:mysql://localhost:3307/secure_pass",
                            "root",
                            "vedant2802"
                    );

            Statement stmt
                    = conn.createStatement();

            ResultSet rs
                    = stmt.executeQuery(
                            "SELECT * FROM password_records"
                    );

            while (rs.next()) {

                data.add(
                        new PasswordRecord(
                                rs.getInt("id"),
                                rs.getInt("score"),
                                rs.getString("strength"),
                                rs.getDouble("entropy"),
                                rs.getString("password_hash")
                        )
                );
            }

            conn.close();

        } catch (Exception e) {

            e.printStackTrace();
        }

        table.setItems(data);
        VBox root
                = new VBox(table);

        Scene scene
                = new Scene(root, 700, 400);

        stage.setTitle(
                "Password Records"
        );

        stage.setScene(scene);

        stage.show();
    }
}
