package brgenerator.generate;

public class Trigger {

    public String generateGenericTrigger (String name, String columnname, String tablename,String fname) {
        String trigger =
                "DROP TRIGGER IF EXISTS %s " + "ON %s; " + "CREATE TRIGGER %s " + "BEFORE INSERT OR UPDATE OF %s ON %s " + "FOR EACH ROW EXECUTE PROCEDURE %s_function();";
        trigger = String.format(trigger, name, tablename, name, columnname, tablename, fname);
        return trigger;
    }
}