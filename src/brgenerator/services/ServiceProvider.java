package brgenerator.services;

import brgenerator.model.AttributeRange;

public class ServiceProvider {
    private static TargetDBSerivce targetDBService = new TargetDBSerivce();
    private static BusinessRuleService businessRuleService = new BusinessRuleService();
    private static AttributeRangeService attributeRangeService = new AttributeRangeService();
    private static TableService tableService = new TableService();
    private static ColumnService columnService = new ColumnService();

    public static TargetDBSerivce getTargetDBService(){
        return targetDBService;
    }
    public static BusinessRuleService getBusinessRuleService(){
        return businessRuleService;
    }
    public static AttributeRangeService getAttributeRangeService(){
        return attributeRangeService;
    }
    public static TableService getTableService(){
        return tableService;
    }
    public static ColumnService getColumnService(){
        return columnService;
    }

}
