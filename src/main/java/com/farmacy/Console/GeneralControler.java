package com.farmacy.Console;

import java.util.Scanner;

import com.farmacy.Modules.activeprinciple.aplication.CreateActivePrincipleUserCase;
import com.farmacy.Modules.activeprinciple.aplication.DeleteActivePrincipleUserCase;
import com.farmacy.Modules.activeprinciple.aplication.ReadActivePrincipleUserCase;
import com.farmacy.Modules.activeprinciple.aplication.UpdateActivePrincipleUserCase;
import com.farmacy.Modules.activeprinciple.domain.service.ActivePrincipleService;
import com.farmacy.Modules.activeprinciple.infrastructure.controller.ActivePrincipleController;
import com.farmacy.Modules.activeprinciple.infrastructure.reporsitory.ActivePrincipleRepository;
import com.farmacy.Modules.city.aplication.CreateCityUserCase;
import com.farmacy.Modules.city.aplication.DeleteCityUserCase;
import com.farmacy.Modules.city.aplication.ReadCityUserCase;
import com.farmacy.Modules.city.aplication.UpdateCityUserCase;
import com.farmacy.Modules.city.domain.service.CityService;
import com.farmacy.Modules.city.infrastructure.controller.CityController;
import com.farmacy.Modules.city.infrastructure.reporsitory.CityRepository;
import com.farmacy.Modules.country.aplication.CreateCountryUserCase;
import com.farmacy.Modules.country.aplication.DeleteCountryUserCase;
import com.farmacy.Modules.country.aplication.ReadCountryUserCase;
import com.farmacy.Modules.country.aplication.UpdateCountryUserCase;
import com.farmacy.Modules.country.domain.service.CountryService;
import com.farmacy.Modules.country.infrastructure.controller.CountryControler;
import com.farmacy.Modules.country.infrastructure.reporsitory.CountryRepository;
import com.farmacy.Modules.customer.aplication.CreateCustomerUserCase;
import com.farmacy.Modules.customer.aplication.DeleteCustomerUserCase;
import com.farmacy.Modules.customer.aplication.ReadCustomerUserCase;
import com.farmacy.Modules.customer.aplication.UpdateCustomerUserCase;
import com.farmacy.Modules.customer.domain.service.CustomerService;
import com.farmacy.Modules.customer.infrastructure.controller.CustomerController;
import com.farmacy.Modules.customer.infrastructure.reporsitory.CustomerRepository;
import com.farmacy.Modules.farmacy.aplication.CreateFarmacyUserCase;
import com.farmacy.Modules.farmacy.aplication.DeleteFarmacyUserCase;
import com.farmacy.Modules.farmacy.aplication.ReadFarmacyUserCase;
import com.farmacy.Modules.farmacy.aplication.UpdateFarmacyUserCase;
import com.farmacy.Modules.farmacy.domain.service.FarmacyService;
import com.farmacy.Modules.farmacy.infrastructure.controller.FarmacyController;
import com.farmacy.Modules.farmacy.infrastructure.reporsitory.FarmacyRepository;
import com.farmacy.Modules.farmacymedicine.aplication.CreateFarmacyMedicineUserCase;
import com.farmacy.Modules.farmacymedicine.aplication.DeleteFarmacyMedicineUserCase;
import com.farmacy.Modules.farmacymedicine.aplication.ReadFarmacyMedicineUserCase;
import com.farmacy.Modules.farmacymedicine.aplication.UpdateFarmacyMedicineUserCase;
import com.farmacy.Modules.farmacymedicine.domain.service.FarmacyMedicineService;
import com.farmacy.Modules.farmacymedicine.infrastructure.controller.FarmacyMedicineController;
import com.farmacy.Modules.farmacymedicine.infrastructure.reporsitory.FarmacyMedicineRepository;
import com.farmacy.Modules.laboratory.aplication.CreateLaboratoryUserCase;
import com.farmacy.Modules.laboratory.aplication.DeleteLaboratoryUserCase;
import com.farmacy.Modules.laboratory.aplication.ReadLaboratoryUserCase;
import com.farmacy.Modules.laboratory.aplication.UpdateLaboratoryUserCase;
import com.farmacy.Modules.laboratory.domain.service.LaboratoryService;
import com.farmacy.Modules.laboratory.infrastructure.reporsitory.LaboratoryRepository;
import com.farmacy.Modules.medicine.aplication.CreateMedicineUserCase;
import com.farmacy.Modules.medicine.aplication.DeleteMedicineUserCase;
import com.farmacy.Modules.medicine.aplication.ReadMedicineUserCase;
import com.farmacy.Modules.medicine.aplication.UpdateMedicineUserCase;
import com.farmacy.Modules.medicine.domain.service.MedicineService;
import com.farmacy.Modules.medicine.infrastructure.controller.MedicineController;
import com.farmacy.Modules.medicine.infrastructure.reporsitory.MedicineRepository;
import com.farmacy.Modules.laboratory.infrastructure.controller.LaboratoryController;
import com.farmacy.Modules.modeadministration.aplication.CreateModeAdministrationUserCase;
import com.farmacy.Modules.modeadministration.aplication.DeleteModeAdministrationUserCase;
import com.farmacy.Modules.modeadministration.aplication.ReadModeAdministrationUserCase;
import com.farmacy.Modules.modeadministration.aplication.UpdateModeAdministrationUserCase;
import com.farmacy.Modules.modeadministration.domain.service.ModeAdministrationService;
import com.farmacy.Modules.modeadministration.infrastructure.controller.ModeAdministrationController;
import com.farmacy.Modules.modeadministration.infrastructure.reporsitory.ModeAdministrationRepository;
import com.farmacy.Modules.region.aplication.CreateRegionUserCase;
import com.farmacy.Modules.region.aplication.DeleteRegionUserCase;
import com.farmacy.Modules.region.aplication.ReadRegionUserCase;
import com.farmacy.Modules.region.aplication.UpdateRegionUserCase;
import com.farmacy.Modules.region.domain.service.RegionService;
import com.farmacy.Modules.region.infrastructure.controller.RegionController;
import com.farmacy.Modules.region.infrastructure.reporsitory.RegionRepository;
import com.farmacy.Modules.unitmeasurement.aplication.CreateUnitMeasurementUserCase;
import com.farmacy.Modules.unitmeasurement.aplication.DeleteUnitMeasurementUserCase;
import com.farmacy.Modules.unitmeasurement.aplication.ReadUnitMeasurementUserCase;
import com.farmacy.Modules.unitmeasurement.aplication.UpdateUnitMeasurementUserCase;
import com.farmacy.Modules.unitmeasurement.domain.service.UnitMeasurementService;
import com.farmacy.Modules.unitmeasurement.infrastructure.controller.UnitMeasurementController;
import com.farmacy.Modules.unitmeasurement.infrastructure.reporsitory.UnitMeasurementRepository;

