package com.ics499.demo;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import com.ics499.clothingstore.ClothingstoreApplication;
import com.ics499.clothingstore.model.Customer;
import com.ics499.clothingstore.model.Guest;
import com.ics499.clothingstore.model.Hat;
import com.ics499.clothingstore.model.Hat.HatType;
import com.ics499.clothingstore.model.Order;
import com.ics499.clothingstore.model.OrderItem;
import com.ics499.clothingstore.model.Pants;
import com.ics499.clothingstore.model.Pants.PantsType;
import com.ics499.clothingstore.model.Product;
import com.ics499.clothingstore.model.Shirt;
import com.ics499.clothingstore.model.Shirt.ShirtType;
import com.ics499.clothingstore.model.Shoes;
import com.ics499.clothingstore.model.Shoes.ShoesType;
import com.ics499.clothingstore.model.Transaction;
import com.ics499.clothingstore.repository.CustomerRepository;
import com.ics499.clothingstore.repository.HatRepository;
import com.ics499.clothingstore.repository.OrderRepository;
import com.ics499.clothingstore.repository.PantsRepository;
import com.ics499.clothingstore.repository.ProductRepository;
import com.ics499.clothingstore.repository.ShirtRepository;
import com.ics499.clothingstore.repository.ShoesRepository;
import com.ics499.clothingstore.repository.UserRepository;

/**
 * These tests assume that the database has been initialized with data.sql in the src/main/resources folder
 * 
 * @author Dylan Skokan - Isaiah Cuellar - Tom Waterman - Justin Pham - Kyle McClernon
 */
@SpringBootTest(classes = ClothingstoreApplication.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@Transactional
class ClothingstoreDemo {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private CustomerRepository customerRepository;

	@Autowired
	private ProductRepository<?> productRepository;

	@Autowired
	private ShirtRepository shirtRepository;

	@Autowired
	private PantsRepository pantsRepository;

	@Autowired
	private HatRepository hatRepository;

	@Autowired
	private ShoesRepository shoesRepository;

	@Autowired
	private OrderRepository orderRepository;

	@Test
	void createUser() {

		Customer customer = new Customer("a firstname", "a lastname", "an address", "a city", "a state",
				"a phone number", new Date(), "an email");

		// user repository is parent of customer repository
		userRepository.save(customer);

		Customer databaseCustomer = customerRepository.findByName("a firstname");
		assertThat(databaseCustomer).isNotNull();
		assertThat(databaseCustomer.getFirstName()).isEqualTo("a firstname");
	}

	@SuppressWarnings("unlikely-arg-type")
	@Test
	void addVariousProductsToDb() {
		// add shirt to db
		shirtRepository.save(new Shirt((float) 12.20, 3, "cool shirt", "shirt brand", "red", "XXL", "loose",
				ShirtType.tShirt, "image file path for front end"));

		// add pants to db
		pantsRepository.save(new Pants((float) 20.00, 3, "cool cargo pants", "cargo pants brand", "tan", "medium",
				"medium", PantsType.Cargo, false, false, false, "image file path for front end"));

		// add hat to db
		hatRepository.save(new Hat((float) 15.50, 3, "beanie hat description", "the beanie hat guys", "gray", "M",
				"loose", HatType.Beanie, "image file path for front end"));

		// add shoes to db
		shoesRepository.save(new Shoes((float) 30.00, 3, "winter boots", "polarboots", "black", "12", "medium",
				ShoesType.boots, "image file path for front end"));

		// confirm from parent products table that they are all there
		List<Product> products = productRepository.findAll();

		// assert that each product is in the list
		assertTrue(products.contains(new Shirt((float) 12.20, 3, "cool shirt", "shirt brand", "red", "XXL", "loose",
				ShirtType.tShirt, "image file path for front end")));
		assertTrue(products.contains(new Pants((float) 20.00, 3, "cool cargo pants", "cargo pants brand", "tan",
				"medium", "medium", PantsType.Cargo, false, false, false, "image file path for front end")));
		assertTrue(products.contains(new Hat((float) 15.50, 3, "beanie hat description", "the beanie hat guys", "gray",
				"M", "loose", HatType.Beanie, "image file path for front end")));
		assertTrue(products.contains(new Shoes((float) 30.00, 3, "winter boots", "polarboots", "black", "12", "medium",
				ShoesType.boots, "image file path for front end")));

		List<Shirt> shirts = shirtRepository.findAll();

		// assert that ONLY the shirt is in the shirt repository
		assertFalse(shirts.contains(new Shoes((float) 30.00, 3, "winter boots", "polarboots", "black", "12", "medium",
				ShoesType.boots, "image file path for front end")));
	}

	@Test
	void saveOrderFromFrontendWithoutAccount() {
		// In this instance, we would get a JSON of the order total, and list of
		// products, and their quantities.
		// In practice we would have to parse the JSON
		Order order = new Order();
		List<Product> products = productRepository.findAll();
		List<OrderItem> items = new ArrayList<>();
		for (int i = 0; i < 10; i++) {
			items.add(new OrderItem(order, products.get(i), 3));
		}

		order.setOrderItems(items);

		// Need to add transaction info, save in another table for security reasons
		// Cardholder name, card num, CVC, exp date, transaction total, transaction date
		order.setTransaction(new Transaction("name of cardholder", "card num goes here", 323, new Date(), 120.20, new Date()));

		order.setUser(new Guest());
		
		Order savedOrder = orderRepository.save(order);

		// Use the order we just saved and get its items
		List<OrderItem> fromRepoItems = savedOrder.getOrderItems();

		assertThat(fromRepoItems).isNotNull();
		assertThat(fromRepoItems.size()).isEqualTo(10);
		assertThat(fromRepoItems.get(3).getQuantity()).isEqualTo(3);

		assertThat(savedOrder.getTransaction().getCreditCardNumber()).isEqualTo("name of cardholder");
	}

	@Test
	void saveOrderFromFrontendWithAccount() {
		Customer customer = customerRepository.findByName("Dylan");
		List<Product> products = productRepository.findAll();
		Order order = new Order();

		// add the first 10 products, and 2 of each product
		List<OrderItem> items = new ArrayList<>();
		for (int i = 0; i < 10; i++) {
			items.add(new OrderItem(order, products.get(i), 2));
		}

		order.setOrderItems(items);
		order.setTransaction(new Transaction("name of cardholder", "card num goes here", 323, new Date(), 120.20, new Date()));
		customer.addOrder(order);

		customerRepository.save(customer);

		Customer custFromRepo = customerRepository.findByName("Dylan");

		List<Order> orderList = custFromRepo.getOrders();

		assertThat(orderList).isNotNull();

		List<OrderItem> fromRepoItems = orderList.get(0).getOrderItems();

		assertThat(fromRepoItems).isNotNull();
		assertThat(fromRepoItems.size()).isEqualTo(10);
		assertThat(fromRepoItems.get(3).getQuantity()).isEqualTo(2);

		Transaction orderTransaction = orderList.get(0).getTransaction();

		assertThat(orderTransaction.getCreditCardNumber()).isEqualTo("name of cardholder");
	}
}