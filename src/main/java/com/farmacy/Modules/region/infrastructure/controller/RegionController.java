package com.farmacy.Modules.region.infrastructure.controller;

import java.text.MessageFormat;
import java.util.Optional;
import java.util.Scanner;

import com.farmacy.Console.GeneralControler;
import com.farmacy.Modules.region.aplication.CreateRegionUserCase;
import com.farmacy.Modules.region.aplication.DeleteRegionUserCase;
import com.farmacy.Modules.region.aplication.ReadRegionUserCase;
import com.farmacy.Modules.region.aplication.UpdateRegionUserCase;
import com.farmacy.Modules.region.domain.entity.Region;

public class RegionController {
    private CreateRegionUserCase createRegionUserCase;
    private ReadRegionUserCase readRegionUserCase;
    private UpdateRegionUserCase updateRegionUserCase;
    private DeleteRegionUserCase deleteRegionUserCase;
    Object[] options = {"Create Region", "Read Region", "Update Region", "Delete Region"};
    int choice = -1;

    public RegionController(CreateRegionUserCase createRegionUserCase, ReadRegionUserCase readRegionUserCase, UpdateRegionUserCase updateRegionUserCase, DeleteRegionUserCase deleteRegionUserCase) {
        this.createRegionUserCase = createRegionUserCase;
        this.readRegionUserCase = readRegionUserCase;
        this.updateRegionUserCase = updateRegionUserCase;
        this.deleteRegionUserCase = deleteRegionUserCase;
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
                        System.out.println("\nEnter Region Code: ");
                        String code = scanner.nextLine();
                        System.out.println("Enter Region Name: ");
                        String name = scanner.nextLine();
                        System.out.println("Enter Country Code: ");
                        String countryCode = scanner.nextLine();
                        Region region = new Region();
                        region.setCodeReg(code);
                        region.setNameReg(name);
                        region.setCodeCountry(countryCode);

                        createRegionUserCase.execute(region);

                        System.out.println("Region created successfully!");
                    } catch (Exception e) {
                        System.out.println("An error occurred while creating the region: " + e.getMessage());
                    }
                    break;
                case 2:
                    try {
                        System.out.println("\nEnter Region Code: ");
                        String code = scanner.nextLine();
                        readRegionUserCase.execute(code).ifPresentOrElse(
                            regionFound -> {
                                System.out.println("\nRegion Info: ");
                                System.out.println(MessageFormat.format("Code: {0}", regionFound.getCodeReg()));
                                System.out.println(MessageFormat.format("Name: {0}", regionFound.getNameReg()));
                                System.out.println(MessageFormat.format("Country Code: {0}", regionFound.getCodeCountry()));
                            },
                            () -> System.out.println("Region not found!")
                        );
                    } catch (Exception e) {
                        System.out.println("An error occurred while reading the region: " + e.getMessage());
                    }
                    break;
                case 3:
                    try {
                        System.out.println("\nEnter Region Code to Update: ");
                        String code = scanner.nextLine();
                        Optional<Region> regionToUpdate = readRegionUserCase.execute(code);
                        regionToUpdate.ifPresentOrElse(
                            region -> {
                                boolean continueUpdating = true;
                                while (continueUpdating) {
                                    System.out.println("\nSelect field to update:");
                                    System.out.println("1. Region Name");
                                    System.out.println("2. Region Code");
                                    System.out.println("3. Country Code");
                                    System.out.println("0. Exit");

                                    System.out.print("Enter your choice: ");
                                    int choice = scanner.nextInt();
                                    scanner.nextLine();
                                    switch (choice) {
                                        case 1:
                                            System.out.println("\nEnter new Region Name: ");
                                            String newName = scanner.nextLine();
                                            region.setNameReg(newName);
                                            break;
                                        case 2:
                                            System.out.println("\nEnter new Region Name: ");
                                            String newCode = scanner.nextLine();
                                            region.setCodeReg(newCode);
                                            break;
                                        case 3:
                                            System.out.println("\nEnter new Country Code: ");
                                            String newCountryCode = scanner.nextLine();
                                            region.setCodeCountry(newCountryCode);
                                            break;
                                        case 0:
                                            continueUpdating = false;
                                            break;

                                        default:
                                            System.out.println("Invalid choice. Please try again.");
                                            continue;
                                    }
                                    if (choice != 0) {
                                        updateRegionUserCase.execute(region, code);
                                        System.out.println("Region updated successfully!");
                                    }
                                }
                            },
                            () -> System.out.println("Region not found!")
                        );
                    } catch (Exception e) {
                        System.out.println("An error occurred while updating the region: " + e.getMessage());
                    }
                    break;
                case 4:
                    try {
                        System.out.println("\nEnter Region Code to Delete: ");
                        String code = scanner.nextLine();
                        deleteRegionUserCase.execute(code);
                        System.out.println("Region deleted successfully!");
                    } catch (Exception e) {
                        System.out.println("An error occurred while deleting the region: " + e.getMessage());
                    }
                    break;
                case 0:
                    GeneralControler gc = new GeneralControler();
                    gc.run();
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }

            System.out.println(); 
        }

        scanner.close();
    }
}