public class GeneralControler {
    public GeneralControler () {

    }

    public void run() {
        // Country
        CountryService countryService = new CountryRepository();
        CreateCountryUserCase createCountryUserCase = new CreateCountryUserCase(countryService);
        ReadCountryUserCase readCountryUserCase = new ReadCountryUserCase(countryService);
        UpdateCountryUserCase updateCountryUserCase = new UpdateCountryUserCase(countryService);
        DeleteCountryUserCase deleteCountryUserCase = new DeleteCountryUserCase(countryService);
        CountryControler countryControler = new CountryControler(createCountryUserCase, readCountryUserCase, updateCountryUserCase, deleteCountryUserCase);
        
        // Region
        RegionService regionService = new RegionRepository();
        CreateRegionUserCase createRegionUserCase = new CreateRegionUserCase(regionService);
        ReadRegionUserCase readRegionUserCase = new ReadRegionUserCase(regionService);
        UpdateRegionUserCase updateRegionUserCase = new UpdateRegionUserCase(regionService);
        DeleteRegionUserCase deleteRegionUserCase = new DeleteRegionUserCase(regionService);
        RegionController regionController = new RegionController(createRegionUserCase, readRegionUserCase, updateRegionUserCase, deleteRegionUserCase);

        // City
        CityService cityService = new CityRepository();
        CreateCityUserCase createCityUserCase = new CreateCityUserCase(cityService);
        ReadCityUserCase readCityUserCase = new ReadCityUserCase(cityService);
        UpdateCityUserCase updateCityUserCase = new UpdateCityUserCase(cityService);
        DeleteCityUserCase deleteCityUserCase = new DeleteCityUserCase(cityService);
        CityController cityController = new CityController(createCityUserCase, readCityUserCase, updateCityUserCase, deleteCityUserCase);

        // Mode Administration
        ModeAdministrationService modeAdministrationService = new ModeAdministrationRepository();
        CreateModeAdministrationUserCase createModeAdministrationUserCase = new CreateModeAdministrationUserCase(modeAdministrationService);
        ReadModeAdministrationUserCase readModeAdministrationUserCase = new ReadModeAdministrationUserCase(modeAdministrationService);
        UpdateModeAdministrationUserCase updateModeAdministrationUserCase = new UpdateModeAdministrationUserCase(modeAdministrationService);
        DeleteModeAdministrationUserCase deleteModeAdministrationUserCase = new DeleteModeAdministrationUserCase(modeAdministrationService);
        ModeAdministrationController modeAdministrationController = new ModeAdministrationController(createModeAdministrationUserCase, readModeAdministrationUserCase, updateModeAdministrationUserCase, deleteModeAdministrationUserCase);

        // Unit Measurement
        UnitMeasurementService unitMeasurementService = new UnitMeasurementRepository();
        CreateUnitMeasurementUserCase createUnitMeasurementUserCase = new CreateUnitMeasurementUserCase(unitMeasurementService);
        ReadUnitMeasurementUserCase readUnitMeasurementUserCase = new ReadUnitMeasurementUserCase(unitMeasurementService);
        UpdateUnitMeasurementUserCase updateUnitMeasurementUserCase = new UpdateUnitMeasurementUserCase(unitMeasurementService);
        DeleteUnitMeasurementUserCase deleteUnitMeasurementUserCase = new DeleteUnitMeasurementUserCase(unitMeasurementService);
        UnitMeasurementController unitMeasurementController = new UnitMeasurementController(createUnitMeasurementUserCase, readUnitMeasurementUserCase, updateUnitMeasurementUserCase, deleteUnitMeasurementUserCase);

        // Active Principle
        ActivePrincipleService activePrincipleService = new ActivePrincipleRepository();
        CreateActivePrincipleUserCase createActivePrincipleUserCase = new CreateActivePrincipleUserCase(activePrincipleService);
        ReadActivePrincipleUserCase readActivePrincipleUserCase = new ReadActivePrincipleUserCase(activePrincipleService);
        UpdateActivePrincipleUserCase updateActivePrincipleUserCase = new UpdateActivePrincipleUserCase(activePrincipleService);
        DeleteActivePrincipleUserCase deleteActivePrincipleUserCase = new DeleteActivePrincipleUserCase(activePrincipleService);
        ActivePrincipleController activePrincipleController = new ActivePrincipleController(createActivePrincipleUserCase, readActivePrincipleUserCase, updateActivePrincipleUserCase, deleteActivePrincipleUserCase);

        // Farmacy
        LaboratoryService laboratoryService = new LaboratoryRepository();
        CreateLaboratoryUserCase createLaboratoryUserCase = new CreateLaboratoryUserCase(laboratoryService);
        ReadLaboratoryUserCase readLaboratoryUserCase = new ReadLaboratoryUserCase(laboratoryService);
        UpdateLaboratoryUserCase updateLaboratoryUserCase = new UpdateLaboratoryUserCase(laboratoryService);
        DeleteLaboratoryUserCase deleteLaboratoryUserCase = new DeleteLaboratoryUserCase(laboratoryService);
        LaboratoryController laboratoryController = new LaboratoryController(createLaboratoryUserCase, readLaboratoryUserCase, updateLaboratoryUserCase, deleteLaboratoryUserCase);

        // Customer
        CustomerService customerService = new CustomerRepository();
        CreateCustomerUserCase createCustomerUserCase = new CreateCustomerUserCase(customerService);
        ReadCustomerUserCase readCustomerUserCase = new ReadCustomerUserCase(customerService);
        UpdateCustomerUserCase updateCustomerUserCase = new UpdateCustomerUserCase(customerService);
        DeleteCustomerUserCase deleteCustomerUserCase = new DeleteCustomerUserCase(customerService);
        CustomerController customerController = new CustomerController(createCustomerUserCase, readCustomerUserCase, updateCustomerUserCase, deleteCustomerUserCase);

        // Farmacy
        FarmacyService farmacyService = new FarmacyRepository();
        CreateFarmacyUserCase createFarmacyUserCase = new CreateFarmacyUserCase(farmacyService);
        ReadFarmacyUserCase readFarmacyUserCase = new ReadFarmacyUserCase(farmacyService);
        UpdateFarmacyUserCase updateFarmacyUserCase = new UpdateFarmacyUserCase(farmacyService);
        DeleteFarmacyUserCase deleteFarmacyUserCase = new DeleteFarmacyUserCase(farmacyService);
        FarmacyController farmacyController = new FarmacyController(createFarmacyUserCase, readFarmacyUserCase, updateFarmacyUserCase, deleteFarmacyUserCase);

        // Medicine
        MedicineService medicineService = new MedicineRepository();
        CreateMedicineUserCase createMedicineUserCase = new CreateMedicineUserCase(medicineService);
        ReadMedicineUserCase readMedicineUserCase = new ReadMedicineUserCase(medicineService);
        UpdateMedicineUserCase updateMedicineUserCase = new UpdateMedicineUserCase(medicineService);
        DeleteMedicineUserCase deleteMedicineUserCase = new DeleteMedicineUserCase(medicineService);
        MedicineController medicineController = new MedicineController(createMedicineUserCase, readMedicineUserCase, updateMedicineUserCase, deleteMedicineUserCase);

        // Farmacy & Medicine
        FarmacyMedicineService farmacyMedicineService = new FarmacyMedicineRepository();
        CreateFarmacyMedicineUserCase createFarmacyMedicineUserCase = new CreateFarmacyMedicineUserCase(farmacyMedicineService);
        ReadFarmacyMedicineUserCase readFarmacyMedicineUserCase = new ReadFarmacyMedicineUserCase(farmacyMedicineService);
        UpdateFarmacyMedicineUserCase updateFarmacyMedicineUserCase = new UpdateFarmacyMedicineUserCase(farmacyMedicineService);
        DeleteFarmacyMedicineUserCase deleteFarmacyMedicineUserCase = new DeleteFarmacyMedicineUserCase(farmacyMedicineService);
        FarmacyMedicineController farmacyMedicineController = new FarmacyMedicineController(createFarmacyMedicineUserCase, readFarmacyMedicineUserCase, updateFarmacyMedicineUserCase, deleteFarmacyMedicineUserCase);




        // Runing
        Scanner scanner = new Scanner(System.in);

        String header = """
        ░█▀▀▀ █▀▀█ █▀▀█ █▀▄▀█ █▀▀█ █▀▀ █──█ 
        ░█▀▀▀ █▄▄█ █▄▄▀ █─▀─█ █▄▄█ █── █▄▄█ 
        ░█─── ▀──▀ ▀─▀▀ ▀───▀ ▀──▀ ▀▀▀ ▄▄▄█

        -----------------------------------
             Welcome to our aplication
        -----------------------------------
        """;
        String[] mainOptions = {
            "- ( 1 ) Country",
            "- ( 2 ) Region",
            "- ( 3 ) City",
            "- ( 4 ) Mode Administration",
            "- ( 5 ) Unit Measurement",
            "- ( 6 ) Active Principle",
            "- ( 7 ) Laboratory",
            "- ( 8 ) Customer",
            "- ( 9 ) Farmacy",
            "- ( 10 ) Medicine",
            "- ( 11 ) Farmacy & Medicine",
            "- ( 0 ) Exit"
        };

        System.out.println(header);
        for (String option : mainOptions) {
            System.out.println(option);
        }
        System.out.println(">> Which table you want to use?");
        int selectedOption = scanner.nextInt();
        switch (selectedOption) {
            case 1:
                countryControler.run();
                break;
            case 2:
                regionController.run();
                break;        
            case 3:
                cityController.run();
                break;        
            case 4:
                modeAdministrationController.run();
                break;        
            case 5:
                unitMeasurementController.run();
                break;        
            case 6:
                activePrincipleController.run();
                break;        
            case 7:
                laboratoryController.run();
                break;        
            case 8:
                customerController.run();
                break;        
            case 9:
                farmacyController.run();
                break;        
            case 10:
                medicineController.run();
                break;        
            case 11:
                farmacyMedicineController.run();
                break;        
            default:
                break;
        }

        scanner.close();
    }

}