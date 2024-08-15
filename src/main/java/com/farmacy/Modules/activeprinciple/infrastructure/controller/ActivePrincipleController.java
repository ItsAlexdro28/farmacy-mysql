package com.farmacy.Modules.activeprinciple.infrastructure.controller;


import java.util.Optional;

import com.farmacy.Console.GeneralControler;
import com.farmacy.Console.Util;
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
    Object[] options = {"Create Active Principle", "Read Active Principle", "Update Active Principle", "Delete Active Principle"};
    int choice = -1;

    public ActivePrincipleController(CreateActivePrincipleUserCase createActivePrincipleUserCase, ReadActivePrincipleUserCase readActivePrincipleUserCase, UpdateActivePrincipleUserCase updateActivePrincipleUserCase, DeleteActivePrincipleUserCase deleteActivePrincipleUserCase) {
        this.createActivePrincipleUserCase = createActivePrincipleUserCase;
        this.readActivePrincipleUserCase = readActivePrincipleUserCase;
        this.updateActivePrincipleUserCase = updateActivePrincipleUserCase;
        this.deleteActivePrincipleUserCase = deleteActivePrincipleUserCase;
    }

    public void run() {

        while (choice != 0) {
            System.out.println("\nPlease select an option:");
            for (int i = 0; i < options.length; i++) {
                System.out.println((i + 1) + ". " + options[i]);
            }
            System.out.println("0. Exit");
            
            choice = Util.getIntInput("Enter your choice: ");

            switch (choice) {
                case 1:
                    try {
                        String name = Util.getStringInput("\nEnter Active Principle Name: ");
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
                        int id = Util.getIntInput("\nEnter Active Principle ID: ");
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
                        int id = Util.getIntInput("\nEnter Active Principle ID to Update: ");
                        Optional<ActivePrinciple> activePrincipleToUpdate = readActivePrincipleUserCase.execute(id);
                        activePrincipleToUpdate.ifPresentOrElse(
                            activePrinciple -> {
                                boolean continueUpdating = true;
                                while (continueUpdating) {
                                    System.out.println("\nSelect field to update:");
                                    System.out.println("1. Active Principle Name");
                                    System.out.println("0. Exit");

                                    int updateChoice = Util.getIntInput("Enter your choice: ");
                                    switch (updateChoice) {
                                        case 1:
                                            String newName = Util.getStringInput("Enter new Active Principle Name: ");
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
                        int id = Util.getIntInput("\nEnter Active Principle ID to Delete: ");
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
    }
}