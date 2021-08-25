package brgenerator.generate;

public class AttributeCompareGenerator extends Trigger {


    //PLSQL CODE IN FORMAT

    private String plsql =
            "CREATE OR REPLACE FUNCTION %s_function() "
                    +   "RETURNS TRIGGER AS "
                    + "$BODY$ "
                    +   "BEGIN "
                    +       "IF (NEW.%s %s '%s') THEN "
                    +           "RAISE EXCEPTION '%s' "
                    +           "USING ERRCODE = 22000; "
                    +       "END IF; "

                    +       "RETURN NEW; "
                    +   "END "
                    + "$BODY$ "
                    + "LANGUAGE plpgsql SECURITY INVOKER; ";

    //INVULLEN PLSQL MET VARIABELEN

    public String generateRule (String brname,String columnname, String tablename, String operator, int litval) {
        String message = "";
        plsql = String.format(plsql,
                brname,
                columnname,
                operator,
                litval,message);

        //OPHALEN TRIGGER EN TOEVOEGEN AAN PLSQL

        String Trigger = generateGenericTrigger(brname,columnname,tablename,brname);
        plsql += Trigger;

        return plsql;
    }
}
