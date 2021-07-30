package brgenerator.model;

import brgenerator.persistency.*;

public class Main {


    public static void main(String[] args) {
        BusinessRuleDAO businessRuleDAOImpl = new BusinessRuleDAOImpl();
        AttributeRangeDAO attributeRangeDAOImpl = new AttributeRangeDAOImpl();
        TargetDatabaseDAO targetDatabaseDAOImpl = new TargetDatabaseDAOImpl();
        TableDAO tableDaoImpl = new TableDAOImpl();
        ColumnDAO columnDAO = new ColumnDAOImpl();
        TestCreateService testCreateService = new TestCreateService();

        //AttributeRange ar = new AttributeRange("Testmans", "OVer",1,10);

        }




    }
}


