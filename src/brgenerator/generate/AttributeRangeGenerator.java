package brgenerator.generate;

public class AttributeRangeGenerator extends Trigger {

    //PLSQL CODE IN FORMAT
    private String plsql = "CREATE OR REPLACE FUNCTION %s_function() " +   "RETURNS TRIGGER AS " + "$BODY$ "
                        +   "BEGIN "
                        +       "IF (NEW.%s %s %s AND %s) THEN "
                        +           "RETURN NEW; "
                        +       "END IF; "
                        +           "RAISE EXCEPTION '%s' "
                        +           "USING ERRCODE = 22000; "
                        +   "END "
                        + "$BODY$ "
                        + "LANGUAGE plpgsql SECURITY INVOKER; ";

    //INVULLEN PLSQL MET VARIABELEN

    public String generateRule (String brname,String columnname, String tablename, String operator, int minval, int maxval) {
            String message = "";
            plsql = String.format(plsql, brname, columnname, operator, minval, maxval, message);

        //OPHALEN TRIGGER EN TOEVOEGEN AAN PLSQL

        String Trigger = generateGenericTrigger(brname,columnname,tablename,brname);
            plsql += Trigger;

            return plsql;
        }
    }
