package com.farmacy.Modules.unitmeasurement.infrastructure.controller;

import java.util.Optional;
import java.util.Scanner;

import com.farmacy.Modules.unitmeasurement.aplication.CreateUnitMeasurementUserCase;
import com.farmacy.Modules.unitmeasurement.aplication.DeleteUnitMeasurementUserCase;
import com.farmacy.Modules.unitmeasurement.aplication.ReadUnitMeasurementUserCase;
import com.farmacy.Modules.unitmeasurement.aplication.UpdateUnitMeasurementUserCase;
import com.farmacy.Modules.unitmeasurement.domain.entity.UnitMeasurement;

public class UnitMeasurementController {
    private CreateUnitMeasurementUserCase createUnitMeasurementUserCase;
    private ReadUnitMeasurementUserCase readUnitMeasurementUserCase;
    private UpdateUnitMeasurementUserCase updateUnitMeasurementUserCase;
    private DeleteUnitMeasurementUserCase deleteUnitMeasurementUserCase;
    Object[] options = {"Create Unit Measurement", "Read Unit Measurement", "Update Unit Measurement", "Delete Unit Measurement"};
    int choice = -1;

    public UnitMeasurementController(CreateUnitMeasurementUserCase createUnitMeasurementUserCase,ReadUnitMeasurementUserCase readUnitMeasurementUserCase,UpdateUnitMeasurementUserCase updateUnitMeasurementUserCase,DeleteUnitMeasurementUserCase deleteUnitMeasurementUserCase) {
        this.createUnitMeasurementUserCase = createUnitMeasurementUserCase;
        this.readUnitMeasurementUserCase = readUnitMeasurementUserCase;
        this.updateUnitMeasurementUserCase = updateUnitMeasurementUserCase;
        this.deleteUnitMeasurementUserCase = deleteUnitMeasurementUserCase;
    }

    public void run() {
        Scanner scanner = new Scanner(System.in);
        while (choice != 0) {
            System.out.println("Please select an option:");
            for (int i = 0; i < options.length; i++) {
                System.out.println((i + 1) + ". " + options[i]);
            }
            System.out.println("0. Exit");

            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    try {
                        System.out.println("\nEnter Unit Measurement Name: ");
                        String name = scanner.nextLine();
                        UnitMeasurement unitMeasurement = new UnitMeasurement();
                        unitMeasurement.setNameUm(name);

                        createUnitMeasurementUserCase.execute(unitMeasurement);

                        System.out.println("Unit Measurement created successfully!");
                    } catch (Exception e) {
                        System.out.println("An error occurred while creating the unit measurement: " + e.getMessage());
                    }
                    break;
                case 2:
                    try {
                        System.out.println("\nEnter Unit Measurement ID: ");
                        int id = scanner.nextInt();
                        scanner.nextLine();
                        readUnitMeasurementUserCase.execute(id).ifPresentOrElse(
                            unitMeasurement -> {
                                System.out.println("Unit Measurement Info:");
                                System.out.println("ID: " + unitMeasurement.getIdUm());
                                System.out.println("Name: " + unitMeasurement.getNameUm());
                            },
                            () -> System.out.println("Unit Measurement not found!")
                        );
                    } catch (Exception e) {
                        System.out.println("An error occurred while reading the unit measurement: " + e.getMessage());
                    }
                    break;
                case 3:
                    try {
                        System.out.println("\nEnter Unit Measurement ID to Update: ");
                        int id = scanner.nextInt();
                        scanner.nextLine();
                        Optional<UnitMeasurement> unitMeasurementToUpdate = readUnitMeasurementUserCase.execute(id);

                        unitMeasurementToUpdate.ifPresentOrElse(
                            unitMeasurement -> {
                                boolean continueUpdating = true;
                                while (continueUpdating) {
                                    System.out.println("\nSelect field to update:");
                                    System.out.println("1. Unit Measurement Name");
                                    System.out.println("0. Exit");

                                    System.out.print("Enter your choice: ");
                                    int updateChoice = scanner.nextInt();
                                    scanner.nextLine();
                                    switch (updateChoice) {
                                        case 1:
                                            System.out.println("\nEnter new Unit Measurement Name: ");
                                            String newName = scanner.nextLine();
                                            unitMeasurement.setNameUm(newName);
                                            break;
                                        case 0:
                                            continueUpdating = false;
                                            break;
                                        default:
                                            System.out.println("Invalid choice. Please try again.");
                                            continue;
                                    }
                                    if (updateChoice != 0) {
                                        updateUnitMeasurementUserCase.execute(unitMeasurement, id);
                                        System.out.println("Unit Measurement updated successfully!");
                                    }
                                }
                            },
                            () -> System.out.println("Unit Measurement not found!")
                        );
                    } catch (Exception e) {
                        System.out.println("An error occurred while updating the unit measurement: " + e.getMessage());
                    }
                    break;
                case 4:
                    try {
                        System.out.println("\nEnter Unit Measurement ID to Delete: ");
                        int id = scanner.nextInt();
                        scanner.nextLine();
                        deleteUnitMeasurementUserCase.execute(id);
                        System.out.println("Unit Measurement deleted successfully!");
                    } catch (Exception e) {
                        System.out.println("An error occurred while deleting the unit measurement: " + e.getMessage());
                    }
                    break;
                case 0:
                    System.out.println("Exiting...");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }

        scanner.close();
    }
}