
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

    // Add a new trainer
    public void addTrainer() {

        System.out.print("Enter Trainer Name: ");
        
        String name = sc.nextLine();

        System.out.print("Enter Specialization: ");
        String specialization = sc.nextLine();

        int id = GymDataStore.nextTrainerId++;

        Trainer trainer = new Trainer(id, name, specialization);

        GymDataStore.records.add(trainer);

        System.out.println("Trainer added successfully!");
        System.out.println("Trainer ID: " + id);
    }

    // View all trainers
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

    // Search trainer by ID
    public void searchTrainer() {

        System.out.print("Enter Trainer ID to search: ");
        int id = Integer.parseInt(sc.nextLine());

        boolean found = false;

        for (GymRecord record : GymDataStore.records) {

            if (record instanceof Trainer) {

                Trainer trainer = (Trainer) record;

                if (trainer.id == id) {

                    System.out.println("\n===== TRAINER FOUND =====");
                    System.out.println("Trainer ID: " + trainer.id);
                    System.out.println("Name: " + trainer.name);
                    System.out.println("Specialization: " + trainer.specialization);

                    found = true;
                    break;
                }
            }
        }

        if (!found) {
            System.out.println("Trainer not found!");
        }
    }

    // Delete trainer by ID
    public void deleteTrainer() {

        System.out.print("Enter Trainer ID to delete: ");
        int id = Integer.parseInt(sc.nextLine());

        Trainer selectedTrainer = null;

        for (GymRecord record : GymDataStore.records) {

            if (record instanceof Trainer) {

                Trainer trainer = (Trainer) record;

                if (trainer.id == id) {
                    selectedTrainer = trainer;
                    break;
                }
            }
        }

        if (selectedTrainer == null) {
            System.out.println("Trainer not found!");
            return;
        }

        GymDataStore.records.remove(selectedTrainer);

        System.out.println("Trainer deleted successfully!");
    }
}
