package brgenerator.services;

import brgenerator.model.Column;
import brgenerator.model.Table;
import brgenerator.persistency.ColumnDAO;
import brgenerator.persistency.ColumnDAOImpl;

import java.sql.SQLException;
import java.util.List;

public class ColumnService {
    private ColumnDAO columnDAOImpl = new ColumnDAOImpl();

    public Column createColumn(String name, String type, int tableid) throws SQLException {
        TableService tableService = ServiceProvider.getTableService();
        for (Table t : tableService.findAll()) {
            if (t.getId() == tableid) {
                Column newColumn = new Column(name, type);
                int cid = columnDAOImpl.create(newColumn, tableid);
                newColumn.setId(cid);
                return newColumn;
            }
        }
        return null;
    }

    public List<Column> findAll(){
        return columnDAOImpl.findAll();
    }

    public Column findById(int id){
        return columnDAOImpl.findById(id);
    }


}
