package brgenerator.services;

import brgenerator.model.Table;
import brgenerator.persistency.TableDAO;
import brgenerator.persistency.TableDAOImpl;

import java.sql.SQLException;
import java.util.List;

public class TableService {
    TableDAO tableDAO = new TableDAOImpl();

    public Table create(String name) throws SQLException {
        Table newTable = new Table(name);
        int tid = tableDAO.create(newTable);
        newTable.setId(tid);
        return newTable;
    }
    public List<Table> findAll(){
        return tableDAO.findAll();
    }

    public Table findById(int id){
        return tableDAO.findById(id);
    }


}
