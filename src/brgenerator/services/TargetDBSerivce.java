package brgenerator.services;

import brgenerator.model.Column;
import brgenerator.model.Table;
import brgenerator.persistency.TargetDatabaseDAO;
import brgenerator.persistency.TargetDatabaseDAOImpl;

import java.util.List;

public class TargetDBSerivce {
    private TargetDatabaseDAO targetDatabaseDAO = new TargetDatabaseDAOImpl();

    public List<Table> findAll(){
        return targetDatabaseDAO.findAll();
    }
    public List<Column> findColumnsByTable(String tableName){
        return targetDatabaseDAO.findColumnsByTable(tableName);
    }
    public Table findTableByName(String tableName){
        return targetDatabaseDAO.findTableByName(tableName);
    }
}
