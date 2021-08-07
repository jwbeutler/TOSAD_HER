package brgenerator.persistency;

import brgenerator.model.Table;

import java.util.List;

public interface TableDAO {
    public boolean create(Table table);
    public List<Table> findAll();
    public Table findById(int id);
}
