package brgenerator.model;

import brgenerator.persistency.*;
import brgenerator.services.TestCreateService;

import java.util.ArrayList;
import java.util.List;

public class Main {


    public static void main(String[] args) {
        BusinessRuleDAO businessRuleDAOImpl = new BusinessRuleDAOImpl();
        AttributeRangeDAO attributeRangeDAOImpl = new AttributeRangeDAOImpl();
        TargetDatabaseDAO targetDatabaseDAOImpl = new TargetDatabaseDAOImpl();
        TableDAO tableDaoImpl = new TableDAOImpl();
        ColumnDAO columnDAO = new ColumnDAOImpl();
        TestCreateService testCreateService = new TestCreateService();


        //testCreateService.createAR("test", "test");


    }
}


