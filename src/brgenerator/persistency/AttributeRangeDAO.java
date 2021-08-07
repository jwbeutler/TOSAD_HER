package brgenerator.persistency;

import brgenerator.model.AttributeRange;
import brgenerator.model.BusinessRule;

import java.util.List;

public interface AttributeRangeDAO {
    public boolean create(AttributeRange attributeRange);
    public List<AttributeRange> findAll();
    public AttributeRange findById(int id);
}
