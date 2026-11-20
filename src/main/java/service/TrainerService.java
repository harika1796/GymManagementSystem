
package service;

import model.Trainer;
import model.GymRecord;
import datastructures.GymDataStore;

import java.util.Scanner;

public class TrainerService {

    private Scanner sc;

    public TrainerService(Scanner sc) {
        this.sc = sc;
    }

    public void addTrainer() {

        System.out.print("Enter Trainer Name: ");
        sc.nextLine();
        String name = sc.nextLine();

        System.out.print("Enter Specialization: ");
        String specialization = sc.nextLine();

        int id = GymDataStore.nextTrainerId++;

        Trainer trainer = new Trainer(id, name, specialization);

        GymDataStore.records.add(trainer);

        System.out.println("Trainer added successfully!");
        System.out.println("Trainer ID: " + id);
    }

    public void viewTrainers() {

        boolean found = false;

        System.out.println("\n===== TRAINER DETAILS =====");

        for (GymRecord record : GymDataStore.records) {

            if (record instanceof Trainer) {

                Trainer trainer = (Trainer) record;

                System.out.println("Trainer ID: " + trainer.id);
                System.out.println("Name: " + trainer.name);
                System.out.println("Specialization: " + trainer.specialization);
                System.out.println("---------------------------");

                found = true;
            }
        }

        if (!found) {
            System.out.println("No trainers found!");
        }
    }
}

