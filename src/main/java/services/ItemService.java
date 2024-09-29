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
                Item item = new Item(
                        resultSet.getInt("id"),
                        resultSet.getString("name"),
                        resultSet.getFloat("price"));
                catalogueOfItems.add(item);
            }
            resultSet.close();
        } catch(SQLException e){
            e.printStackTrace();
        }
        return catalogueOfItems;
    }
    public Item getItemByID(int id){
        DataBaseService dataBaseService = new DataBaseService();
        String request = "SELECT * FROM items WHERE id='"+id+"'";
        ResultSet resultSet = dataBaseService.select(request);
        Item item = new Item();
        try{
            resultSet.last();
            item.setId(resultSet.getInt("id"));
            item.setName(resultSet.getString("name"));
            item.setPrice(resultSet.getFloat("price"));
            resultSet.close();
        }catch(SQLException e){
            e.printStackTrace();
        }
        return item;
    }
    public Integer getIDOfLastItem(){
        int lastID = 0;
        DataBaseService dataBaseService = new DataBaseService();
        String request = "SELECT * FROM items";
        ResultSet resultSet = dataBaseService.select(request);
        try {
            resultSet.last();
            lastID = resultSet.getInt("id");
            resultSet.close();
        } catch(SQLException e){
            e.printStackTrace();
        }
        return lastID;
    }
}
