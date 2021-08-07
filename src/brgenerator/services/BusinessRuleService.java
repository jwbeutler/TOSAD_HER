package brgenerator.services;

import brgenerator.model.AttributeRange;
import brgenerator.model.BusinessRule;
import brgenerator.model.Column;
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
    public List<String> getAllRuleTypes(){
        return businessRuleDAO.getRuleTypes();
    }


    public BusinessRule createARBusinessRule(String name,int columnid, int ruletypeid){
        ColumnService columnService = ServiceProvider.getColumnService();
        AttributeRangeService attributeRangeService = ServiceProvider.getAttributeRangeService();
        for(Column c : columnService.findAll()){
            if(c.getId() == columnid){
                for(AttributeRange ar : attributeRangeService.findAll()){
                    if(ar.getId() == ruletypeid){
                        BusinessRule br = new BusinessRule(name,"AttributeRange");
                        businessRuleDAO.create(br,columnid,ruletypeid);
                        return br;
                    }
                    return null;
                }
            }
        }
        return null;
    }
    public List<BusinessRule> findStartRulesById(int ruletypeid, int columnid){
        ColumnService columnService = ServiceProvider.getColumnService();
        AttributeRangeService attributeRangeService = ServiceProvider.getAttributeRangeService();
        for(Column c : columnService.findAll()){
            if(c.getId() == columnid){
                for(AttributeRange ar : attributeRangeService.findAll()){
                    if(ar.getId() == ruletypeid){
                        return businessRuleDAO.findStartRuleById(ruletypeid,columnid);
                    }
                    return null;
                }
            }
        }
        return null;
    }
    public List<BusinessRule> findStartSchermRules(){
        return businessRuleDAO.findStartSchermRules();
    }

    public BusinessRule findById(int id){
        return businessRuleDAO.findById(id);
    }




}
