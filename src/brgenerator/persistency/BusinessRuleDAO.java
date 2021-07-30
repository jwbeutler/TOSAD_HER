package brgenerator.persistency;

import brgenerator.model.AttributeRange;
import brgenerator.model.BusinessRule;
import brgenerator.model.Column;
import brgenerator.model.Table;

import java.util.List;

public interface BusinessRuleDAO {
    public List<BusinessRule> findAll();
    public boolean create(BusinessRule businessRule,int columnid,int ruletypeid);

}
