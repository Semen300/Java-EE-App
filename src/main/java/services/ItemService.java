package services;

import structure.Item;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ItemService {

    public List<Item> getItems(){
        List<Item> catalogueOfItems = new ArrayList<>();
        DataBaseService dataBaseService = new DataBaseService();
        String request = "SELECT * FROM items";
        ResultSet resultSet = dataBaseService.select(request);
        try {
            while(resultSet.next()){
                Item item = new Item(resultSet.getInt("id"),
                        resultSet.getString("name"));
                catalogueOfItems.add(item);
            }
        } catch(SQLException e){
            e.printStackTrace();
        }
        return catalogueOfItems;
    }
    public String getItemNameByID(int id){
        String itemName = "";
        DataBaseService dataBaseService = new DataBaseService();
        String request = "SELECT * FROM items WHERE id='"+id+"'";
        ResultSet resultSet = dataBaseService.select(request);
        try{
            resultSet.last();
            itemName = resultSet.getString("name");
        }catch(SQLException e){
            e.printStackTrace();
        }
        return itemName;
    }
    public Integer getIDOfLastItem(){
        int lastID = 0;
        DataBaseService dataBaseService = new DataBaseService();
        String request = "SELECT * FROM items";
        ResultSet resultSet = dataBaseService.select(request);
        try {
            resultSet.last();
            lastID = resultSet.getInt("id");
        } catch(SQLException e){
            e.printStackTrace();
        }
        return lastID;
    }
}
