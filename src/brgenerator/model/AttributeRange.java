package brgenerator.model;

public class AttributeRange extends BusinessRule{
    private String operator;
    private int minvalue;
    private int maxvalue;

    public AttributeRange(String name,String operator,int minvalue, int maxvalue){
        super(name,"Attribute Range");
        this.operator = operator;
        this.minvalue = minvalue;
        this.maxvalue = maxvalue;
    }
    public String getType(){
        return super.type;
    }
    public String getName(){
        return super.name;
    }

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }

    public int getMinvalue() {
        return minvalue;
    }

    public void setMinvalue(int minvalue) {
        this.minvalue = minvalue;
    }

    public int getMaxvalue() {
        return maxvalue;
    }

    public void setMaxvalue(int maxvalue) {
        this.maxvalue = maxvalue;
    }

}
