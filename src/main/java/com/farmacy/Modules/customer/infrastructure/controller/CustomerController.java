package com.farmacy.Modules.customer.infrastructure.controller;

import java.util.Optional;

import com.farmacy.Console.GeneralControler;
import com.farmacy.Console.Util;
import com.farmacy.Modules.customer.aplication.CreateCustomerUserCase;
import com.farmacy.Modules.customer.aplication.DeleteCustomerUserCase;
import com.farmacy.Modules.customer.aplication.ReadCustomerUserCase;
import com.farmacy.Modules.customer.aplication.UpdateCustomerUserCase;
import com.farmacy.Modules.customer.domain.entity.Customer;

public class CustomerController {
    private CreateCustomerUserCase createCustomerUserCase;
    private ReadCustomerUserCase readCustomerUserCase;
    private UpdateCustomerUserCase updateCustomerUserCase;
    private DeleteCustomerUserCase deleteCustomerUserCase;
    Object[] options = { "Create Customer", "Read Customer", "Update Customer", "Delete Customer" };
    int choice = -1;
    boolean dateCorrect = true;

    public CustomerController(CreateCustomerUserCase createCustomerUserCase, ReadCustomerUserCase readCustomerUserCase,
            UpdateCustomerUserCase updateCustomerUserCase, DeleteCustomerUserCase deleteCustomerUserCase) {
        this.createCustomerUserCase = createCustomerUserCase;
        this.readCustomerUserCase = readCustomerUserCase;
        this.updateCustomerUserCase = updateCustomerUserCase;
        this.deleteCustomerUserCase = deleteCustomerUserCase;
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
                        String id = Util.getStringInput("\nEnter Customer ID: ");
                        String firstName = Util.getStringInput("Enter Customer First Name: ");
                        String lastName = Util.getStringInput("Enter Customer Last Name: ");
                        String cityCode = Util.getStringInput("Enter Customer City Code: ");
                        String email = Util.getStringInput("Enter Customer Email: ");
                        String birthdate = Util.getStringInput("Enter Customer Birthdate (YYYY-MM-DD): ");
                        float longitude = Util.getFloatInput("Enter Customer Longitude: ");
                        float latitude = Util.getFloatInput("Enter Customer Latitude: ");
                        Customer customer = new Customer(id, firstName, lastName, cityCode, email, birthdate, longitude, latitude);
                        createCustomerUserCase.execute(customer);

                        System.out.println("Customer created successfully!");
                    } catch (Exception e) {
                        System.out.println("An error occurred while creating the customer: " + e.getMessage());
                    }
                    break;
                case 2:
                    try {
                        String id = Util.getStringInput("\nEnter Customer ID: ");
                        readCustomerUserCase.execute(id).ifPresentOrElse(
                            customerFound -> {
                                System.out.println("\nCustomer Info: ");
                                System.out.println("ID: " + customerFound.getIdCustomer());
                                System.out.println("First Name: " + customerFound.getNameCustomer());
                                System.out.println("Last Name: " + customerFound.getLastNameCustomer());
                                System.out.println("City Code: " + customerFound.getCodeCityCustomer());
                                System.out.println("Email: " + customerFound.getEmailCustomer());
                                System.out.println("Birthdate: " + customerFound.getBirthDate());
                                System.out.println("Longitude: " + customerFound.getLongitude());
                                System.out.println("Latitude: " + customerFound.getLatitude());
                            },
                            () -> System.out.println("Customer not found!")
                        );
                    } catch (Exception e) {
                        System.out.println("An error occurred while reading the customer: " + e.getMessage());
                    }
                    break;
                case 3:
                    try {
                        String id = Util.getStringInput("\nEnter Customer ID to Update: ");
                        Optional<Customer> customerToUpdate = readCustomerUserCase.execute(id);
                        customerToUpdate.ifPresentOrElse(
                            customer -> {
                                boolean continueUpdating = true;
                                while (continueUpdating) {
                                    System.out.println("\nSelect field to update:");
                                    System.out.println("1. First Name");
                                    System.out.println("2. Last Name");
                                    System.out.println("3. City Code");
                                    System.out.println("4. Email");
                                    System.out.println("5. Birthdate");
                                    System.out.println("6. Longitude");
                                    System.out.println("7. Latitude");
                                    System.out.println("0. Exit");

                                    int fieldChoice = Util.getIntInput("Enter your choice: ");
                                    switch (fieldChoice) {
                                        case 1:
                                            String newFirstName = Util.getStringInput("\nEnter new First Name: ");
                                            customer.setNameCustomer(newFirstName);
                                            break;
                                        case 2:
                                            String newLastName = Util.getStringInput("\nEnter new Last Name: ");
                                            customer.setLastNameCustomer(newLastName);
                                            break;
                                        case 3:
                                            String newCityCode = Util.getStringInput("\nEnter new City Code: ");
                                            customer.setCodeCityCustomer(newCityCode);
                                            break;
                                        case 4:
                                            String newEmail = Util.getStringInput("\nEnter new Email: ");
                                            customer.setEmailCustomer(newEmail);
                                            break;
                                        case 5:
                                            String newBirthdate = "si";
                                            do {
                                            String Birthdate = Util.getStringInput("\nEnter new Birthdate (YYYY-MM-DD): ");
                                            dateCorrect = Util.checkDateFormat(newBirthdate, "yyyy-MM-dd");
                                            newBirthdate = Birthdate;
                                            } while (dateCorrect == false);
                                            customer.setBirthDate(newBirthdate);
                                            break;
                                        case 6:
                                            float newLongitude = Util.getFloatInput("\nEnter new Longitude: ");
                                            customer.setLongitude(newLongitude);
                                            break;
                                        case 7:
                                            float newLatitude = Util.getFloatInput("\nEnter new Latitude: ");
                                            customer.setLatitude(newLatitude);
                                            break;
                                        case 0:
                                            continueUpdating = false;
                                            break;
                                        default:
                                            System.out.println("Invalid choice. Please try again.");
                                            continue;
                                    }
                                    if (fieldChoice != 0) {
                                        updateCustomerUserCase.execute(customer, id);
                                        System.out.println("Customer updated successfully!");
                                    }
                                }
                            },
                            () -> System.out.println("Customer not found!")
                        );
                    } catch (Exception e) {
                        System.out.println("An error occurred while updating the customer: " + e.getMessage());
                    }
                    break;
                case 4:
                    try {
                        String id = Util.getStringInput("\nEnter Customer ID to Delete: ");
                        deleteCustomerUserCase.execute(id);
                        System.out.println("Customer deleted successfully!");
                    } catch (Exception e) {
                        System.out.println("An error occurred while deleting the customer: " + e.getMessage());
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
