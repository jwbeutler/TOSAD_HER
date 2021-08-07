package brgenerator.persistency;

import brgenerator.model.AttributeRange;
import brgenerator.model.BusinessRule;
import brgenerator.model.Column;
import brgenerator.model.Table;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BusinessRuleDAOImpl extends pgToolDao implements BusinessRuleDAO{

    public List<BusinessRule> selectBusinessRule(String query) {
        List<BusinessRule> results = new ArrayList<BusinessRule>();
        try(Connection con = super.getConnection()){
            PreparedStatement pstmt = con.prepareStatement(query);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()){
//                int id =rs.getInt("id");
                String name = rs.getString("name");
                BusinessRule newBusinessRule = new BusinessRule(name);
                results.add(newBusinessRule);
            }
        }catch (SQLException sqle){
            sqle.printStackTrace();
        }
        return results;
    }

    @Override
    public List<BusinessRule> findStartRuleById(int brtypeid, int tcid) {
        List<BusinessRule> results = new ArrayList<BusinessRule>();
        try(Connection conn = super.getConnection()){
            Statement pstmt = conn.createStatement();
            ResultSet resultSet = pstmt.executeQuery(
                    "SELECT br.id,br.name,brt.ruletype,brt.operator,tc.name as attribute " +
                            "FROM BUSINESSRULE br, BUSINESSRULETYPE brt, targetcolumn tc " +
                            "where brt.id = '"+brtypeid+"' and tc.id = '"+tcid+"';");

            while (resultSet.next()){
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                String type = resultSet.getString("type");
                String operator = resultSet.getString("operator");
                String attribute = resultSet.getString("attribute");
                BusinessRule br = new BusinessRule(id,name,type,operator,attribute);
                results.add(br);
            }
        }catch (SQLException sqle){
            sqle.printStackTrace();
        }
        return results;
    }

    @Override
    public List<BusinessRule> findAll() {
        return selectBusinessRule(
                "SELECT ID,NAME FROM BUSINESSRULE;"
        );
    }

    @Override
    public List<BusinessRule> findStartSchermRules() {
        List<BusinessRule> results = new ArrayList<BusinessRule>();
        try(Connection conn = super.getConnection()){
            Statement pstmt = conn.createStatement();
            ResultSet resultSet = pstmt.executeQuery(
                    "SELECT br.id,br.name,brt.ruletype,brt.operator,tc.name as attribute FROM BUSINESSRULE br, BUSINESSRULETYPE brt, targetcolumn tc;"
            );
            while (resultSet.next()){
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                String ruletype = resultSet.getString("ruletype");
                String operator = resultSet.getString("operator");
                String attribute = resultSet.getString("attribute");
                BusinessRule br = new BusinessRule(id,name,ruletype,operator,attribute);
                results.add(br);
            }
        }catch (SQLException sqle){
            sqle.printStackTrace();
        }
        return results;
    }

    @Override
    public boolean create(BusinessRule businessRule,int columnid,int ruletypeid) {
        try(Connection connection = super.getConnection()){
            String query = "INSERT INTO BUSINESSRULE(ID,NAME,TARGETCOLUMN_ID,BUSINESSRULETYPE_ID)VALUES(?,?,?,?)";
            PreparedStatement pstmt = connection.prepareStatement(query);
            pstmt.setInt(1,businessRule.getId());
            pstmt.setString(2,businessRule.getName());
            pstmt.setInt(3,columnid);
            pstmt.setInt(4,ruletypeid);
            pstmt.executeUpdate();
        }catch (SQLException throwables){
            throwables.printStackTrace();
        }
        return true;


    }

    @Override
    public List<String> getRuleTypes() {
        List<String> ruleTypes = new ArrayList<String>();

        try(Connection connection = super.getConnection()){
            Statement pstmt = connection.createStatement();
            ResultSet resultSet = pstmt.executeQuery(
                    "SELECT DISTINCT RULETYPE FROM BUSINESSRULETYPE;"
            );
            while (resultSet.next()){
                String ruletype = resultSet.getString("ruletype");
                ruleTypes.add(ruletype);
            }
        }catch (SQLException sqle){
            sqle.printStackTrace();;
        }
        return ruleTypes;
    }

    @Override
    public BusinessRule findById(int id) {
        BusinessRule businessRule = null;

        try(Connection connection = super.getConnection()){
            Statement pstmt = connection.createStatement();
            ResultSet resultset = pstmt.executeQuery(
                    "SELECT id, name, targetcolumn_id, businessruletype_id from businessrule where id = '"
                            + id + "';");

            while (resultset.next()) {
                businessRule = new BusinessRule(resultset.getInt("id"),
                        resultset.getString("name"),
                        resultset.getInt("targetcolumn_id"),
                        resultset.getInt("businessruletype_id"));
            }
            resultset.close();
            pstmt.close();
        } catch (Exception e) {
            System.out.println(e);
        }
        return businessRule;
    }
}
