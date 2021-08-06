package brgenerator.persistency;

import brgenerator.model.Table;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TableDAOImpl extends pgToolDao implements TableDAO {
    @Override
    public boolean create(Table table) {
        try(Connection connection = super.getConnection()){
            String query = "INSERT INTO TARGETTABLE(ID,NAME)VALUES(?,?)";
            PreparedStatement pstmt = connection.prepareStatement(query);
            pstmt.setInt(1,table.getId());
            pstmt.setString(2,table.getName());
            pstmt.executeUpdate();
        }catch (SQLException throwables){
            throwables.printStackTrace();
        }
        return true;

    }

    @Override
    public List<Table> findAll() {
        List<Table> results = new ArrayList<Table>();
        try(Connection conn = super.getConnection()){
            Statement pstmt = conn.createStatement();
            ResultSet resultSet = pstmt.executeQuery(
                    "SELECT ID,NAME FROM TARGETTABLE"
            );
            while (resultSet.next()){
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                Table newTable = new Table(id,name);
                results.add(newTable);
            }
        }catch (SQLException sqle){
            sqle.printStackTrace();
        }
        return results;
    }
}
