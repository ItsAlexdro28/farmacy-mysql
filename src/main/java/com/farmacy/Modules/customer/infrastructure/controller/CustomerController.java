package com.farmacy.Modules.customer.infrastructure.controller;

import java.util.Optional;
import java.util.Scanner;

import com.farmacy.Console.GeneralControler;
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

    public CustomerController(CreateCustomerUserCase createCustomerUserCase, ReadCustomerUserCase readCustomerUserCase,
            UpdateCustomerUserCase updateCustomerUserCase, DeleteCustomerUserCase deleteCustomerUserCase) {
        this.createCustomerUserCase = createCustomerUserCase;
        this.readCustomerUserCase = readCustomerUserCase;
        this.updateCustomerUserCase = updateCustomerUserCase;
        this.deleteCustomerUserCase = deleteCustomerUserCase;
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
                        System.out.println("\nEnter Customer ID: ");
                        String id = scanner.nextLine();
                        System.out.println("Enter Customer First Name: ");
                        String firstName = scanner.nextLine();
                        System.out.println("Enter Customer Last Name: ");
                        String lastName = scanner.nextLine();
                        System.out.println("Enter Customer City Code: ");
                        String cityCode = scanner.nextLine();
                        System.out.println("Enter Customer Email: ");
                        String email = scanner.nextLine();
                        System.out.println("Enter Customer Birthdate (YYYY-MM-DD): ");
                        String birthdate = scanner.nextLine();
                        System.out.println("Enter Customer Longitude: ");
                        float longitude = scanner.nextFloat();
                        System.out.println("Enter Customer Latitude: ");
                        float latitude = scanner.nextFloat();
                        scanner.nextLine(); // consume the newline

                        Customer customer = new Customer(id, firstName, lastName, cityCode, email, birthdate, longitude, latitude);
                        createCustomerUserCase.execute(customer);

                        System.out.println("Customer created successfully!");
                    } catch (Exception e) {
                        System.out.println("An error occurred while creating the customer: " + e.getMessage());
                    }
                    break;
                case 2:
                    try {
                        System.out.println("\nEnter Customer ID: ");
                        String id = scanner.nextLine();
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
                        System.out.println("\nEnter Customer ID to Update: ");
                        String id = scanner.nextLine();
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

                                    System.out.print("Enter your choice: ");
                                    int fieldChoice = scanner.nextInt();
                                    scanner.nextLine(); // consume the newline
                                    switch (fieldChoice) {
                                        case 1:
                                            System.out.println("\nEnter new First Name: ");
                                            String newFirstName = scanner.nextLine();
                                            customer.setNameCustomer(newFirstName);
                                            break;
                                        case 2:
                                            System.out.println("\nEnter new Last Name: ");
                                            String newLastName = scanner.nextLine();
                                            customer.setLastNameCustomer(newLastName);
                                            break;
                                        case 3:
                                            System.out.println("\nEnter new City Code: ");
                                            String newCityCode = scanner.nextLine();
                                            customer.setCodeCityCustomer(newCityCode);
                                            break;
                                        case 4:
                                            System.out.println("\nEnter new Email: ");
                                            String newEmail = scanner.nextLine();
                                            customer.setEmailCustomer(newEmail);
                                            break;
                                        case 5:
                                            System.out.println("\nEnter new Birthdate (YYYY-MM-DD): ");
                                            String newBirthdate = scanner.nextLine();
                                            customer.setBirthDate(newBirthdate);
                                            break;
                                        case 6:
                                            System.out.println("\nEnter new Longitude: ");
                                            float newLongitude = scanner.nextFloat();
                                            customer.setLongitude(newLongitude);
                                            break;
                                        case 7:
                                            System.out.println("\nEnter new Latitude: ");
                                            float newLatitude = scanner.nextFloat();
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
                        System.out.println("\nEnter Customer ID to Delete: ");
                        String id = scanner.nextLine();
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
        scanner.close();
    }
}
