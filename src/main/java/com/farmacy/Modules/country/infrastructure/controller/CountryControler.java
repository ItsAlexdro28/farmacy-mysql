package com.farmacy.Modules.country.infrastructure.controller;

import java.text.MessageFormat;
import java.util.Optional;
import java.util.Scanner;

import com.farmacy.Console.GeneralControler;
import com.farmacy.Modules.country.aplication.CreateCountryUserCase;
import com.farmacy.Modules.country.aplication.DeleteCountryUserCase;
import com.farmacy.Modules.country.aplication.ReadCountryUserCase;
import com.farmacy.Modules.country.aplication.UpdateCountryUserCase;
import com.farmacy.Modules.country.domain.entity.Country;

public class CountryControler {
    private CreateCountryUserCase createCountryUserCase;
    private ReadCountryUserCase readCountryUserCase;
    private UpdateCountryUserCase updateCountryUserCase;
    private DeleteCountryUserCase deleteCountryUserCase;
    Object[] options = {"Create Country", "Read Country", "Update Country", "Delete Country"};
    int choice = -1;
    
    public CountryControler(CreateCountryUserCase createCountryUserCase, ReadCountryUserCase readCountryUserCase, UpdateCountryUserCase updateCountryUserCase, DeleteCountryUserCase deleteCountryUserCase) {
        this.createCountryUserCase = createCountryUserCase;
        this.readCountryUserCase = readCountryUserCase;
        this.updateCountryUserCase = updateCountryUserCase;
        this.deleteCountryUserCase = deleteCountryUserCase;
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
                        System.out.println("\nEnter Country Code: ");
                        String code = scanner.nextLine();
                        System.out.println("Enter Country Name: ");
                        String name = scanner.nextLine();
                        Country country = new Country();
                        country.setCodeCountry(code);
                        country.setNameCountry(name);

                        createCountryUserCase.execute(country);

                        System.out.println("Country created successfully!");
                    } catch (Exception e) {
                        System.out.println("An error occurred while creating the country: " + e.getMessage());
                    }
                    break;
                case 2:
                    try {
                        System.out.println("\nEnter Country Code: ");
                        String code = scanner.nextLine();
                        readCountryUserCase.execute(code).ifPresentOrElse(
                            countryFound -> {
                                System.out.println("\nCountry Info: ");
                                System.out.println(MessageFormat.format("Code: {0}", countryFound.getCodeCountry()));
                                System.out.println(MessageFormat.format("Name: {0}", countryFound.getNameCountry()));
                            },
                            () -> {
                        });
                    } catch (Exception e) {
                        System.out.println("An error occurred while reading the country: " + e.getMessage());
                    }
                    break;
                case 3:
                    try {
                        System.out.println("\nEnter Country Code to Update: ");
                        String code = scanner.nextLine();
                        Optional<Country> countryToUpdate = readCountryUserCase.execute(code);
                        countryToUpdate.ifPresentOrElse(
                            country -> {
                                boolean continueUpdating = true;
                                while (continueUpdating) {
                                    System.out.println("\nSelect field to update:");
                                    System.out.println("1. Country Name");
                                    System.out.println("2. Country Code");
                                    System.out.println("0. Exit");
        
                                    System.out.print("Enter your choice: ");
                                    int choice = scanner.nextInt();
                                    scanner.nextLine();
                                    switch (choice) {
                                        case 1:
                                            System.out.println("\nEnter new Country Name: ");
                                            String newName = scanner.nextLine();
                                            country.setNameCountry(newName);
                                            break;
                                        
                                        case 2:
                                            System.out.println("\nEnter new Country Code: ");
                                            String newCode = scanner.nextLine();
                                            country.setCodeCountry(newCode);
                                            break;
                                        case 0:
                                            continueUpdating = false;
                                            break;
                    
                                        default:
                                            System.out.println("Invalid choice. Please try again.");
                                            continue;
                                    }
                                    if (choice != 0) {
                                        updateCountryUserCase.execute(country, code);
                                        System.out.println("Country updated successfully!");
                                    }
                                }
                            },
                            () -> System.out.println("Country not found!")
                        );
                    } catch (Exception e) {
                        System.out.println("An error occurred while updating the country: " + e.getMessage());
                    }
                    break;
                case 4:
                    try {
                        System.out.println("\nEnter Country Code to Delete: ");
                        String code = scanner.nextLine();
                        deleteCountryUserCase.execute(code);
                        System.out.println("Country deleted successfully!");
                    } catch (Exception e) {
                        System.out.println("An error occurred while deleting the country: " + e.getMessage());
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
