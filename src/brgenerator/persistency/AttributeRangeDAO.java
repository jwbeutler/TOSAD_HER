package brgenerator.persistency;

import brgenerator.model.AttributeRange;
import brgenerator.model.BusinessRule;

import java.sql.SQLException;
import java.util.List;

public interface AttributeRangeDAO {
    public int create(AttributeRange attributeRange) throws SQLException;
    public List<AttributeRange> findAll();
    public AttributeRange findById(int id);
}
