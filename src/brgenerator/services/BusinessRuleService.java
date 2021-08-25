package brgenerator.services;

import brgenerator.model.*;
import brgenerator.persistency.BusinessRuleDAO;
import brgenerator.persistency.BusinessRuleDAOImpl;

import java.sql.SQLException;
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


    public BusinessRule createARBusinessRule(String name,int columnid, int ruletypeid) throws SQLException {
        ColumnService columnService = ServiceProvider.getColumnService();
        AttributeRangeService attributeRangeService = ServiceProvider.getAttributeRangeService();
        for(Column c : columnService.findAll()){
            if(c.getId() == columnid){
                //System.out.println("column id matcht");
                AttributeRange ar = attributeRangeService.findById(ruletypeid);
                System.out.println(ar.getId());
                System.out.println(ruletypeid);
                    if(ar.getId() == ruletypeid){
                        BusinessRule br = new BusinessRule(name,"AttributeRange");
                        int brid = businessRuleDAO.create(br,columnid,ruletypeid);
                        br.setId(brid);
                        return br;
                    }
                    return null;
                }
            }
        return null;
    }
    public BusinessRule createACBusinessRule(String name,int columnid, int ruletypeid) throws SQLException {
        ColumnService columnService = ServiceProvider.getColumnService();
        AttributeCompareService attributeCompareService = ServiceProvider.getAttributeCompareService();
        for(Column c : columnService.findAll()){
            if(c.getId() == columnid){
                //System.out.println("column id matcht");
                AttributeCompare ac = attributeCompareService.findById(ruletypeid);
                System.out.println(ac.getId());
                System.out.println(ruletypeid);
                if(ac.getId() == ruletypeid){
                    BusinessRule br = new BusinessRule(name,"AttributeCompare");
                    int brid = businessRuleDAO.create(br,columnid,ruletypeid);
                    br.setId(brid);
                    return br;
                }
                return null;
            }
        }
        return null;
    }


    public List<BusinessRule> findStartSchermRules(){
        List<BusinessRule>brStartSchermRules = businessRuleDAO.findStartSchermRules();
        for(BusinessRule br : brStartSchermRules){
            System.out.println(br.getName());
        }
        return businessRuleDAO.findStartSchermRules();
    }

    public BusinessRule findById(int id){
        return businessRuleDAO.findById(id);
    }




}
