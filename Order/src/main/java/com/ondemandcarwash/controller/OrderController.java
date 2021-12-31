package com.ondemandcarwash.controller;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.ondemandcarwash.exception.ApiRequestException;
import com.ondemandcarwash.model.Address;
import com.ondemandcarwash.model.Order;
import com.ondemandcarwash.repository.OrderRepository;
import com.ondemandcarwash.service.OrderService;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;

@RestController
@RequestMapping("/order")
public class OrderController {

	Logger logger = LoggerFactory.getLogger(OrderController.class);

	private static final String OrderService = "Order-Service";

	@Autowired
	private RestTemplate restTemplate;

	@Autowired
	private OrderService orderService;

	@Autowired
	private OrderRepository orderRepository;

	@CircuitBreaker(name = OrderService, fallbackMethod = "orderFallback")

	public ResponseEntity<String> orderFallback(Exception e) {
		return new ResponseEntity<String>("Order Service is down", HttpStatus.OK);

	}

	// Creating/Adding Order
	@PostMapping("/addorder")
	public String saveOrder(@RequestBody Order order) {
		orderService.addOrder(order);
		logger.trace("Save Order Method accessed");
		return "Order is Placed with Washer and will be Proceesed soon " + order;
	}

	// Reading all Order
	@GetMapping("/allorders")
	public List<Order> getOrder() {
		logger.trace("get all Order Method accessed");
		return orderService.getOrders();
	}

	// Reading Order by id
	@GetMapping("/orders/{id}")
	public Optional<Order> getOrderById(@PathVariable int id) throws ApiRequestException {
		return Optional.of(orderRepository.findById(id)
				.orElseThrow(() -> new ApiRequestException("Order NOT FOUND WITH THIS ID ::")));
	}

	// Deleting order by Id
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<Object> deleteOrder(@PathVariable int id) {
		boolean isOrderExist = orderRepository.existsById(id);
		if (isOrderExist) {
			orderService.deleteById(id);
			return new ResponseEntity<Object>("Order deleted with id" + id, HttpStatus.OK);
		} else {
			throw new ApiRequestException("CAN NOT DELETE ORDER ,AS ORDER NOT FOUND WITH THIS ID ::");
		}

	}

	/*
	 * * Below code is for the Order for the Address * Customer can Add Address and
	 * Update Address
	 */

	// For Adding address

	@PostMapping("/addaddress")
	public String addAddress(@RequestBody Address address) {
		return restTemplate.postForObject("http://ADDRESS-SERVICE/address/addaddress", address, String.class);

	}

	// for updating address for or

	@PutMapping("/updateaddress/{id}")
	public String updateaddress(@PathVariable("id") int id, @RequestBody Address address) {
		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		HttpEntity<Address> entity = new HttpEntity<Address>(address, headers);

		return restTemplate.exchange("http://ADDRESS-SERVICE/address/update/" + id, HttpMethod.PUT, entity, String.class)
				.getBody();
	}

}
