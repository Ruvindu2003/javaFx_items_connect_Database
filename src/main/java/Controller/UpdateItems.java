package Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UpdateItems {

    @FXML
    private TextField lbl_Descripton;

    @FXML
    private TextField lbl_code;

    @FXML
    private TextField lbl_qtyonhands;

    @FXML
    private TextField lbl_uniteprice;

    @FXML
    void btn_update_items(ActionEvent event) throws RuntimeException {

        try (Connection connection = DBconnection.getInstance().getConnection()) {
            String SQL = "UPDATE item SET description = ?, unitPrice = ?, qtyOnHand = ? WHERE code = ?";
            try (PreparedStatement stm = connection.prepareStatement(SQL)) {
                // Setting the parameters for the prepared statement
                stm.setString(1, lbl_Descripton.getText());
                stm.setDouble(2, Double.parseDouble(lbl_uniteprice.getText()));
                stm.setInt(3, Integer.parseInt(lbl_qtyonhands.getText()));
                stm.setString(4, lbl_code.getText());

                // Executing the update and checking if it succeeded
                if (stm.executeUpdate() > 0) {
                    new Alert(Alert.AlertType.INFORMATION, "Item updated successfully").showAndWait();
                } else {
                    new Alert(Alert.AlertType.WARNING, "No item found with the provided code").showAndWait();
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "An error occurred while updating the item: " + e.getMessage()).showAndWait();
        }
    }

        public void btn_Search(ActionEvent actionEvent) throws SQLException {
        Connection connection= DBconnection.getInstance().getConnection();

        PreparedStatement pst =connection.prepareStatement("SELECT * FROM item WHERE code = ?");
        pst.setString(1,lbl_code.getText().trim());

        ResultSet resultSet=pst.executeQuery();

        if (resultSet.next()){
            lbl_Descripton.setText(resultSet.getString("description").trim());
            lbl_uniteprice.setText(String.valueOf(resultSet.getDouble("unitPrice")));
            lbl_qtyonhands.setText(String.valueOf(resultSet.getInt("qtyOnHand")));

        }else {
            new Alert(Alert.AlertType.INFORMATION,"Not Success full").show();
        }

        resultSet.close();
        pst.close();


    }


}

