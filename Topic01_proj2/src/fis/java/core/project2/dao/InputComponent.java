package fis.java.core.project2.dao;
public class InputComponent {
    private IValidation validation;
    private String data;

    public void setData(String data) {
        this.data = data;
    }
    public void display(){

    }
    public void setValidation(IValidation validation){

    }
    public boolean validate(){
        return true;
    }
}
