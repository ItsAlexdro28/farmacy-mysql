package com.farmacy.Console;

import java.util.Scanner;

import com.farmacy.Modules.country.aplication.CreateCountryUserCase;
import com.farmacy.Modules.country.aplication.DeleteCountryUserCase;
import com.farmacy.Modules.country.aplication.ReadCountryUserCase;
import com.farmacy.Modules.country.aplication.UpdateCountryUserCase;
import com.farmacy.Modules.country.domain.service.CountryService;
import com.farmacy.Modules.country.infrastructure.controller.CountryControler;
import com.farmacy.Modules.country.infrastructure.reporsitory.CountryRepository;

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
            "- ( 1 ) Country"
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
        
            default:
                break;
        }

        scanner.close();
    }

}