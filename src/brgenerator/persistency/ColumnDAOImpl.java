package brgenerator.persistency;

import brgenerator.model.Column;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ColumnDAOImpl extends pgToolDao implements ColumnDAO {


    @Override
    public List<Column> findAll() {
        List<Column> results = new ArrayList<Column>();
        try(Connection conn = super.getConnection()){
            Statement pstmt = conn.createStatement();
            ResultSet resultSet = pstmt.executeQuery(
                    "SELECT ID,NAME,TYPE FROM TARGETCOLUMN;"
            );
            while (resultSet.next()){
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                String type = resultSet.getString("type");
                Column newColumn = new Column(id,name,type);
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
                    "SELECT ID,NAME,TYPE,table_id FROM TARGETCOLUMN WHERE ID ='"
                            + id + "';"
            );
            while (resultSet.next()){
                column = new Column(
                        resultSet.getInt("id"),
                        resultSet.getString("name"),resultSet.getString("type"),resultSet.getInt("table_id"));
            }
            resultSet.close();
            pstmt.close();
        }catch (Exception e){
            System.out.println(e);
        }
        return column;
    }

    //Aanmaken nieuwe Column die nieuwe ID teruggeeft

    @Override
    public int create(Column column, int tableid) throws SQLException {
        int x = 0;
        Connection conn = super.getConnection();
        try{
            Column clmn = column;
            clmn.toString();
            String query = "INSERT INTO TARGETCOLUMN(NAME,TYPE,TABLE_ID)"+"VALUES('"+clmn.getName()+"','"+clmn.getType()+"','"+tableid+"')RETURNING id;";
            //pstmt.setInt(1,column.getId());
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while(rs.next()){
                x += rs.getInt("id");
            }
            return x;
        }catch (SQLException throwables){
            throwables.printStackTrace();
        }
        return x;
    }

}
