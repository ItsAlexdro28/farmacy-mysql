package com.farmacy.Modules.city.infrastructure.controller;
import java.text.MessageFormat;
import java.util.Optional;
import java.util.Scanner;

import com.farmacy.Console.GeneralControler;
import com.farmacy.Modules.city.aplication.CreateCityUserCase;
import com.farmacy.Modules.city.aplication.DeleteCityUserCase;
import com.farmacy.Modules.city.aplication.ReadCityUserCase;
import com.farmacy.Modules.city.aplication.UpdateCityUserCase;
import com.farmacy.Modules.city.domain.entity.City;

public class CityController {
    private CreateCityUserCase createCityUserCase;
    private ReadCityUserCase readCityUserCase;
    private UpdateCityUserCase updateCityUserCase;
    private DeleteCityUserCase deleteCityUserCase;
    Object[] options = {"Create City", "Read City", "Update City", "Delete City"};
    int choice = -1;

    public CityController(CreateCityUserCase createCityUserCase, ReadCityUserCase readCityUserCase, UpdateCityUserCase updateCityUserCase, DeleteCityUserCase deleteCityUserCase) {
        this.createCityUserCase = createCityUserCase;
        this.readCityUserCase = readCityUserCase;
        this.updateCityUserCase = updateCityUserCase;
        this.deleteCityUserCase = deleteCityUserCase;
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
                        System.out.println("\nEnter City Code: ");
                        String code = scanner.nextLine();
                        System.out.println("Enter City Name: ");
                        String name = scanner.nextLine();
                        System.out.println("Enter Region Code: ");
                        String regionCode = scanner.nextLine();
                        City city = new City();
                        city.setCodeCity(code);
                        city.setNameCity(name);
                        city.setCodeReg(regionCode);

                        createCityUserCase.execute(city);

                        System.out.println("City created successfully!");
                    } catch (Exception e) {
                        System.out.println("An error occurred while creating the city: " + e.getMessage());
                    }
                    break;
                case 2:
                    try {
                        System.out.println("\nEnter City Code: ");
                        String code = scanner.nextLine();
                        readCityUserCase.execute(code).ifPresentOrElse(
                            cityFound -> {
                                System.out.println("\nCity Info: ");
                                System.out.println(MessageFormat.format("Code: {0}", cityFound.getCodeCity()));
                                System.out.println(MessageFormat.format("Name: {0}", cityFound.getNameCity()));
                                System.out.println(MessageFormat.format("Region Code: {0}", cityFound.getCodeReg()));
                            },
                            () -> System.out.println("City not found!")
                        );
                    } catch (Exception e) {
                        System.out.println("An error occurred while reading the city: " + e.getMessage());
                    }
                    break;
                case 3:
                    try {
                        System.out.println("\nEnter City Code to Update: ");
                        String code = scanner.nextLine();
                        Optional<City> cityToUpdate = readCityUserCase.execute(code);
                        cityToUpdate.ifPresentOrElse(
                            city -> {
                                boolean continueUpdating = true;
                                while (continueUpdating) {
                                    System.out.println("\nSelect field to update:");
                                    System.out.println("1. City Name");
                                    System.out.println("2. City Code");
                                    System.out.println("3. Region Code");
                                    System.out.println("0. Exit");

                                    System.out.print("Enter your choice: ");
                                    int choice = scanner.nextInt();
                                    scanner.nextLine();
                                    switch (choice) {
                                        case 1:
                                            System.out.println("\nEnter new City Name: ");
                                            String newName = scanner.nextLine();
                                            city.setNameCity(newName);
                                            break;
                                        case 2:
                                            System.out.println("\nEnter new City Code: ");
                                            String newCode = scanner.nextLine();
                                            city.setCodeCity(newCode);
                                            break;
                                        case 3:
                                            System.out.println("\nEnter new Region Code: ");
                                            String newRegionCode = scanner.nextLine();
                                            city.setCodeReg(newRegionCode);
                                            break;
                                        case 0:
                                            continueUpdating = false;
                                            break;

                                        default:
                                            System.out.println("Invalid choice. Please try again.");
                                            continue;
                                    }
                                    if (choice != 0) {
                                        updateCityUserCase.execute(city, code);
                                        System.out.println("City updated successfully!");
                                    }
                                }
                            },
                            () -> System.out.println("City not found!")
                        );
                    } catch (Exception e) {
                        System.out.println("An error occurred while updating the city: " + e.getMessage());
                    }
                    break;
                case 4:
                    try {
                        System.out.println("\nEnter City Code to Delete: ");
                        String code = scanner.nextLine();
                        deleteCityUserCase.execute(code);
                        System.out.println("City deleted successfully!");
                    } catch (Exception e) {
                        System.out.println("An error occurred while deleting the city: " + e.getMessage());
                    }
                    break;
                case 0:
                    GeneralControler gc = new GeneralControler();
                    gc.run();
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }

            System.out.println(); // Print a blank line for better readability
        }

        scanner.close();
    }
}