package Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SearchItems {

    @FXML
    private Label lbl_Description;

    @FXML
    private TextField lbl_code;

    @FXML
    private Label lbl_qtyhands;

    @FXML
    private Label lbl_unitPrice;

    @FXML
    void btn_searchCustomer_Action(ActionEvent event) throws SQLException {
        Connection connection= DBconnection.getInstance().getConnection();

        PreparedStatement pst =connection.prepareStatement("SELECT * FROM item WHERE code = ?");
        pst.setString(1,lbl_code.getText().trim());

        ResultSet resultSet=pst.executeQuery();

        if (resultSet.next()){
            lbl_Description.setText(resultSet.getString("description").trim());
            lbl_unitPrice.setText(String.valueOf(resultSet.getDouble("unitPrice")));
            lbl_qtyhands.setText(String.valueOf(resultSet.getInt("qtyOnHand")));

        }else {
            new Alert(Alert.AlertType.INFORMATION,"Not Success full").show();
        }

        resultSet.close();
        pst.close();


    }

}
