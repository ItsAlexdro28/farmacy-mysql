package com.farmacy.Modules.farmacymedicine.infrastructure.controller;

import java.util.Optional;
import java.util.Scanner;

import com.farmacy.Console.GeneralControler;
import com.farmacy.Modules.farmacymedicine.aplication.CreateFarmacyMedicineUserCase;
import com.farmacy.Modules.farmacymedicine.aplication.DeleteFarmacyMedicineUserCase;
import com.farmacy.Modules.farmacymedicine.aplication.ReadFarmacyMedicineUserCase;
import com.farmacy.Modules.farmacymedicine.aplication.UpdateFarmacyMedicineUserCase;
import com.farmacy.Modules.farmacymedicine.domain.entity.FarmacyMedicine;

public class FarmacyMedicineController {
    private CreateFarmacyMedicineUserCase createFarmacyMedicineUserCase;
    private ReadFarmacyMedicineUserCase readFarmacyMedicineUserCase;
    private UpdateFarmacyMedicineUserCase updateFarmacyMedicineUserCase;
    private DeleteFarmacyMedicineUserCase deleteFarmacyMedicineUserCase;
    Object[] options = { "Create Farmacy-Medicine Entry", "Read Farmacy-Medicine Entry", "Update Farmacy-Medicine Entry", "Delete Farmacy-Medicine Entry" };
    int choice = -1;

    public FarmacyMedicineController(CreateFarmacyMedicineUserCase createFarmacyMedicineUserCase, ReadFarmacyMedicineUserCase readFarmacyMedicineUserCase,
            UpdateFarmacyMedicineUserCase updateFarmacyMedicineUserCase, DeleteFarmacyMedicineUserCase deleteFarmacyMedicineUserCase) {
        this.createFarmacyMedicineUserCase = createFarmacyMedicineUserCase;
        this.readFarmacyMedicineUserCase = readFarmacyMedicineUserCase;
        this.updateFarmacyMedicineUserCase = updateFarmacyMedicineUserCase;
        this.deleteFarmacyMedicineUserCase = deleteFarmacyMedicineUserCase;
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
                        System.out.println("\nEnter Farmacy ID: ");
                        int idFarmacy = scanner.nextInt();
                        System.out.println("Enter Medicine ID: ");
                        int idMedicineFarm = scanner.nextInt();
                        System.out.println("Enter Price: ");
                        float price = scanner.nextFloat();

                        FarmacyMedicine farmacyMedicine = new FarmacyMedicine(idFarmacy, idMedicineFarm, price);
                        createFarmacyMedicineUserCase.execute(farmacyMedicine);

                        System.out.println("Farmacy-Medicine entry created successfully!");
                    } catch (Exception e) {
                        System.out.println("An error occurred while creating the farmacy-medicine entry: " + e.getMessage());
                    }
                    break;
                case 2:
                    try {
                        System.out.println("\nEnter Farmacy ID: ");
                        int idFarmacy = scanner.nextInt();
                        System.out.println("Enter Medicine ID: ");
                        int idMedicineFarm = scanner.nextInt();
                        Optional<FarmacyMedicine> farmacyMedicineOpt = readFarmacyMedicineUserCase.execute(idFarmacy, idMedicineFarm);
                        farmacyMedicineOpt.ifPresentOrElse(
                            farmacyMedicine -> {
                                System.out.println("\nFarmacy-Medicine Info: ");
                                System.out.println("Farmacy ID: " + farmacyMedicine.getIdFarmacy());
                                System.out.println("Medicine ID: " + farmacyMedicine.getIdMedicineFarm());
                                System.out.println("Price: " + farmacyMedicine.getPrice());
                            },
                            () -> System.out.println("Farmacy-Medicine entry not found!")
                        );
                    } catch (Exception e) {
                        System.out.println("An error occurred while reading the farmacy-medicine entry: " + e.getMessage());
                    }
                    break;
                case 3:
                    try {
                        System.out.println("\nEnter Farmacy ID to Update: ");
                        int idFarmacy = scanner.nextInt();
                        System.out.println("Enter Medicine ID to Update: ");
                        int idMedicineFarm = scanner.nextInt();
                        Optional<FarmacyMedicine> farmacyMedicineOpt = readFarmacyMedicineUserCase.execute(idFarmacy, idMedicineFarm);
                        farmacyMedicineOpt.ifPresentOrElse(
                            farmacyMedicine -> {
                                System.out.println("\nEnter new Price: ");
                                float newPrice = scanner.nextFloat();
                                farmacyMedicine.setPrice(newPrice);

                                updateFarmacyMedicineUserCase.execute(farmacyMedicine, idFarmacy, idMedicineFarm);
                                System.out.println("Farmacy-Medicine entry updated successfully!");
                            },
                            () -> System.out.println("Farmacy-Medicine entry not found!")
                        );
                    } catch (Exception e) {
                        System.out.println("An error occurred while updating the farmacy-medicine entry: " + e.getMessage());
                    }
                    break;
                case 4:
                    try {
                        System.out.println("\nEnter Farmacy ID to Delete: ");
                        int idFarmacy = scanner.nextInt();
                        System.out.println("Enter Medicine ID to Delete: ");
                        int idMedicineFarm = scanner.nextInt();
                        deleteFarmacyMedicineUserCase.execute(idFarmacy, idMedicineFarm);
                        System.out.println("Farmacy-Medicine entry deleted successfully!");
                    } catch (Exception e) {
                        System.out.println("An error occurred while deleting the farmacy-medicine entry: " + e.getMessage());
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