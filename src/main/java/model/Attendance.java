
package model;

import java.time.LocalDate;

public class Attendance extends GymRecord {

    public int memberId;
    public String memberName;
    public LocalDate date;

    public Attendance(int id, String name) {
        this.memberId = id;
        this.memberName = name;
        this.date = LocalDate.now();
    }
}
