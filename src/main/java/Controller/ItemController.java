package Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ItemController {

    @FXML
    private TextField txt_description;

    @FXML
    private TextField txt_id;

    @FXML
    private TextField txt_qtyonhand;

    @FXML
    private TextField txt_unitprice;

    @FXML
    void btn_Add_items_Action(ActionEvent event) {
        Connection connection= DBconnection.getInstance().getConnection();
        try {
            PreparedStatement pst=connection.prepareStatement("Insert into item values (?,?,?,?) ");

            pst.setString(1,txt_id.getText());
            pst.setString(2,txt_description.getText());
            pst.setDouble(3,Double.parseDouble(txt_unitprice.getText()));
            pst.setInt(4,Integer.parseInt(txt_qtyonhand.getText()));

            if (pst.executeUpdate()>0){

              new  Alert(Alert.AlertType.INFORMATION,"Item Add").show();
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @FXML
    void btn_Delete_items_Action(ActionEvent event) throws IOException {
        Stage stage=new Stage();
        stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("../View/Delete.fxml"))));
        stage.show();

    }

    @FXML
    void btn_serach_items_Action(ActionEvent event) throws IOException {

        Stage stage =new Stage();
        stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("../View/Search_Items.fxml"))));
        stage.show();
    }

    @FXML
    void btn_view_items_Action(ActionEvent event) throws IOException {
        Stage stage=new Stage();
        stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("../View/View_item.fxml"))));
        stage.show();


    }

    @FXML
    void update_Items_Action(ActionEvent event) throws IOException {
        Stage stage=new Stage();
        stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("../View/update.fxml"))));
        stage.show();

    }

}
