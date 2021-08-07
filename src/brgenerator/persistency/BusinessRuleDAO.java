package brgenerator.persistency;

import brgenerator.model.AttributeRange;
import brgenerator.model.BusinessRule;
import brgenerator.model.Column;
import brgenerator.model.Table;

import java.util.List;

public interface BusinessRuleDAO {
    public List<BusinessRule> findStartRuleById(int brtypeid, int tcid);
    public List<BusinessRule> findAll();
    public List<BusinessRule> findStartSchermRules();
    public boolean create(BusinessRule businessRule,int columnid,int ruletypeid);
    public List<String> getRuleTypes();
    public BusinessRule findById(int id);

}
