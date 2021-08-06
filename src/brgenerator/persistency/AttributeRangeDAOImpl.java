package brgenerator.persistency;

import brgenerator.model.AttributeRange;
import brgenerator.model.BusinessRule;
import brgenerator.model.Column;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AttributeRangeDAOImpl extends pgToolDao implements AttributeRangeDAO {

    @Override
    public boolean create(AttributeRange attributeRange) {
        try(Connection connection = super.getConnection()){
            String query = "INSERT INTO BUSINESSRULETYPE(id,ruletype,operator,minval,maxval)values(?,?,?,?,?)";
            PreparedStatement pstmt = connection.prepareStatement(query);
            pstmt.setInt(1,attributeRange.getId());
            pstmt.setString(2,attributeRange.getType());
            pstmt.setString(3,attributeRange.getOperator());
            pstmt.setInt(4,attributeRange.getMinvalue());
            pstmt.setInt(5,attributeRange.getMaxvalue());
            pstmt.executeUpdate();
        }catch (SQLException throwables){
            throwables.printStackTrace();
        }
        return true;
    }

    @Override
    public List<AttributeRange> findAll() {
            List<AttributeRange> results = new ArrayList<AttributeRange>();
            String rule = "AttributeRange";
            try(Connection conn = super.getConnection()){
                Statement pstmt = conn.createStatement();
                ResultSet resultSet = pstmt.executeQuery(
                        "SELECT ID,RULETYPE,OPERATOR,MINVAL,MAXVAL FROM BUSINESSRULETYPE WHERE RULETYPE = '"+rule+"';");
                while (resultSet.next()){
                    int id = resultSet.getInt("id");
                    String ruletype = resultSet.getString("ruletype");
                    String operator = resultSet.getString("operator");
                    int minval = resultSet.getInt("minval");
                    int maxval = resultSet.getInt("maxval");
                    AttributeRange newAR = new AttributeRange(id,ruletype,operator,minval,maxval);
                    results.add(newAR);
                }
            }catch (SQLException sqle){
                sqle.printStackTrace();
            }
            return results;
        }
    }
