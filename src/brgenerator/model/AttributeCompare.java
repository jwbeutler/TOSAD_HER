package brgenerator.model;

public class AttributeCompare extends BusinessRule {
    private int id;
    private String operator;
    private int litvalue;

    public AttributeCompare(String name, String operator, int litvalue){
        super(name,"AttributeCompare");
        this.operator = operator;
        this.litvalue = litvalue;
    }
    public AttributeCompare(int id,String name,String operator, int litvalue){
        super(name,"AttributeCompare");
        this.id = id;
        this.operator = operator;
        this.litvalue = litvalue;
    }

    @Override
    public int getId() {
        return id;
    }

    @Override
    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }

    public int getLitvalue() {
        return litvalue;
    }

    public void setLitvalue(int litvalue) {
        this.litvalue = litvalue;
    }
}
