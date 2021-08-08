package brgenerator.persistency;

import brgenerator.model.Table;

import java.sql.SQLException;
import java.util.List;

public interface TableDAO {
    public int create(Table table) throws SQLException;
    public List<Table> findAll();
    public Table findById(int id);
}
