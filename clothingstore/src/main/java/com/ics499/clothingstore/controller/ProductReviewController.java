package com.ics499.clothingstore.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ics499.clothingstore.model.ProductReview;
import com.ics499.clothingstore.repository.ProductRepository;
import com.ics499.clothingstore.repository.ProductReviewRepository;
import com.ics499.clothingstore.service.ProductReviewService;

/**
 * Product Review Controller, used to send information to Angular Front end.
 * 
 * @author Dylan Skokan - Isaiah Cuellar - Tom Waterman - Justin Pham - Kyle
 *         McClernon
 *
 */

@RestController
@RequestMapping("/productReview")
@CrossOrigin(origins = "http://localhost:4200")
public class ProductReviewController {

	@Autowired
	ProductReviewRepository productReviewRepository;

	@Autowired
	ProductRepository<?> productRepository;

	@Autowired
	ProductReviewService productReviewService;

	@GetMapping("/test")
	public String test() {
		return "Product Review Controller";
	}

	@PostMapping("/save")
	public ProductReview saveProductReview(@RequestBody ProductReview productReview) {
		return productReviewRepository.save(productReview);
	}

	@PostMapping("/saveProductReview")
	public boolean saveProductReview(@RequestBody Map<String, String> productReview) {
		return productReviewService.saveProductReview(productReview);
	}

	@GetMapping("/all")
	public List<ProductReview> list() {
		return productReviewRepository.findAll();
	}

	@GetMapping("/getById/{id}")
	public List<ProductReview> getById(@PathVariable Long id) {
		List<ProductReview> foundReviews = productReviewRepository.findByProductId(id);
		return foundReviews;
	}

	@GetMapping("{id}")
	public ProductReview getReviewsByProdId(@PathVariable Long id) {
		return productReviewRepository.getReferenceById(id);
	}

	@PostMapping("/add")
	public ProductReview create(@RequestBody final ProductReview productReview) {
		return productReviewRepository.saveAndFlush(productReview);
	}
}
