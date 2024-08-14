package com.farmacy.Modules.modeadministration.infrastructure.controller;
import java.text.MessageFormat;
import java.util.Optional;
import java.util.Scanner;

import com.farmacy.Console.GeneralControler;
import com.farmacy.Modules.modeadministration.aplication.CreateModeAdministrationUserCase;
import com.farmacy.Modules.modeadministration.aplication.DeleteModeAdministrationUserCase;
import com.farmacy.Modules.modeadministration.aplication.ReadModeAdministrationUserCase;
import com.farmacy.Modules.modeadministration.aplication.UpdateModeAdministrationUserCase;
import com.farmacy.Modules.modeadministration.domain.entity.ModeAdministration;

public class ModeAdministrationController {
    private final CreateModeAdministrationUserCase createModeAdministrationUserCase;
    private final ReadModeAdministrationUserCase readModeAdministrationUserCase;
    private final UpdateModeAdministrationUserCase updateModeAdministrationUserCase;
    private final DeleteModeAdministrationUserCase deleteModeAdministrationUserCase;
    private final Object[] options = {"Create Mode Administration", "Read Mode Administration", "Update Mode Administration", "Delete Mode Administration"};
    private int choice = -1;

    public ModeAdministrationController(
        CreateModeAdministrationUserCase createModeAdministrationUserCase,
        ReadModeAdministrationUserCase readModeAdministrationUserCase,
        UpdateModeAdministrationUserCase updateModeAdministrationUserCase,
        DeleteModeAdministrationUserCase deleteModeAdministrationUserCase
    ) {
        this.createModeAdministrationUserCase = createModeAdministrationUserCase;
        this.readModeAdministrationUserCase = readModeAdministrationUserCase;
        this.updateModeAdministrationUserCase = updateModeAdministrationUserCase;
        this.deleteModeAdministrationUserCase = deleteModeAdministrationUserCase;
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
                        System.out.println("\nEnter Mode Description: ");
                        String description = scanner.nextLine();
                        ModeAdministration modeAdministration = new ModeAdministration();
                        modeAdministration.setDescriptionMode(description);

                        createModeAdministrationUserCase.execute(modeAdministration);

                        System.out.println("Mode Administration created successfully!");
                    } catch (Exception e) {
                        System.out.println("An error occurred while creating the mode administration: " + e.getMessage());
                    }
                    break;
                case 2:
                    try {
                        System.out.println("\nEnter Mode ID: ");
                        int id = scanner.nextInt();
                        readModeAdministrationUserCase.execute(id).ifPresentOrElse(
                            modeAdministrationFound -> {
                                System.out.println("\nMode Administration Info: ");
                                System.out.println(MessageFormat.format("ID: {0}", modeAdministrationFound.getId()));
                                System.out.println(MessageFormat.format("Description: {0}", modeAdministrationFound.getDescriptionMode()));
                            },
                            () -> System.out.println("Mode Administration not found!")
                        );
                    } catch (Exception e) {
                        System.out.println("An error occurred while reading the mode administration: " + e.getMessage());
                    }
                    break;
                case 3:
                    try {
                        System.out.println("\nEnter Mode ID to Update: ");
                        int id = scanner.nextInt();
                        scanner.nextLine();
                        Optional<ModeAdministration> modeAdministrationToUpdate = readModeAdministrationUserCase.execute(id);

                        modeAdministrationToUpdate.ifPresentOrElse(
                            modeAdministration -> {
                                boolean continueUpdating = true;
                                while (continueUpdating) {
                                    System.out.println("\nSelect field to update:");
                                    System.out.println("1. Mode Description");
                                    System.out.println("0. Exit");

                                    System.out.print("Enter your choice: ");
                                    int updateChoice = scanner.nextInt();
                                    scanner.nextLine();
                                    switch (updateChoice) {
                                        case 1:
                                            System.out.println("\nEnter new Mode Description: ");
                                            String newDescription = scanner.nextLine();
                                            modeAdministration.setDescriptionMode(newDescription);
                                            break;
                                        case 0:
                                            continueUpdating = false;
                                            break;
                                        default:
                                            System.out.println("Invalid choice. Please try again.");
                                            continue;
                                    }
                                    if (updateChoice != 0) {
                                        updateModeAdministrationUserCase.execute(modeAdministration, id);
                                        System.out.println("Mode Administration updated successfully!");
                                    }
                                }
                            },
                            () -> System.out.println("Mode Administration not found!")
                        );
                    } catch (Exception e) {
                        System.out.println("An error occurred while updating the mode administration: " + e.getMessage());
                    }
                    break;
                case 4:
                    try {
                        System.out.println("\nEnter Mode ID to Delete: ");
                        int id = scanner.nextInt();
                        deleteModeAdministrationUserCase.execute(id);
                        System.out.println("Mode Administration deleted successfully!");
                    } catch (Exception e) {
                        System.out.println("An error occurred while deleting the mode administration: " + e.getMessage());
                    }
                    break;
                case 0:
                    GeneralControler gc = new GeneralControler();
                    gc.run();
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
        scanner.close();
    }
}