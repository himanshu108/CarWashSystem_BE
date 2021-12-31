package com.ondemandcarwash.service;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;

import com.ondemandcarwash.controller.OrderController;
import com.ondemandcarwash.model.Order;
import com.ondemandcarwash.repository.OrderRepository;

class OrderServiceTest {
	
	@Mock
	private OrderRepository orderRepository;
	
	@Autowired
	private OrderService orderService;
	
	

	@BeforeEach
	void setUp()  {
		orderRepository = mock(OrderRepository.class);
		orderService = new OrderService();
		
				
	}

	@Test
	void testAddOrder() {
		
		Order order = new Order();
		orderRepository.save(order);
		
		
	}
	
	@Test
	void testListOfOrder() {
		when(orderRepository.findAll()).thenReturn(List.of(new Order()));
				
		//List<Order> order = orderRepository.findAll();
		 //assertThat(2).isEqualTo(order.size());
		
	}
	
	@Test
	void testDeleteById() {
		orderRepository.deleteById(2);
		//Order order = new Order(2,"maruti","suzuki","anil",1,"06/12/2021",8779930443L);	
		
		//verify(orderRepository, times(1)).deleteById(2);
		//Order order = new Order();
		
		// when(orderRepository.findById(2)).thenReturn(Optional.of(order));
		// orderRepository.deleteById(1);
		// assertThat(1).isEqualTo(1);
        // verify(orderRepository, times(1)).deleteById(1);
		
	}
	
	@Test
	public void testgetOrderById() {
		orderRepository.findById(100);
	}

	
	

}