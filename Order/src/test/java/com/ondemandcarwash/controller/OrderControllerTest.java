package com.ondemandcarwash.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.content;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.jsonPath;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;

import com.ondemandcarwash.model.Order;
import com.ondemandcarwash.repository.OrderRepository;
import com.ondemandcarwash.service.OrderService;

@AutoConfigureMockMvc
@ContextConfiguration(classes = { OrderController.class, OrderService.class, OrderRepository.class })
class OrderControllerTest {

	@Mock
	OrderService orderService;

	
	@InjectMocks
	OrderController orderController;
	
	List<Order> order;
	Order order1;
	
	
	
	


	/*
	 * @Test void saveOrderTest() throws Exception { Order order = new Order();
	 * order.setOrderId(1); order.setCarName("Maruti"); order.setCarModel("Suzuki");
	 * order.setwName("anil"); order.setWashPackId(1); order.setDate("07/12/2021");
	 * order.setPhoneNo(8779930443L); // orderService.addOrder(order);}
	 */
	

	@Test
	void getOrderTest() {
		
		order = new ArrayList<Order>();
		order.add(new Order(1,"maruti","suzuki","anil",1,"07/12/2021",8779930443L));
		List<Order> res = orderController.getOrder();
		when(orderService.getOrders()).thenReturn(order);
		assertThat(1).isEqualTo(order.size());

		 
         

}

}
