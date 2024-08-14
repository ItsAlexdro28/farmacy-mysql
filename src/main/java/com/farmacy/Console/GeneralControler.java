package com.farmacy.Console;

import java.util.Scanner;

import com.farmacy.Modules.country.aplication.CreateCountryUserCase;
import com.farmacy.Modules.country.aplication.DeleteCountryUserCase;
import com.farmacy.Modules.country.aplication.ReadCountryUserCase;
import com.farmacy.Modules.country.aplication.UpdateCountryUserCase;
import com.farmacy.Modules.country.domain.service.CountryService;
import com.farmacy.Modules.country.infrastructure.controller.CountryControler;
import com.farmacy.Modules.country.infrastructure.reporsitory.CountryRepository;
import com.farmacy.Modules.region.aplication.CreateRegionUserCase;
import com.farmacy.Modules.region.aplication.DeleteRegionUserCase;
import com.farmacy.Modules.region.aplication.ReadRegionUserCase;
import com.farmacy.Modules.region.aplication.UpdateRegionUserCase;
import com.farmacy.Modules.region.domain.service.RegionService;
import com.farmacy.Modules.region.infrastructure.controller.RegionController;
import com.farmacy.Modules.region.infrastructure.reporsitory.RegionRepository;

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
            "- ( 2 ) Region"
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
            default:
                break;
        }

        scanner.close();
    }

}