
package service;

import model.Member;
import model.GymRecord;
import datastructures.GymDataStore;

public class MembershipService {

    // Display all registered members
    public static void viewMembers() {

        boolean found = false;

        System.out.println("\n===== REGISTERED MEMBERS =====");

        for (GymRecord r : GymDataStore.records) {

            if (r instanceof Member) {

                Member m = (Member) r;

                System.out.println("\nMember ID: " + m.id);
                System.out.println("Name: " + m.name);
                System.out.println("Age: " + m.age);
                System.out.println("Phone: " + m.phone);
                System.out.println("Plan: " + m.plan);
                System.out.println("Trainer: " + m.trainer);

                found = true;
            }
        }

        if (!found) {
            System.out.println("No members registered yet!");
        }
    }

    // Display available membership plans
    public static void viewPlans() {

        System.out.println("\n===== GYM MEMBERSHIP PLANS =====");

        System.out.println("1. Monthly Plan  - Rs. 1000");
        System.out.println("2. Quarterly Plan - Rs. 2500");
        System.out.println("3. Half-Yearly Plan - Rs. 4500");
        System.out.println("4. Yearly Plan - Rs. 8000");
    }
}
