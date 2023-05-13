package com.ics499.clothingstore.init;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import com.ics499.clothingstore.repository.ShirtRepository;

/**
 * Loads template database info upon program start if the database is empty
 * 
 * @author Dylan Skokan
 */
@Component
class DatabaseInitialization implements CommandLineRunner {
	
	@Autowired
	ShirtRepository shirtRepository;

	@Override
	public void run(String... args) throws Exception {
		
        if (shirtRepository.count() == 0) {
        	
        }
        
        
	}
}