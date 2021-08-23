package brgenerator.generate;

import brgenerator.model.AttributeRange;
import brgenerator.model.BusinessRule;

public class AttributeRangeGenerator extends Trigger {

        private String template =
                "CREATE OR REPLACE FUNCTION %s_function() " +   "RETURNS TRIGGER AS " + "$BODY$ "
                        +   "BEGIN "
                        +       "IF (NEW.%s %s %s AND %s) THEN "
                        +           "RETURN NEW; "
                        +       "END IF; "
                        +           "RAISE EXCEPTION '%s' "
                        +           "USING ERRCODE = 22000; "
                        +   "END "
                        + "$BODY$ "
                        + "LANGUAGE plpgsql SECURITY INVOKER; ";

        public String generateRule (String brname,String columnname, String tablename, String operator, int minval, int maxval) {
            String message = "";
            template = String.format(template, brname, columnname, operator, minval, maxval, message);
            String Trigger = generateGenericTrigger(brname,columnname,tablename,brname);
            template += Trigger;

            return template;
        }
    }
