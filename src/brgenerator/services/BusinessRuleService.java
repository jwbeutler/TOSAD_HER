package brgenerator.services;

import brgenerator.model.AttributeRange;
import brgenerator.model.BusinessRule;
import brgenerator.model.Table;
import brgenerator.persistency.BusinessRuleDAO;
import brgenerator.persistency.BusinessRuleDAOImpl;

import java.util.List;

public class BusinessRuleService {
    BusinessRuleDAO businessRuleDAO = new BusinessRuleDAOImpl();
    private TargetDBSerivce targetDBSerivce = ServiceProvider.getTargetDBService();
    private AttributeRangeService attributeRangeService = ServiceProvider.getAttributeRangeService();

    public List<BusinessRule> findAll(){
        return businessRuleDAO.findAll();
    }
    public List<String> getAllRuleTypes(){
        return businessRuleDAO.getRuleTypes();
    }



}
