package brgenerator.generate;

import brgenerator.model.BusinessRule;

public class Trigger {

    public String generateGenericTrigger (String name, String columnname, String tablename,String fname) {
        String template =
                "DROP TRIGGER IF EXISTS %s "
                        +   "ON %s; "

                        + "CREATE TRIGGER %s "
                        +   "BEFORE INSERT OR UPDATE OF %s ON %s "
                        +   "FOR EACH ROW EXECUTE PROCEDURE %s_function();";

        template = String.format(
                template,
                name,
                tablename,
                name,
                columnname,
                tablename,
                fname);


        return template;
    }
}