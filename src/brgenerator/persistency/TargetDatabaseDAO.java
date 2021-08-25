package brgenerator.persistency;

import brgenerator.model.Column;
import brgenerator.model.Table;

import java.sql.SQLException;
import java.util.List;

public interface TargetDatabaseDAO {
    public List<Table> findAll();
    public Table findTableByName(String name);
    public List<Column> findColumnsByTable(String tablename);
    public boolean executeRuleScript(String script) throws SQLException;
}
