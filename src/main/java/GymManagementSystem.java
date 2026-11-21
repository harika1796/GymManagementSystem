
import java.util.Scanner;

import service.MemberService;
import service.AttendanceService;
import service.TrainerService;

import util.InputUtil;

import exceptions.DuplicateMemberException;
import exceptions.InvalidAgeException;
import exceptions.MemberNotFoundException;

public class GymManagementSystem {

    public static void main(String[] args) {

        Scanner sc = InputUtil.sc;

        AttendanceService attendanceService =
                new AttendanceService(sc);

        TrainerService trainerService =
                new TrainerService(sc);

        int choice;

        do {

            System.out.println("\n================================");
            System.out.println("     GYM MANAGEMENT SYSTEM");
            System.out.println("================================");

            System.out.println("1. Register Member");
            System.out.println("2. View Members");
            System.out.println("3. Search Member");
            System.out.println("4. Select Membership Plan");
            System.out.println("5. Renew Membership");
            System.out.println("6. Mark Attendance");
            System.out.println("7. View Attendance History");
            System.out.println("8. Add Trainer");
            System.out.println("9. View Trainers");
            System.out.println("10. Search Trainer");
            System.out.println("11. Delete Trainer");
            System.out.println("0. Exit");

            System.out.println("================================");
            System.out.print("Enter your choice: ");

            choice = InputUtil.readNumber();

            switch (choice) {

                case 1:
                    try {
                        MemberService.registerMember();
                    } catch (DuplicateMemberException |
                             InvalidAgeException e) {
                        System.out.println(e.getMessage());
                    }
                    break;

                case 2:
                    MemberService.viewMembers();
                    break;

                case 3:
                    try {
                        System.out.print("Enter Member ID: ");
                        int id = InputUtil.readNumber();

                        System.out.println(
                            MemberService.searchMember(id).name
                        );

                    } catch (MemberNotFoundException e) {
                        System.out.println(e.getMessage());
                    }
                    break;

                case 4:
                    try {
                        MemberService.selectPlan();
                    } catch (MemberNotFoundException e) {
                        System.out.println(e.getMessage());
                    }
                    break;

                case 5:
                    try {
                        MemberService.renewMembership();
                    } catch (MemberNotFoundException e) {
                        System.out.println(e.getMessage());
                    }
                    break;

                case 6:
                    attendanceService.markAttendance();
                    break;

                case 7:
                    attendanceService.viewAttendance();
                    break;

                case 8:
                    trainerService.addTrainer();
                    break;

                case 9:
                    trainerService.viewTrainers();
                    break;

                case 10:
                    trainerService.searchTrainer();
                    break;

                case 11:
                    trainerService.deleteTrainer();
                    break;

                case 0:
                    System.out.println(
                        "Thank you for using Gym Management System!"
                    );
                    break;

                default:
                    System.out.println("Invalid choice! Try again.");
            }

        } while (choice != 0);
    }
}
