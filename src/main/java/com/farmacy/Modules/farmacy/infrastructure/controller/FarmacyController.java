package com.farmacy.Modules.farmacy.infrastructure.controller;

import java.util.Optional;
import java.util.Scanner;

import com.farmacy.Console.GeneralControler;
import com.farmacy.Modules.farmacy.aplication.CreateFarmacyUserCase;
import com.farmacy.Modules.farmacy.aplication.DeleteFarmacyUserCase;
import com.farmacy.Modules.farmacy.aplication.ReadFarmacyUserCase;
import com.farmacy.Modules.farmacy.aplication.UpdateFarmacyUserCase;
import com.farmacy.Modules.farmacy.domain.entity.Farmacy;

public class FarmacyController {
    private CreateFarmacyUserCase createFarmacyUserCase;
    private ReadFarmacyUserCase readFarmacyUserCase;
    private UpdateFarmacyUserCase updateFarmacyUserCase;
    private DeleteFarmacyUserCase deleteFarmacyUserCase;
    Object[] options = { "Create Farmacy", "Read Farmacy", "Update Farmacy", "Delete Farmacy" };
    int choice = -1;

    public FarmacyController(CreateFarmacyUserCase createFarmacyUserCase, ReadFarmacyUserCase readFarmacyUserCase,
            UpdateFarmacyUserCase updateFarmacyUserCase, DeleteFarmacyUserCase deleteFarmacyUserCase) {
        this.createFarmacyUserCase = createFarmacyUserCase;
        this.readFarmacyUserCase = readFarmacyUserCase;
        this.updateFarmacyUserCase = updateFarmacyUserCase;
        this.deleteFarmacyUserCase = deleteFarmacyUserCase;
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
                        System.out.println("\nEnter Farmacy Name: ");
                        String name = scanner.nextLine();
                        System.out.println("Enter Farmacy Address: ");
                        String address = scanner.nextLine();
                        System.out.println("Enter Farmacy Longitude: ");
                        float longitude = scanner.nextFloat();
                        System.out.println("Enter Farmacy Latitude: ");
                        float latitude = scanner.nextFloat();
                        scanner.nextLine(); // consume the newline
                        System.out.println("Enter Farmacy City Code: ");
                        String cityCode = scanner.nextLine();
                        System.out.println("Enter Farmacy Logo: ");
                        String logo = scanner.nextLine();

                        Farmacy farmacy = new Farmacy(0, name, address, longitude, latitude, cityCode, logo);
                        createFarmacyUserCase.execute(farmacy);

                        System.out.println("Farmacy created successfully!");
                    } catch (Exception e) {
                        System.out.println("An error occurred while creating the farmacy: " + e.getMessage());
                    }
                    break;
                case 2:
                    try {
                        System.out.println("\nEnter Farmacy ID: ");
                        int id = scanner.nextInt();
                        scanner.nextLine(); // consume the newline
                        readFarmacyUserCase.execute(id).ifPresentOrElse(
                            farmacyFound -> {
                                System.out.println("\nFarmacy Info: ");
                                System.out.println("ID: " + farmacyFound.getIdFarmacy());
                                System.out.println("Name: " + farmacyFound.getNameFarmacy());
                                System.out.println("Address: " + farmacyFound.getAddressFarmacy());
                                System.out.println("Longitude: " + farmacyFound.getLongitude());
                                System.out.println("Latitude: " + farmacyFound.getLatitude());
                                System.out.println("City Code: " + farmacyFound.getCodeCityFarm());
                                System.out.println("Logo: " + farmacyFound.getLogoFarmacy());
                            },
                            () -> System.out.println("Farmacy not found!")
                        );
                    } catch (Exception e) {
                        System.out.println("An error occurred while reading the farmacy: " + e.getMessage());
                    }
                    break;
                case 3:
                    try {
                        System.out.println("\nEnter Farmacy ID to Update: ");
                        int id = scanner.nextInt();
                        scanner.nextLine(); // consume the newline
                        Optional<Farmacy> farmacyToUpdate = readFarmacyUserCase.execute(id);
                        farmacyToUpdate.ifPresentOrElse(
                            farmacy -> {
                                boolean continueUpdating = true;
                                while (continueUpdating) {
                                    System.out.println("\nWhich field would you like to update?");
                                    System.out.println("1. Name");
                                    System.out.println("2. Address");
                                    System.out.println("3. Longitude");
                                    System.out.println("4. Latitude");
                                    System.out.println("5. City Code");
                                    System.out.println("6. Logo");
                                    System.out.println("0. Exit");

                                    System.out.print("Enter your choice: ");
                                    int fieldChoice = scanner.nextInt();
                                    scanner.nextLine(); // consume the newline
                                    switch (fieldChoice) {
                                        case 1:
                                            System.out.println("\nEnter new Name: ");
                                            String newName = scanner.nextLine();
                                            farmacy.setNameFarmacy(newName);
                                            break;
                                        case 2:
                                            System.out.println("\nEnter new Address: ");
                                            String newAddress = scanner.nextLine();
                                            farmacy.setAddressFarmacy(newAddress);
                                            break;
                                        case 3:
                                            System.out.println("\nEnter new Longitude: ");
                                            float newLongitude = scanner.nextFloat();
                                            farmacy.setLongitude(newLongitude);
                                            break;
                                        case 4:
                                            System.out.println("\nEnter new Latitude: ");
                                            float newLatitude = scanner.nextFloat();
                                            farmacy.setLatitude(newLatitude);
                                            break;
                                        case 5:
                                            System.out.println("\nEnter new City Code: ");
                                            String newCityCode = scanner.nextLine();
                                            farmacy.setCodeCityFarm(newCityCode);
                                            break;
                                        case 6:
                                            System.out.println("\nEnter new Logo: ");
                                            String newLogo = scanner.nextLine();
                                            farmacy.setLogoFarmacy(newLogo);
                                            break;
                                        case 0:
                                            continueUpdating = false;
                                            break;
                                        default:
                                            System.out.println("Invalid choice. Please try again.");
                                            continue;
                                    }
                                    if (fieldChoice != 0) {
                                        updateFarmacyUserCase.execute(farmacy, id);
                                        System.out.println("Farmacy updated successfully!");
                                    }
                                }
                            },
                            () -> System.out.println("Farmacy not found!")
                        );
                    } catch (Exception e) {
                        System.out.println("An error occurred while updating the farmacy: " + e.getMessage());
                    }
                    break;
                case 4:
                    try {
                        System.out.println("\nEnter Farmacy ID to Delete: ");
                        int id = scanner.nextInt();
                        scanner.nextLine(); // consume the newline
                        deleteFarmacyUserCase.execute(id);
                        System.out.println("Farmacy deleted successfully!");
                    } catch (Exception e) {
                        System.out.println("An error occurred while deleting the farmacy: " + e.getMessage());
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
