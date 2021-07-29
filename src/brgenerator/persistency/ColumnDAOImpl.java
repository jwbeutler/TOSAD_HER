package brgenerator.persistency;

import brgenerator.model.Column;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ColumnDAOImpl extends pgToolDao implements ColumnDAO {
    @Override
    public List<Column> findColumnsByTable(int tableid) {
        List<Column> results = new ArrayList<Column>();
        try(Connection conn = super.getConnection()){
            Statement pstmt = conn.createStatement();
            ResultSet resultSet = pstmt.executeQuery(
                    "SELECT ID,NAME,TYPE FROM TARGETCOLUMN WHERE TABLE_ID = '"+tableid+"';"
            );
            while (resultSet.next()){
                //int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                String type =resultSet.getString("type");
                Column newColumn = new Column(name,type);
                results.add(newColumn);
            }
        }catch (SQLException sqle){
            sqle.printStackTrace();;
        }
        return results;
    }

    @Override
    public List<Column> findAll() {
        List<Column> results = new ArrayList<Column>();
        try(Connection conn = super.getConnection()){
            Statement pstmt = conn.createStatement();
            ResultSet resultSet = pstmt.executeQuery(
                    "SELECT ID,NAME,TYPE FROM TARGETCOLUMN;"
            );
            while (resultSet.next()){
                //int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                String type = resultSet.getString("type");
                Column newColumn = new Column(name,type);
                results.add(newColumn);
            }
        }catch (SQLException sqle){
            sqle.printStackTrace();
        }
        return results;
    }

    @Override
    public Column findById(int id) {
        Column column = null;
        try(Connection connection = super.getConnection()){
            Statement pstmt = connection.createStatement();
            ResultSet resultSet = pstmt.executeQuery(
                    "SELECT ID,NAME,TYPE FROM TARGETCOLUMN WHERE ID ='"
                            + id + "';"
            );
            while (resultSet.next()){
                column = new Column(
//                        resultSet.getInt("id"),
                        resultSet.getString("name"),resultSet.getString("type"));
            }
            resultSet.close();
            pstmt.close();
        }catch (Exception e){
            System.out.println(e);
        }
        return column;
    }

    @Override
    public boolean create(Column column, int tableid) {
        try(Connection connection = super.getConnection()){
            String query = "INSERT INTO TARGETCOLUMN(ID,NAME,TYPE,TABLE_ID)VALUES(?,?,?,?)";
            PreparedStatement pstmt = connection.prepareStatement(query);
            pstmt.setInt(1,column.getId());
            pstmt.setString(2,column.getName());
            pstmt.setString(3,column.getType());
            pstmt.setInt(4,tableid);
            pstmt.executeUpdate();
        }catch (SQLException throwables){
            throwables.printStackTrace();
        }
        return true;
    }
}
