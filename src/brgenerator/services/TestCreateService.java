package brgenerator.services;

import brgenerator.model.AttributeRange;
import brgenerator.model.Column;
import brgenerator.model.Table;
import brgenerator.persistency.*;

import java.util.List;

public class TestCreateService {
    BusinessRuleDAO businessRuleDAOImpl = new BusinessRuleDAOImpl();
    AttributeRangeDAO attributeRangeDAOImpl = new AttributeRangeDAOImpl();
    TargetDatabaseDAO targetDatabaseDAOImpl = new TargetDatabaseDAOImpl();
    TableDAO tableDaoImpl = new TableDAOImpl();
    ColumnDAO columnDAO = new ColumnDAOImpl();

    public List<Table> getAllTargetTables(){
        return targetDatabaseDAOImpl.findAll();
    }
    public List<Column> getAllTargetColumns(String tablename){
        return targetDatabaseDAOImpl.findColumnsByTable(tablename);
    }
    public AttributeRange createRangeRule(String name, String operator, int min, int max){
        AttributeRange newAr = new AttributeRange(name,operator,min,max);
        attributeRangeDAOImpl.create(newAr);
        return newAr;
    }

//    public boolean createAR(String tableName, String columnName) {
//        AttributeRange br = new AttributeRange("AR1", "Between", 1, 10);
//        List<Table> allTables = targetDatabaseDAOImpl.findAll();
//        for (Table t : allTables) {
//            if ((t.getName().equals(tableName))) {
//                for (Column c : targetDatabaseDAOImpl.findColumnsByTable(tableName)) {
//                    if ((c.getName().equals(columnName))) {
//                        attributeRangeDAOImpl.create(br);
//                        tableDaoImpl.create(t);
//                        columnDAO.create(c, t.getId());
//                        businessRuleDAOImpl.create(br, c.getId(), br.getId());

}