package Controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import modle.Items;

import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class ViewForm implements Initializable {

    @FXML
    private TableColumn<?, ?> colum_code;

    @FXML
    private TableColumn<?, ?> colum_description;

    @FXML
    private TableColumn<?, ?> colum_qtyHands;

    @FXML
    private TableColumn<?, ?> colum_uniteprice;

    @FXML
    private TableView<Items> tbl_items;
List<Items>items=new ArrayList<>();
    @FXML
    void btn_Relode_Action(ActionEvent event) {

        try {
            Connection  connection= DBconnection.getInstance().getConnection();
            Statement statement=connection.createStatement();
            ResultSet resultSet=statement.executeQuery("Select * from item");
            while (resultSet.next()){
                items.add(
                        new Items(
                                resultSet.getString(1),
                                resultSet.getString(2),
                                resultSet.getDouble(3),
                                resultSet.getInt(4)
                        )

                );


            }





        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        ObservableList<Items>itemsObservableList= FXCollections.observableArrayList();

        items.forEach(items1 ->  {
            itemsObservableList.add(items1);


        }
        );
        tbl_items.setItems(itemsObservableList);


    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
         colum_code.setCellValueFactory(new PropertyValueFactory<>("code"));
         colum_description.setCellValueFactory(new PropertyValueFactory<>("description"));
         colum_uniteprice.setCellValueFactory(new PropertyValueFactory<>("unitprice"));
         colum_qtyHands.setCellValueFactory(new PropertyValueFactory<>("qtyonHand"));

    }



}
