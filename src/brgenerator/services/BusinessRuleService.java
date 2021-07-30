package brgenerator.services;

import brgenerator.model.BusinessRule;
import brgenerator.model.Table;
import brgenerator.persistency.BusinessRuleDAO;
import brgenerator.persistency.BusinessRuleDAOImpl;

import java.util.List;

public class BusinessRuleService {
    BusinessRuleDAO businessRuleDAO = new BusinessRuleDAOImpl();
    private TargetDBSerivce targetDBSerivce = ServiceProvider.getTargetDBService();

    public List<BusinessRule> findAll(){
        return businessRuleDAO.findAll();
    }


}
