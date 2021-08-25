package brgenerator.persistency;

import brgenerator.model.Column;
import brgenerator.model.Table;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TableDAOImpl extends pgToolDao implements TableDAO {
    @Override
    public int create(Table table) throws SQLException {
        int x= 0;
        Connection conn = super.getConnection();
        try {
            Table tbl = table;
            tbl.toString();
            String query = "INSERT INTO TARGETTABLE(NAME)"+"VALUES('"+tbl.getName()+"') RETURNING id;";
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while(rs.next()){
                x+=rs.getInt("id");
            }
            return x;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return x;
    }


    @Override
    public List<Table> findAll() {
        List<Table> results = new ArrayList<Table>();
        try (Connection conn = super.getConnection()) {
            Statement pstmt = conn.createStatement();
            ResultSet resultSet = pstmt.executeQuery(
                    "SELECT ID,NAME FROM TARGETTABLE"
            );
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                Table newTable = new Table(id, name);
                results.add(newTable);
            }
        } catch (SQLException sqle) {
            sqle.printStackTrace();
        }
        return results;
    }

    @Override
    public Table findById(int id) {
        Table table = null;
        try (Connection connection = super.getConnection()) {
            Statement pstmt = connection.createStatement();
            ResultSet resultSet = pstmt.executeQuery(
                    "SELECT ID,NAME FROM TARGETTABLE WHERE ID ='"
                            + id + "';"
            );
            while (resultSet.next()) {
                table = new Table(
                        resultSet.getInt("id"),
                        resultSet.getString("name"));
            }
            resultSet.close();
            pstmt.close();
        } catch (Exception e) {
            System.out.println(e);
        }
        return table;
    }
}