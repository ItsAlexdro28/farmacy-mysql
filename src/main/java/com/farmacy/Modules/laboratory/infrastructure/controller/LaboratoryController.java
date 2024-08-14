package com.farmacy.Modules.laboratory.infrastructure.controller;

import java.util.Optional;
import java.util.Scanner;

import com.farmacy.Modules.laboratory.aplication.CreateLaboratoryUserCase;
import com.farmacy.Modules.laboratory.aplication.DeleteLaboratoryUserCase;
import com.farmacy.Modules.laboratory.aplication.ReadLaboratoryUserCase;
import com.farmacy.Modules.laboratory.aplication.UpdateLaboratoryUserCase;
import com.farmacy.Modules.laboratory.domain.entity.Laboratory;

public class LaboratoryController {
    private final CreateLaboratoryUserCase createLaboratoryUserCase;
    private final ReadLaboratoryUserCase readLaboratoryUserCase;
    private final UpdateLaboratoryUserCase updateLaboratoryUserCase;
    private final DeleteLaboratoryUserCase deleteLaboratoryUserCase;

    public LaboratoryController(CreateLaboratoryUserCase createLaboratoryUserCase,
                                ReadLaboratoryUserCase readLaboratoryUserCase,
                                UpdateLaboratoryUserCase updateLaboratoryUserCase,
                                DeleteLaboratoryUserCase deleteLaboratoryUserCase) {
        this.createLaboratoryUserCase = createLaboratoryUserCase;
        this.readLaboratoryUserCase = readLaboratoryUserCase;
        this.updateLaboratoryUserCase = updateLaboratoryUserCase;
        this.deleteLaboratoryUserCase = deleteLaboratoryUserCase;
    }

    public void run() {
        Scanner scanner = new Scanner(System.in);
        int choice = -1;

        while (choice != 0) {
            System.out.println("Please select an option:");
            System.out.println("1. Create Laboratory");
            System.out.println("2. Read Laboratory");
            System.out.println("3. Update Laboratory");
            System.out.println("4. Delete Laboratory");
            System.out.println("0. Exit");

            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    try {
                        System.out.println("\nEnter Laboratory Name: ");
                        String name = scanner.nextLine();
                        System.out.println("Enter City Code: ");
                        String codeCityReg = scanner.nextLine();

                        Laboratory laboratory = new Laboratory();
                        laboratory.setNameLab(name);
                        laboratory.setCodeCityReg(codeCityReg);

                        createLaboratoryUserCase.execute(laboratory);

                        System.out.println("Laboratory created successfully!");
                    } catch (Exception e) {
                        System.out.println("An error occurred while creating the laboratory: " + e.getMessage());
                    }
                    break;
                case 2:
                    try {
                        System.out.println("\nEnter Laboratory ID: ");
                        int id = scanner.nextInt();
                        scanner.nextLine();
                        readLaboratoryUserCase.execute(id).ifPresentOrElse(
                            laboratory -> {
                                System.out.println("Laboratory Info:");
                                System.out.println("ID: " + laboratory.getId());
                                System.out.println("Name: " + laboratory.getNameLab());
                                System.out.println("City Code: " + laboratory.getCodeCityReg());
                            },
                            () -> System.out.println("Laboratory not found!")
                        );
                    } catch (Exception e) {
                        System.out.println("An error occurred while reading the laboratory: " + e.getMessage());
                    }
                    break;
                case 3:
                    try {
                        System.out.println("\nEnter Laboratory ID to Update: ");
                        int id = scanner.nextInt();
                        scanner.nextLine();
                        Optional<Laboratory> laboratoryToUpdate = readLaboratoryUserCase.execute(id);
                        laboratoryToUpdate.ifPresentOrElse(
                            laboratory -> {
                                boolean continueUpdating = true;
                                while (continueUpdating) {
                                    System.out.println("\nSelect field to update:");
                                    System.out.println("1. Laboratory Name");
                                    System.out.println("2. City Code");
                                    System.out.println("0. Exit");

                                    System.out.print("Enter your choice: ");
                                    int updateChoice = scanner.nextInt();
                                    scanner.nextLine();
                                    switch (updateChoice) {
                                        case 1:
                                            System.out.println("Enter new Laboratory Name: ");
                                            String newName = scanner.nextLine();
                                            laboratory.setNameLab(newName);
                                            break;
                                        case 2:
                                            System.out.println("Enter new City Code: ");
                                            String newCityCode = scanner.nextLine();
                                            laboratory.setCodeCityReg(newCityCode);
                                            break;
                                        case 0:
                                            continueUpdating = false;
                                            break;
                                        default:
                                            System.out.println("Invalid choice. Please try again.");
                                            continue;
                                    }
                                    if (updateChoice != 0) {
                                        updateLaboratoryUserCase.execute(laboratory, id);
                                        System.out.println("Laboratory updated successfully!");
                                    }
                                }
                            },
                            () -> System.out.println("Laboratory not found!")
                        );
                    } catch (Exception e) {
                        System.out.println("An error occurred while updating the laboratory: " + e.getMessage());
                    }
                    break;
                case 4:
                    try {
                        System.out.println("\nEnter Laboratory ID to Delete: ");
                        int id = scanner.nextInt();
                        scanner.nextLine();

                        deleteLaboratoryUserCase.execute(id);

                        System.out.println("Laboratory deleted successfully!");
                    } catch (Exception e) {
                        System.out.println("An error occurred while deleting the laboratory: " + e.getMessage());
                    }
                    break;
                case 0:
                    System.out.println("Exiting...");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
            System.out.println();
        }

        scanner.close();
    }
}