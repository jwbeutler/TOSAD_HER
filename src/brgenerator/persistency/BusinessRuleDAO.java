package brgenerator.persistency;

import brgenerator.model.AttributeRange;
import brgenerator.model.BusinessRule;
import brgenerator.model.Column;
import brgenerator.model.Table;

import java.sql.SQLException;
import java.util.List;

public interface BusinessRuleDAO {
    public List<BusinessRule> findStartRuleById(int brtypeid, int tcid);
    public List<BusinessRule> findAll();
    public List<BusinessRule> findStartSchermRules();
    public int create(BusinessRule businessRule,int columnid,int ruletypeid) throws SQLException;
    public List<String> getRuleTypes();
    public BusinessRule findById(int id);

}
