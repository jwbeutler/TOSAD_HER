package brgenerator.persistency;

import brgenerator.model.AttributeRange;
import brgenerator.model.BusinessRule;
import brgenerator.model.Column;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AttributeRangeDAOImpl extends pgToolDao implements AttributeRangeDAO {
    private Connection conn;


    //Aanmaken nieuwe Rule die nieuwe ID teruggeeft
    @Override
    public int create(AttributeRange rule) throws SQLException   {
        conn = super.getConnection();
        int x = 0;
        Connection conn = super.getConnection();
        try {
            AttributeRange atrng = rule;
            atrng.toString();
            String range = "insert into BUSINESSRULETYPE(ruletype,operator,minval,maxval)" +
                    "Values( '" + atrng.getType() + "','" + atrng.getOperator() + "'," +atrng.getMinvalue() + "," + atrng.getMaxvalue() + ") RETURNING id;";
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(range);
            while (rs.next()) {
                x += rs.getInt("id");
            }
            return x;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return x;
    }

    @Override
    public List<AttributeRange> findAll() {
        List<AttributeRange> results = new ArrayList<AttributeRange>();
        String rule = "AttributeRange";
        try (Connection conn = super.getConnection()) {
            Statement pstmt = conn.createStatement();
            ResultSet resultSet = pstmt.executeQuery(
                    "SELECT ID,RULETYPE,OPERATOR,MINVAL,MAXVAL FROM BUSINESSRULETYPE WHERE RULETYPE = '" + rule + "';");
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String ruletype = resultSet.getString("ruletype");
                String operator = resultSet.getString("operator");
                int minval = resultSet.getInt("minval");
                int maxval = resultSet.getInt("maxval");
                AttributeRange newAR = new AttributeRange(id, ruletype, operator, minval, maxval);
                results.add(newAR);
            }
        } catch (SQLException sqle) {
            sqle.printStackTrace();
        }
        return results;
    }

    @Override
    public AttributeRange findById(int id) {
        AttributeRange attributeRange = null;

        try (Connection connection = super.getConnection()) {
            Statement pstmt = connection.createStatement();
            ResultSet resultset = pstmt.executeQuery(
                    "SELECT id, ruletype, operator, minval, maxval from businessruletype where id = '"
                            + id + "';");
            while (resultset.next()) {
                attributeRange = new AttributeRange(
                        resultset.getInt("id"),
                        resultset.getString("ruletype"),
                        resultset.getString("operator"),
                        resultset.getInt("minval"),
                        resultset.getInt("maxval"));
            }
            resultset.close();
            pstmt.close();
        } catch (Exception e) {
            System.out.println(e);
        }
        return attributeRange;
    }
}
