package brgenerator.persistency;

import brgenerator.model.AttributeCompare;
import brgenerator.model.AttributeRange;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class AttributeCompareDAOImpl extends pgToolDao implements AttributeCompareDAO {


    //Aanmaken nieuwe Rule die nieuwe ID teruggeeft
    @Override
    public int create(AttributeCompare attributeCompare) throws SQLException {
        int x = 0;
        Connection conn = super.getConnection();
        try{
            AttributeCompare atrc = attributeCompare;
            atrc.toString();
            String compare = "INSERT INTO BUSINESSRULETYPE(ruletype,operator,litval)" +
                    "VALUES('" + atrc.getType() + "','" + atrc.getOperator() + "',"  + atrc.getLitvalue() + ") RETURNING id;";
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(compare);
            while(rs.next()){
                x += rs.getInt("id");
            }
            return x;
        } catch (SQLException throwables){
            throwables.printStackTrace();;
        }
        return x;
    }

    @Override
    public List<AttributeCompare> findAll() {
        List<AttributeCompare> results = new ArrayList<AttributeCompare>();
        String rule = "AttributeCompare";
        try(Connection conn = super.getConnection()){
            Statement pstmt = conn.createStatement();
            ResultSet resultSet = pstmt.executeQuery(
                    "SELECT ID,RULETYPE,OPERATOR,LITVAL FROM BUSINESSRULETYPE WHERE RULETYPE = '" + rule + "';");
            while(resultSet.next()){
                int id =resultSet.getInt("id");
                String ruletype = resultSet.getString("ruletype");
                String operator = resultSet.getString("operator");
                int litval = resultSet.getInt("litval");
                AttributeCompare newAC = new AttributeCompare(id,ruletype,operator,litval);
                results.add(newAC);
            }
        }catch (SQLException sqle){
            sqle.printStackTrace();;
        }
        return results;
    }

    @Override
    public AttributeCompare findByID(int id) {
        AttributeCompare attributeCompare = null;

        try(Connection connection = super.getConnection()){
            Statement pstmt = connection.createStatement();
            ResultSet resultSet = pstmt.executeQuery(
                    "SELECT id, ruletype, operator, litval from businessruletype where id = '"
                            + id + "';");
            while(resultSet.next()){
                attributeCompare = new AttributeCompare(
                        resultSet.getInt("id"),
                        resultSet.getString("ruletype"),
                        resultSet.getString("operator"),
                        resultSet.getInt("litval"));
            }
            resultSet.close();;
            pstmt.close();
        }catch (Exception e){
            System.out.println(e);
        }
        return attributeCompare;
    }
}
