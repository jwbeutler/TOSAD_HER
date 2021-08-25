package brgenerator.persistency;

import brgenerator.model.Column;

import java.sql.SQLException;
import java.util.List;

public interface ColumnDAO {
    public List<Column> findAll();
    public Column findById(int id);
    public int create(Column column, int tableid) throws SQLException;
}
