package brgenerator.persistency;

import brgenerator.model.Column;

import java.util.List;

public interface ColumnDAO {
    public List<Column> findColumnsByTable(int tableid);
    public List<Column> findAll();
    public Column findById(int id);
    public boolean create(Column column, int tableid);
}
