package brgenerator.persistency;

import brgenerator.model.AttributeRange;
import brgenerator.model.BusinessRule;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

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
}
