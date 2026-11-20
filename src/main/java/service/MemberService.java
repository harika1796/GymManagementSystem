
package service;

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
            throw new InvalidAgeException("Age must be between 16 and 100!");
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

        // Check for duplicate phone numbers
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
}
