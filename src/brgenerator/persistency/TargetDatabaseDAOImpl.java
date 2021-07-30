package brgenerator.persistency;

import brgenerator.model.Column;
import brgenerator.model.Table;

import javax.xml.transform.Result;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TargetDatabaseDAOImpl extends pgTargetDao implements TargetDatabaseDAO {

    @Override
    public List<Table> findAll() {
        List<Table> results = new ArrayList<Table>();
        try(Connection conn = super.getConnection()){
            Statement pstmt = conn.createStatement();
            ResultSet resultSet = pstmt.executeQuery(
                    "SELECT table_name FROM information_schema.tables WHERE table_schema='public' AND table_type='BASE TABLE';"
            );
            while (resultSet.next()){
                String name = resultSet.getString("table_name");
                Table newTable = new Table(name);
                results.add(newTable);
            }
        }catch (SQLException sqle){
            sqle.printStackTrace();
        }
        return results;
    }

    @Override
    public Table findTableByName(String name) {
        Table table = null;

        try(Connection connection = super.getConnection()){
            Statement pstmt = connection.createStatement();
            ResultSet resultSet = pstmt.executeQuery(
                    "SELECT table_name FROM information_schema.tables WHERE table_schema='public' AND table_type='BASE TABLE' AND TABLE_NAME = '" + name + "';");

            while (resultSet.next()){
                table = new Table(resultSet.getString("table_name"));
            }
            resultSet.close();
            pstmt.close();
        }catch (Exception e){
            System.out.println(e);
        }
        return table;
    }

    @Override
    public List<Column> findColumnsByTable(String tablename) {
        List<Column> columns = new ArrayList<Column>();

        try(Connection connection = super.getConnection()){
            Statement pstmt = connection.createStatement();
            ResultSet resultset = pstmt.executeQuery(
                    "SELECT column_name, DATA_TYPE FROM INFORMATION_SCHEMA.COLUMNS WHERE TABLE_NAME ='"+tablename+"';");

            while (resultset.next()){
                String columnname = resultset.getString("column_name");
                String datatype = resultset.getString("data_type");
                Column newColumn = new Column(columnname,datatype);
                columns.add(newColumn);
            }
        }catch (SQLException sqle){
            sqle.printStackTrace();;
        }
        return columns;

    }

    @Override
    public Column findColumnByName(String name) {
        return null;
    }

}
