package com.farmacy.Modules.country.infrastructure.controller;

import java.text.MessageFormat;
import java.util.Optional;

import com.farmacy.Console.GeneralControler;
import com.farmacy.Modules.country.aplication.CreateCountryUserCase;
import com.farmacy.Modules.country.aplication.DeleteCountryUserCase;
import com.farmacy.Modules.country.aplication.ReadCountryUserCase;
import com.farmacy.Modules.country.aplication.UpdateCountryUserCase;
import com.farmacy.Modules.country.domain.entity.Country;
import com.farmacy.Console.Util;

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
                        String code = Util.getStringInput("\nEnter Country Code: ");
                        String name = Util.getStringInput("Enter Country Name: ");
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
                        String code = Util.getStringInput("\nEnter Country Code: ");
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
                        String code = Util.getStringInput("\nEnter Country Code: ");
                        Optional<Country> countryToUpdate = readCountryUserCase.execute(code);
                        countryToUpdate.ifPresentOrElse(
                            country -> {
                                boolean continueUpdating = true;
                                while (continueUpdating) {
                                    System.out.println("\nSelect field to update:");
                                    System.out.println("1. Country Name");
                                    System.out.println("2. Country Code");
                                    System.out.println("0. Exit");
        
                                    int choice = Util.getIntInput("Enter your choice: ");
                                    switch (choice) {
                                        case 1:
                                            String newName = Util.getStringInput("\nEnter new Country Name: ");
                                            country.setNameCountry(newName);
                                            break;
                                        case 2:
                                            String newCode = Util.getStringInput("\nEnter new Country Code: ");
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
                        String code = Util.getStringInput("\nEnter Country Code to Delete: ");
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
            
            System.out.println(); 
        }
        
    }
}
