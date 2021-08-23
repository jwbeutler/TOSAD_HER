package brgenerator.generate;

public class AttributeCompareGenerator extends Trigger {

    private String template =
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

    public String generateRule (String brname,String columnname, String tablename, String operator, int litval) {
        String message = "";
        template = String.format(template,
                brname,
                columnname,
                operator,
                litval,message);

        String Trigger = generateGenericTrigger(brname,columnname,tablename,brname);
        template += Trigger;

        return template;
    }
}
