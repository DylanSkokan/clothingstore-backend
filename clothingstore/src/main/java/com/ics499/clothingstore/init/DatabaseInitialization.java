package com.ics499.clothingstore.init;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.ics499.clothingstore.model.Hat;
import com.ics499.clothingstore.model.Pants;
import com.ics499.clothingstore.model.Shirt;
import com.ics499.clothingstore.model.Shoes;
import com.ics499.clothingstore.repository.CustomerRepository;
import com.ics499.clothingstore.repository.ProductRepository;
import com.ics499.clothingstore.service.CustomerService;
/**
 * Loads template database info upon program start if the database is empty
 * 
 * @author Dylan Skokan
 */
@Component
class DatabaseInitialization implements CommandLineRunner {
	
	@Autowired
	ProductRepository<?> productRepository;
	
	@Autowired
	CustomerRepository customerRepository;
	
	@Autowired
	CustomerService customerService;

	@Override
	public void run(String... args) throws Exception {
        if (productRepository.count() == 0) {
        	
        	//shirts
        	productRepository.save(new Shirt(49.99f, 10, "V-Neck 100% cotton slim fit tee", "hanes", "white", "XL", "slim",
        	        Shirt.ShirtType.tShirt, "/assets/img/shirt1.png"));
        	productRepository.save(new Shirt(49.99f, 10, "100% cotton tee", "hanes", "black", "XL", "tshirt",
        	        Shirt.ShirtType.tShirt, "/assets/img/shirt2.png"));
        	productRepository.save(new Shirt(49.99f, 10, "Dinosaur Bar-b-que shirt", "Balenciaga", "black", "L", "tshirt",
        	        Shirt.ShirtType.tShirt, "/assets/img/shirt3.png"));
        	productRepository.save(new Shirt(49.99f, 10, "Naruto Shirt", "UTE", "grey", "XL", "tshirt",
        	        Shirt.ShirtType.tShirt, "/assets/img/shirt4.png"));
        	productRepository.save(new Shirt(49.99f, 10, "Hawaiian shirt", "UTE", "multicolor", "XL", "polo",
        	        Shirt.ShirtType.polo, "/assets/img/shirt5.png"));
        	productRepository.save(new Shirt(49.99f, 10, "Akatsuki sweatshirt", "UTE", "multicolor", "XL", "sweat",
        	        Shirt.ShirtType.sweat, "/assets/img/shirt6.png"));
        	
        	//pants
        	productRepository.save(new Pants(49.99f, 10, "Endura pants", "Endura", "black", "32W 30L", "slim",
        	        Pants.PantsType.Jeans, true, true, true, "/assets/img/pants1.png"));
        	productRepository.save(new Pants(49.99f, 10, "Khakhi pants", "Levis", "tan", "32W 30L", "boot cut",
        	        Pants.PantsType.Khaki, true, true, true, "/assets/img/pants2.png"));
        	productRepository.save(new Pants(49.99f, 10, "Fox pants", "fox", "black/brown", "32W 30L", "slim",
        	        Pants.PantsType.Jeans, true, true, true, "/assets/img/pants3.png"));
        	productRepository.save(new Pants(49.99f, 10, "Pinstripe pants", "Gucci", "white", "32W 30L", "boot cut",
        	        Pants.PantsType.Jeans, true, true, true, "/assets/img/pants4.png"));
        	productRepository.save(new Pants(49.99f, 10, "oarnge chinos", "bonobos", "orange", "32W 30L", "slim",
        	        Pants.PantsType.Jeans, true, true, true, "/assets/img/pants5.png"));
        	productRepository.save(new Pants(49.99f, 10, "cargo pants", "Dickies", "black", "32W 30L", "boot cut",
        	        Pants.PantsType.Jeans, true, true, true, "/assets/img/pants6.png"));

        	//shoes
        	productRepository.save(new Shoes(49.99f, 10, "Red High Heels", "Calvin Klein", "red", "11", "heels",
        	        Shoes.ShoesType.dress, "/assets/img/shoes1.png"));
        	productRepository.save(new Shoes(49.99f, 10, "Black running shoes", "Nike", "black/neon green", "11", "running",
        	        Shoes.ShoesType.sneakers, "/assets/img/shoes2.png"));
        	productRepository.save(new Shoes(49.99f, 10, "brown slip on dress shoes", "Nordstrom", "slip on", "11", "Hightop",
        	        Shoes.ShoesType.dress, "/assets/img/shoes3.png"));
        	productRepository.save(new Shoes(49.99f, 10, "Purple running shoes", "New Balance", "purple", "11", "sneakers",
        	        Shoes.ShoesType.sneakers, "/assets/img/shoes4.png"));
        	productRepository.save(new Shoes(49.99f, 10, "Blue loafers", "Coach", "Blue", "11", "loafers",
        	        Shoes.ShoesType.dress, "/assets/img/shoes5.png"));
        	productRepository.save(new Shoes(49.99f, 10, "winter boots", "Northwave", "black", "11", "boots",
        	        Shoes.ShoesType.boots, "/assets/img/shoes6.png"));
        	
        	//hats
        	productRepository.save(new Hat(49.99f, 10, "Yellowstone hat", "Zumiez", "grey/blackz", "large", "baseball",
        	        Hat.HatType.Baseball, "/assets/img/hat1.png"));
        	productRepository.save(new Hat(49.99f, 10, "Black Bucket hat", "Artisan", "black", "large", "bucket",
        	        Hat.HatType.Bucket, "/assets/img/hat2.png"));
        	productRepository.save(new Hat(49.99f, 10, "Trail Magik Hat", "trail Magik", "red", "large", "baseball",
        	        Hat.HatType.Baseball, "/assets/img/hat3.png"));
        	productRepository.save(new Hat(49.99f, 10, "Lawn Rebel hat", "New Era", "Gray", "large", "baseball",
        	        Hat.HatType.Baseball, "/assets/img/hat4.png"));
        	productRepository.save(new Hat(49.99f, 10, "The Boulevard hat", "New Era", "tan", "large", "baseball",
        	        Hat.HatType.Baseball, "/assets/img/hat5.png"));
        	productRepository.save(new Hat(49.99f, 10, "American flag hat", "New Era", "FDE", "large", "baseball",
        	        Hat.HatType.Baseball, "/assets/img/hat6.png"));
        }
        
        if (customerRepository.count() == 0) {
        	//test account
        	customerService.createCustomer("a", "a", "testFirstName", "testLastName", "test@test.com");
        }
	}
}