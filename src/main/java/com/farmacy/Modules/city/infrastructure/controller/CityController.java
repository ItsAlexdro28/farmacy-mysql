package com.farmacy.Modules.city.infrastructure.controller;
import java.text.MessageFormat;
import java.util.Optional;

import com.farmacy.Console.GeneralControler;
import com.farmacy.Console.Util;
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
        while (choice != 0) {
            System.out.println("Please select an option:");
            for (int i = 0; i < options.length; i++) {
                System.out.println((i + 1) + ". " + options[i]);
            }
            System.out.println("0. Exit");

            System.out.print("Enter your choice: ");
            choice = Util.getIntInput("Enter your choice: ");
            switch (choice) {
                case 1:
                    try {
                        String code = Util.getStringInput("\nEnter City Code: ");
                        String name = Util.getStringInput("Enter City Name: ");
                        String regionCode = Util.getStringInput("Enter Region Code: ");
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
                        String code = Util.getStringInput("\nEnter City Code: ");
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
                        String code = Util.getStringInput("\nEnter City Code to Update: ");
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

                                    int choice = Util.getIntInput("Enter your choice: ");
                                    switch (choice) {
                                        case 1:
                                            String newName = Util.getStringInput("\nEnter new City Name: ");
                                            city.setNameCity(newName);
                                            break;
                                        case 2:
                                            String newCode = Util.getStringInput("\nEnter new City Code: ");
                                            city.setCodeCity(newCode);
                                            break;
                                        case 3:
                                            String newRegionCode = Util.getStringInput("\nEnter new Region Code: ");
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
                        String code = Util.getStringInput("\nEnter City Code to Delete: ");
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
    }
}