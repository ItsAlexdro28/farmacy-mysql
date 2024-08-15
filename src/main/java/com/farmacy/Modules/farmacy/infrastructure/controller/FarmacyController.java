package com.farmacy.Modules.farmacy.infrastructure.controller;

import java.util.Optional;

import com.farmacy.Console.GeneralControler;
import com.farmacy.Console.Util;
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
        while (choice != 0) {
            System.out.println("Please select an option:");
            for (int i = 0; i < options.length; i++) {
                System.out.println((i + 1) + ". " + options[i]);
            }
            System.out.println("0. Exit");

            choice = Util.getIntInput("\nEnter your choice: ");

            switch (choice) {
                case 1:
                    try {
                        String name = Util.getStringInput("\nEnter Farmacy Name: ");
                        String address = Util.getStringInput("Enter Farmacy Address: ");
                        float longitude = Util.getFloatInput("Enter Farmacy Longitude: ");
                        float latitude = Util.getFloatInput("Enter Farmacy Latitude: ");
                        String cityCode = Util.getStringInput("Enter Farmacy City Code: ");
                        String logo = Util.getStringInput("Enter Farmacy Logo: ");
                        Farmacy farmacy = new Farmacy(0, name, address, longitude, latitude, cityCode, logo);
                        createFarmacyUserCase.execute(farmacy);

                        System.out.println("Farmacy created successfully!");
                    } catch (Exception e) {
                        System.out.println("An error occurred while creating the farmacy: " + e.getMessage());
                    }
                    break;
                case 2:
                    try {
                        int id = Util.getIntInput("\nEnter Farmacy ID: ");
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
                        int id = Util.getIntInput("\nEnter Farmacy ID to Update: ");
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

                                    int fieldChoice = Util.getIntInput("Enter your choice: ");
                                    switch (fieldChoice) {
                                        case 1:
                                            String newName = Util.getStringInput("\nEnter new Name: ");
                                            farmacy.setNameFarmacy(newName);
                                            break;
                                        case 2:
                                            String newAddress = Util.getStringInput("\nEnter new Address: ");
                                            farmacy.setAddressFarmacy(newAddress);
                                            break;
                                        case 3:
                                            float newLongitude = Util.getFloatInput("\nEnter new Longitude: ");
                                            farmacy.setLongitude(newLongitude);
                                            break;
                                        case 4:
                                            float newLatitude = Util.getFloatInput("\nEnter new Latitude: ");
                                            farmacy.setLatitude(newLatitude);
                                            break;
                                        case 5:
                                            String newCityCode = Util.getStringInput("\nEnter new City Code: ");
                                            farmacy.setCodeCityFarm(newCityCode);
                                            break;
                                        case 6:
                                            String newLogo = Util.getStringInput("\nEnter new Logo: ");
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
                        int id = Util.getIntInput("\nEnter Farmacy ID to Delete: ");
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
    }
}
