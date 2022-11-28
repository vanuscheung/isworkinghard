package com.vtxlab.demo.greeting;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.vtxlab.demo.greeting.controller.GreetingOperation;
import com.vtxlab.demo.greeting.controller.impl.GreetingController;
import com.vtxlab.demo.greeting.service.GreetingServiceInterface;

//import org.springframework.boot.test.context.SpringBootTest;

@ExtendWith(SpringExtension.class) // @Mock, @InjectMocks
public class GreetingControllerUnitTest {
    
    @Mock // no implementation injected in this reference 冇實體class
    GreetingServiceInterface greetingServiceInterface;

    private GreetingOperation greetingOperation;

    @BeforeEach
    void setup() {
        greetingOperation = new GreetingController(greetingServiceInterface);
    }

    private void testHelloworld(String input, String output, String notEqualOutput) {
        // Mockito
        // Given
        Mockito.when(greetingServiceInterface.greeting()).thenReturn(input);
        // When
        String string = greetingOperation.greeting();
        // Then
        if (output != null) {
            Assertions.assertThat(string).isEqualTo(output);
        }
        if (notEqualOutput != null) {
            Assertions.assertThat(string).isNotEqualTo(notEqualOutput);
        }
    }

    @Test
    void testCases() {
        testHelloworld("hello world", "hello worldd", "hello");
        testHelloworld("", "d", "c");
        testHelloworld("abc", "abcd", "bcda");
    }
}
