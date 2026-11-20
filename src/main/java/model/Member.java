
package model;

import java.time.LocalDate;

public class Member extends GymRecord {

    public int id;
    public int age;

    public String name;
    public String phone;
    public String plan;
    public String trainer;

    public double fee;

    public LocalDate startDate;
    public LocalDate endDate;

    public Member(int id, String name, int age, String phone) {

        this.id = id;
        this.name = name;
        this.age = age;
        this.phone = phone;

        plan = "Not Selected";
        trainer = "Not Assigned";
    }
}
