package com.vtxlab.demo.greeting;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
//import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.log;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.ContentResultMatchers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.result.StatusResultMatchers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.vtxlab.demo.greeting.entity.Book;
import com.vtxlab.demo.greeting.repository.BookRepository;
import com.vtxlab.demo.greeting.service.GreetingServiceInterface;

import lombok.extern.slf4j.Slf4j;

@WebMvcTest
@Slf4j
// start spring context, but with Controller related bean ONLY! (ie no service bean)
public class GreetingControllerIntegrationTest {

    @MockBean //create a new bean to spring context
    GreetingServiceInterface greetingServiceInterface;

    @MockBean
    BookRepository bookRepository;

    @Autowired //becoz we have WebMvcTest, the mockMvc Bean has been loaded to context
    MockMvc mockMvc; // similar to postman for testing

    @Test
    void testWebMvc() throws Exception {
        //Given
        Mockito.when(greetingServiceInterface.greeting()).thenReturn("hello world");
        //When
        MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.get("/api/v1/greeting");
        //Execute the call
        ResultActions response = mockMvc.perform(builder);
        //check if the http response is 200
        StatusResultMatchers status = MockMvcResultMatchers.status();
        ResultMatcher statusOk = status.isOk(); //200
        response.andExpect(statusOk);

        ContentResultMatchers content = MockMvcResultMatchers.content();
        ResultMatcher contentHelloworld = content.string("hello worldd");
        response.andExpect(contentHelloworld);
    }

    @Test
    void testWebMvc2() throws Exception {
        when(greetingServiceInterface.greeting()).thenReturn("hello world");
        mockMvc.perform(get("/api/v1/greeting")) //
            .andExpect(status().isOk())
            .andExpect(content().string("hello worldd"));
    }

    @Test
    void testFindAllBooks() throws Exception {
        Book book = new Book(1l, "Her Book", LocalDate.of(2022, Month.JUNE, 2));

        List<Book> books = new ArrayList<>();
        books.add(book);

        when(greetingServiceInterface.findAllBook()).thenReturn(books);

        //mockMvc.perform(get("/api/v1/books")).andExpect(status().isOk()).andExpect(jsonPath("$.size()").value(1));
        MvcResult result = mockMvc.perform(get("/api/v1/books"))
            .andDo(print())
            .andExpect(status().isOk()).andReturn();
        //.andExpect(jsonPath("$.length()").value(1));
        String string = result.getResponse().getContentAsString();
        log.info(string);

        ObjectMapper objectMapper = new ObjectMapper();
        //deserialization
        List<Book> bookList = objectMapper.readValue(string, List.class);
        assertThat(bookList.size()).isEqualTo(1);
    
    }
    
}
