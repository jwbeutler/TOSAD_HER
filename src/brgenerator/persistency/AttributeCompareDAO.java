package brgenerator.persistency;

import brgenerator.model.AttributeCompare;

import java.sql.SQLException;
import java.util.List;

public interface AttributeCompareDAO {
    public int create(AttributeCompare attributeCompare) throws SQLException;
    public List<AttributeCompare> findAll();
    public AttributeCompare findByID(int id);
}
