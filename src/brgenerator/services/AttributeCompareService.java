package brgenerator.services;

import brgenerator.model.AttributeCompare;
import brgenerator.model.AttributeRange;
import brgenerator.persistency.AttributeCompareDAO;
import brgenerator.persistency.AttributeCompareDAOImpl;
import brgenerator.persistency.AttributeRangeDAO;
import brgenerator.persistency.AttributeRangeDAOImpl;

import java.sql.SQLException;
import java.util.List;

public class AttributeCompareService {
    AttributeCompareDAO attributeCompareDAO = new AttributeCompareDAOImpl();

    public AttributeCompare createAC(String name, String operator, int litval) throws SQLException{
        AttributeCompare newAC = new AttributeCompare(name,operator,litval);
        int id = attributeCompareDAO.create(newAC);
        newAC.setId(id);
        return newAC;
    }

    public List<AttributeCompare> findAll(){
        return attributeCompareDAO.findAll();
    }

    public AttributeCompare findById(int id){
        return attributeCompareDAO.findByID(id);
    }


}
