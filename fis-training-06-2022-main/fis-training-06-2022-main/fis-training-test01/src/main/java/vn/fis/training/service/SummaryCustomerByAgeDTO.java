package vn.fis.training.service;

public class SummaryCustomerByAgeDTO {
    private int age;
    private int count;
    //TODO: Setters, Getter, Constructors

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public SummaryCustomerByAgeDTO(int age, int count) {
        this.age = age;
        this.count = count;
    }
}
