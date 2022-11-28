package com.vtxlab.demo.greeting;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import com.vtxlab.demo.greeting.controller.GreetingOperation;
import com.vtxlab.demo.greeting.repository.BookRepository;
import com.vtxlab.demo.greeting.service.GreetingServiceInterface;

// TDD, test driven design
@SpringBootTest // pretend Springboot Application startup exact situation
// in this example, controller + service will be created in spring context
@AutoConfigureMockMvc
class GreetingApplicationTests {

	@Autowired
	GreetingOperation greetingOperation; //interface作用⋯⋯唔洗用實體class

	@Autowired
	GreetingServiceInterface greetingServiceInterface;

	@Autowired
	BookRepository bookRepository;

	@Autowired //自帶implementation, if not, cannot compile
	MockMvc mockMvc;

	@Test // represent this method will be tested/ran automatically for this test case
	void contextLoads() {
		// Mockito, Junit 5
		assertThat(greetingOperation).isNotNull(); // 1) can know hv compile error or not, 2) in compile time autowired, 3) TTB assume someone wrote restcontroller
    	assertThat(greetingServiceInterface).isNotNull();
		assertThat(bookRepository).isNotNull();
  	}

  	@Test
	void testIntegrationForGreeting() throws Exception {
		mockMvc.perform(get("/api/v1/greeting"))
			.andExpect(status().isOk())
			.andExpect(content().string("abcd"));
	}

}
