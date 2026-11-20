
package service;

import java.time.LocalDate;

import model.Member;
import model.GymRecord;
import datastructures.GymDataStore;
import exceptions.MemberNotFoundException;
import exceptions.DuplicateMemberException;
import exceptions.InvalidAgeException;
import util.InputUtil;
import util.ValidationUtil;

public class MemberService {

    // Register a new member
    public static void registerMember()
            throws DuplicateMemberException, InvalidAgeException {

        String name;

        while (true) {
            System.out.print("Enter member name: ");
            name = InputUtil.readLine().trim();

            if (ValidationUtil.isValidName(name)) {
                break;
            }

            System.out.println("Invalid name! Enter letters only.");
        }

        System.out.print("Enter age: ");
        int age = InputUtil.readNumber();

        if (age < 16 || age > 100) {
            System.out.println("Invalid age! Age must be between 16 and 100.");
            return;
        }

        String phone;

        while (true) {
            System.out.print("Enter phone number: ");
            phone = InputUtil.readLine().trim();

            if (ValidationUtil.isValidPhone(phone)) {
                break;
            }

            System.out.println("Invalid phone number! Enter a valid 10-digit number.");
        }

        for (GymRecord r : GymDataStore.records) {
            if (r instanceof Member) {
                Member m = (Member) r;

                if (m.phone.equals(phone)) {
                    throw new DuplicateMemberException("Member already exists!");
                }
            }
        }

        Member m = new Member(
                GymDataStore.nextMemberId++, name, age, phone
        );

        GymDataStore.records.add(m);

        System.out.println("Member registered successfully!");
        System.out.println("Your Member ID: " + m.id);
    }

    // Search for a member using ID
    public static Member searchMember(int id)
            throws MemberNotFoundException {

        for (GymRecord r : GymDataStore.records) {
            if (r instanceof Member) {
                Member m = (Member) r;

                if (m.id == id) {
                    return m;
                }
            }
        }

        throw new MemberNotFoundException("Member not found!");
    }

    // Display all registered members
    public static void viewMembers() {

        boolean found = false;

        for (GymRecord r : GymDataStore.records) {
            if (r instanceof Member) {
                Member m = (Member) r;
                found = true;

                System.out.println("\n--------------------");
                System.out.println("Member ID: " + m.id);
                System.out.println("Name: " + m.name);
                System.out.println("Age: " + m.age);
                System.out.println("Phone: " + m.phone);
                System.out.println("Plan: " + m.plan);
                System.out.println("Fee: Rs." + m.fee);
                System.out.println("Trainer: " + m.trainer);
                System.out.println("Start Date: " + m.startDate);
                System.out.println("Expiry Date: " + m.endDate);
            }
        }

        if (!found) {
            System.out.println("No members registered!");
        }
    }

    // Display available membership plans
    public static void viewPlans() {

        System.out.println("\n--- MEMBERSHIP PLANS ---");

        System.out.println("1. Monthly     - Rs.1000 - 30 Days");
        System.out.println("2. Quarterly   - Rs.2500 - 90 Days");
        System.out.println("3. Half Yearly - Rs.4500 - 180 Days");
        System.out.println("4. Yearly      - Rs.8000 - 365 Days");
    }

    // Set membership plan and fee
    public static void setPlan(Member m, int choice) {

        switch (choice) {
            case 1:
                m.plan = "Monthly";
                m.fee = 1000;
                break;

            case 2:
                m.plan = "Quarterly";
                m.fee = 2500;
                break;

            case 3:
                m.plan = "Half Yearly";
                m.fee = 4500;
                break;

            case 4:
                m.plan = "Yearly";
                m.fee = 8000;
                break;
        }
    }

    // Get the duration of the selected plan
    public static int getDuration(int choice) {

        switch (choice) {
            case 1:
                return 30;
            case 2:
                return 90;
            case 3:
                return 180;
            case 4:
                return 365;
            default:
                return 0;
        }
    }

    // Select a membership plan
    public static void selectPlan()
            throws MemberNotFoundException {

        System.out.print("Enter Member ID: ");
        int id = InputUtil.readNumber();

        Member m = searchMember(id);

        viewPlans();

        System.out.print("Choose plan: ");
        int choice = InputUtil.readNumber();

        if (choice < 1 || choice > 4) {
            System.out.println("Invalid plan!");
            return;
        }

        setPlan(m, choice);

        m.startDate = LocalDate.now();
        m.endDate = m.startDate.plusDays(getDuration(choice));

        System.out.println("Plan selected successfully!");
        System.out.println("Expiry Date: " + m.endDate);
    }

    // Renew an existing membership
    public static void renewMembership()
            throws MemberNotFoundException {

        System.out.print("Enter Member ID: ");
        int id = InputUtil.readNumber();

        Member m = searchMember(id);

        viewPlans();

        System.out.print("Choose renewal plan: ");
        int choice = InputUtil.readNumber();

        if (choice < 1 || choice > 4) {
            System.out.println("Invalid plan!");
            return;
        }

        LocalDate today = LocalDate.now();
        LocalDate renewalDate = today;

        // Extend from the existing expiry date if the plan is still active
        if (m.endDate != null && m.endDate.isAfter(today)) {
            renewalDate = m.endDate;
        }

        setPlan(m, choice);

        m.startDate = today;
        m.endDate = renewalDate.plusDays(getDuration(choice));

        System.out.println("Membership renewed!");
        System.out.println("New Expiry Date: " + m.endDate);
    }
}
