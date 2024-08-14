package com.farmacy.Modules.activeprinciple.infrastructure.controller;


import java.util.Optional;
import java.util.Scanner;

import com.farmacy.Console.GeneralControler;
import com.farmacy.Modules.activeprinciple.aplication.CreateActivePrincipleUserCase;
import com.farmacy.Modules.activeprinciple.aplication.DeleteActivePrincipleUserCase;
import com.farmacy.Modules.activeprinciple.aplication.ReadActivePrincipleUserCase;
import com.farmacy.Modules.activeprinciple.aplication.UpdateActivePrincipleUserCase;
import com.farmacy.Modules.activeprinciple.domain.entity.ActivePrinciple;

public class ActivePrincipleController {
    private final CreateActivePrincipleUserCase createActivePrincipleUserCase;
    private final ReadActivePrincipleUserCase readActivePrincipleUserCase;
    private final UpdateActivePrincipleUserCase updateActivePrincipleUserCase;
    private final DeleteActivePrincipleUserCase deleteActivePrincipleUserCase;

    public ActivePrincipleController(CreateActivePrincipleUserCase createActivePrincipleUserCase, ReadActivePrincipleUserCase readActivePrincipleUserCase, UpdateActivePrincipleUserCase updateActivePrincipleUserCase, DeleteActivePrincipleUserCase deleteActivePrincipleUserCase) {
        this.createActivePrincipleUserCase = createActivePrincipleUserCase;
        this.readActivePrincipleUserCase = readActivePrincipleUserCase;
        this.updateActivePrincipleUserCase = updateActivePrincipleUserCase;
        this.deleteActivePrincipleUserCase = deleteActivePrincipleUserCase;
    }

    public void run() {
        Scanner scanner = new Scanner(System.in);
        int choice = -1;

        while (choice != 0) {
            System.out.println("\nPlease select an option:");
            System.out.println("1. Create Active Principle");
            System.out.println("2. Read Active Principle");
            System.out.println("3. Update Active Principle");
            System.out.println("4. Delete Active Principle");
            System.out.println("0. Exit");

            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    try {
                        System.out.println("\nEnter Active Principle Name: ");
                        String name = scanner.nextLine();
                        ActivePrinciple activePrinciple = new ActivePrinciple();
                        activePrinciple.setNameAp(name);

                        createActivePrincipleUserCase.execute(activePrinciple);

                        System.out.println("Active Principle created successfully!");
                    } catch (Exception e) {
                        System.out.println("An error occurred while creating the active principle: " + e.getMessage());
                    }
                    break;
                case 2:
                    try {
                        System.out.println("\nEnter Active Principle ID: ");
                        int id = scanner.nextInt();
                        scanner.nextLine();
                        readActivePrincipleUserCase.execute(id).ifPresentOrElse(
                            activePrinciple -> {
                                System.out.println("Active Principle Info:");
                                System.out.println("ID: " + activePrinciple.getIdAp());
                                System.out.println("Name: " + activePrinciple.getNameAp());
                            },
                            () -> System.out.println("Active Principle not found!")
                        );
                    } catch (Exception e) {
                        System.out.println("An error occurred while reading the active principle: " + e.getMessage());
                    }
                    break;
                case 3:
                    try {
                        System.out.println("\nEnter Active Principle ID to Update: ");
                        int id = scanner.nextInt();
                        scanner.nextLine();
                        Optional<ActivePrinciple> activePrincipleToUpdate = readActivePrincipleUserCase.execute(id);
                        activePrincipleToUpdate.ifPresentOrElse(
                            activePrinciple -> {
                                boolean continueUpdating = true;
                                while (continueUpdating) {
                                    System.out.println("\nSelect field to update:");
                                    System.out.println("1. Active Principle Name");
                                    System.out.println("0. Exit");

                                    System.out.print("Enter your choice: ");
                                    int updateChoice = scanner.nextInt();
                                    scanner.nextLine();
                                    switch (updateChoice) {
                                        case 1:
                                            System.out.println("Enter new Active Principle Name: ");
                                            String newName = scanner.nextLine();
                                            activePrinciple.setNameAp(newName);
                                            break;
                                        case 0:
                                            continueUpdating = false;
                                            break;
                                        default:
                                            System.out.println("Invalid choice. Please try again.");
                                            continue;
                                    }
                                    if (updateChoice != 0) {
                                        updateActivePrincipleUserCase.execute(activePrinciple, id);
                                        System.out.println("Active Principle updated successfully!");
                                    }
                                }
                            },
                            () -> System.out.println("Active Principle not found!")
                        );
                    } catch (Exception e) {
                        System.out.println("An error occurred while updating the active principle: " + e.getMessage());
                    }
                    break;
                case 4:
                    try {
                        System.out.println("\nEnter Active Principle ID to Delete: ");
                        int id = scanner.nextInt();
                        scanner.nextLine();

                        deleteActivePrincipleUserCase.execute(id);

                        System.out.println("Active Principle deleted successfully!");
                    } catch (Exception e) {
                        System.out.println("An error occurred while deleting the active principle: " + e.getMessage());
                    }
                    break;
                case 0:
                    GeneralControler generalControler = new GeneralControler();
                    generalControler.run();
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }

        scanner.close();
    }
}