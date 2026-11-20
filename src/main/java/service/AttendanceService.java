package service;

import model.Member;
import model.GymRecord;
import model.Attendance;
import datastructures.GymDataStore;

import java.util.Scanner;

public class AttendanceService {

    private Scanner sc;

    public AttendanceService(Scanner sc) {
        this.sc = sc;
    }

    public void markAttendance() {

        System.out.print("Enter Member ID: ");
        int id = sc.nextInt();

        Member selectedMember = null;

        for (GymRecord record : GymDataStore.records) {

            if (record instanceof Member) {

                Member member = (Member) record;

                if (member.id == id) {
                    selectedMember = member;
                    break;
                }
            }
        }

        if (selectedMember == null) {
            System.out.println("Member not found!");
            return;
        }

        Attendance attendance = new Attendance(
            selectedMember.id,
            selectedMember.name
        );

        GymDataStore.records.add(attendance);

        System.out.println("Attendance marked successfully!");
        System.out.println("Member: " + selectedMember.name);
        System.out.println("Date: " + attendance.date);
    }

    public void viewAttendance() {

        if (GymDataStore.records.isEmpty()) {
            System.out.println("No attendance records found!");
            return;
        }

        boolean found = false;

        System.out.println("\n===== ATTENDANCE HISTORY =====");

        System.out.printf("%-12s %-20s %-15s%n",
                "Member ID", "Member Name", "Date");

        for (GymRecord record : GymDataStore.records) {

            if (record instanceof Attendance) {

                Attendance attendance = (Attendance) record;

                System.out.printf("%-12d %-20s %-15s%n",
                        attendance.memberId,
                        attendance.memberName,
                        attendance.date);

                found = true;
            }
        }

        if (!found) {
            System.out.println("No attendance records found!");
        }
    }
}