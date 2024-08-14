package com.farmacy.Modules.medicine.infrastructure.controller;

import java.util.Optional;
import java.util.Scanner;

import com.farmacy.Console.GeneralControler;
import com.farmacy.Modules.medicine.aplication.CreateMedicineUserCase;
import com.farmacy.Modules.medicine.aplication.DeleteMedicineUserCase;
import com.farmacy.Modules.medicine.aplication.ReadMedicineUserCase;
import com.farmacy.Modules.medicine.aplication.UpdateMedicineUserCase;
import com.farmacy.Modules.medicine.domain.entity.Medicine;

public class MedicineController {
    private CreateMedicineUserCase createMedicineUserCase;
    private ReadMedicineUserCase readMedicineUserCase;
    private UpdateMedicineUserCase updateMedicineUserCase;
    private DeleteMedicineUserCase deleteMedicineUserCase;
    Object[] options = { "Create Medicine", "Read Medicine", "Update Medicine", "Delete Medicine" };
    int choice = -1;

    public MedicineController(CreateMedicineUserCase createMedicineUserCase, ReadMedicineUserCase readMedicineUserCase,
            UpdateMedicineUserCase updateMedicineUserCase, DeleteMedicineUserCase deleteMedicineUserCase) {
        this.createMedicineUserCase = createMedicineUserCase;
        this.readMedicineUserCase = readMedicineUserCase;
        this.updateMedicineUserCase = updateMedicineUserCase;
        this.deleteMedicineUserCase = deleteMedicineUserCase;
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
                        System.out.println("\nEnter Proceedings: ");
                        String proceedings = scanner.nextLine();
                        System.out.println("Enter Medicine Name: ");
                        String nameMedicine = scanner.nextLine();
                        System.out.println("Enter Health Register: ");
                        String healthRegister = scanner.nextLine();
                        System.out.println("Enter Description: ");
                        String description = scanner.nextLine();
                        System.out.println("Enter Short Description: ");
                        String descriptionShort = scanner.nextLine();
                        System.out.println("Enter Mode Administration Code: ");
                        int codeModeAdmin = scanner.nextInt();
                        System.out.println("Enter Active Principle Code: ");
                        int codeAp = scanner.nextInt();
                        System.out.println("Enter Unit Measurement Code: ");
                        int codeUm = scanner.nextInt();
                        scanner.nextLine(); 
                        System.out.println("Enter Role Name: ");
                        String nameRol = scanner.nextLine();
                        System.out.println("Enter Laboratory Code: ");
                        int codeLab = scanner.nextInt();

                        Medicine medicine = new Medicine(0, proceedings, nameMedicine, healthRegister, description, descriptionShort, codeModeAdmin, codeAp, codeUm, nameRol, codeLab);
                        createMedicineUserCase.execute(medicine);

                        System.out.println("Medicine created successfully!");
                    } catch (Exception e) {
                        System.out.println("An error occurred while creating the medicine: " + e.getMessage());
                    }
                    break;
                case 2:
                    try {
                        System.out.println("\nEnter Medicine ID: ");
                        int id = scanner.nextInt();
                        scanner.nextLine(); // consume the newline
                        readMedicineUserCase.execute(id).ifPresentOrElse(
                            medicineFound -> {
                                System.out.println("\nMedicine Info: ");
                                System.out.println("ID: " + medicineFound.getId());
                                System.out.println("Proceedings: " + medicineFound.getProceedings());
                                System.out.println("Name: " + medicineFound.getNameMedicine());
                                System.out.println("Health Register: " + medicineFound.getHealthRegister());
                                System.out.println("Description: " + medicineFound.getDescription());
                                System.out.println("Short Description: " + medicineFound.getDescriptionShort());
                                System.out.println("Mode Administration Code: " + medicineFound.getCodeModeAdmin());
                                System.out.println("Active Principle Code: " + medicineFound.getCodeAp());
                                System.out.println("Unit Measurement Code: " + medicineFound.getCodeUm());
                                System.out.println("Role Name: " + medicineFound.getNameRol());
                                System.out.println("Laboratory Code: " + medicineFound.getCodeLab());
                            },
                            () -> System.out.println("Medicine not found!")
                        );
                    } catch (Exception e) {
                        System.out.println("An error occurred while reading the medicine: " + e.getMessage());
                    }
                    break;
                case 3:
                    try {
                        System.out.println("\nEnter Medicine ID to Update: ");
                        int id = scanner.nextInt();
                        scanner.nextLine(); // consume the newline
                        Optional<Medicine> medicineToUpdate = readMedicineUserCase.execute(id);
                        medicineToUpdate.ifPresentOrElse(
                            medicine -> {
                                boolean continueUpdating = true;
                                while (continueUpdating) {
                                    System.out.println("\nWhich field would you like to update?");
                                    System.out.println("1. Proceedings");
                                    System.out.println("2. Name");
                                    System.out.println("3. Health Register");
                                    System.out.println("4. Description");
                                    System.out.println("5. Short Description");
                                    System.out.println("6. Mode Administration Code");
                                    System.out.println("7. Active Principle Code");
                                    System.out.println("8. Unit Measurement Code");
                                    System.out.println("9. Role Name");
                                    System.out.println("10. Laboratory Code");
                                    System.out.println("0. Exit");

                                    System.out.print("Enter your choice: ");
                                    int fieldChoice = scanner.nextInt();
                                    scanner.nextLine(); // consume the newline
                                    switch (fieldChoice) {
                                        case 1:
                                            System.out.println("\nEnter new Proceedings: ");
                                            String newProceedings = scanner.nextLine();
                                            medicine.setProceedings(newProceedings);
                                            break;
                                        case 2:
                                            System.out.println("\nEnter new Name: ");
                                            String newName = scanner.nextLine();
                                            medicine.setNameMedicine(newName);
                                            break;
                                        case 3:
                                            System.out.println("\nEnter new Health Register: ");
                                            String newHealthRegister = scanner.nextLine();
                                            medicine.setHealthRegister(newHealthRegister);
                                            break;
                                        case 4:
                                            System.out.println("\nEnter new Description: ");
                                            String newDescription = scanner.nextLine();
                                            medicine.setDescription(newDescription);
                                            break;
                                        case 5:
                                            System.out.println("\nEnter new Short Description: ");
                                            String newDescriptionShort = scanner.nextLine();
                                            medicine.setDescriptionShort(newDescriptionShort);
                                            break;
                                        case 6:
                                            System.out.println("\nEnter new Mode Administration Code: ");
                                            int newCodeModeAdmin = scanner.nextInt();
                                            medicine.setCodeModeAdmin(newCodeModeAdmin);
                                            break;
                                        case 7:
                                            System.out.println("\nEnter new Active Principle Code: ");
                                            int newCodeAp = scanner.nextInt();
                                            medicine.setCodeAp(newCodeAp);
                                            break;
                                        case 8:
                                            System.out.println("\nEnter new Unit Measurement Code: ");
                                            int newCodeUm = scanner.nextInt();
                                            medicine.setCodeUm(newCodeUm);
                                            break;
                                        case 9:
                                            System.out.println("\nEnter new Role Name: ");
                                            String newNameRol = scanner.nextLine();
                                            medicine.setNameRol(newNameRol);
                                            break;
                                        case 10:
                                            System.out.println("\nEnter new Laboratory Code: ");
                                            int newCodeLab = scanner.nextInt();
                                            medicine.setCodeLab(newCodeLab);
                                            break;
                                        case 0:
                                            continueUpdating = false;
                                            break;
                                        default:
                                            System.out.println("Invalid choice. Please try again.");
                                            continue;
                                    }
                                    if (fieldChoice != 0) {
                                        updateMedicineUserCase.execute(medicine, id);
                                        System.out.println("Medicine updated successfully!");
                                    }
                                }
                            },
                            () -> System.out.println("Medicine not found!")
                        );
                    } catch (Exception e) {
                        System.out.println("An error occurred while updating the medicine: " + e.getMessage());
                    }
                    break;
                case 4:
                    try {
                        System.out.println("\nEnter Medicine ID to Delete: ");
                        int id = scanner.nextInt();
                        scanner.nextLine(); // consume the newline
                        deleteMedicineUserCase.execute(id);
                        System.out.println("Medicine deleted successfully!");
                    } catch (Exception e) {
                        System.out.println("An error occurred while deleting the medicine: " + e.getMessage());
                    }
                    break;
                case 0:
                    GeneralControler generalControler = new GeneralControler();
                    generalControler.run();
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
                    break;
            }
            System.out.println();
        }
        scanner.close();
    }
}
