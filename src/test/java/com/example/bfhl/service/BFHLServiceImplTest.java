package com.example.bfhl.service;

import com.example.bfhl.dto.BfhlRequest;
import com.example.bfhl.dto.BfhlResponse;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class BFHLServiceImplTest {

    @Autowired
    private BFHLService service;

    @Test
    void testExampleA() {
        BfhlRequest request = new BfhlRequest();
        request.setData(Arrays.asList("a", "1", "334", "4", "R", "$"));

        BfhlResponse response = service.processData(request);

        assertTrue(response.isSuccess());
        assertEquals("339", response.getSum());
        assertEquals(Arrays.asList("1"), response.getOddNumbers());
        assertEquals(Arrays.asList("334", "4"), response.getEvenNumbers());
        assertEquals(Arrays.asList("A", "R"), response.getAlphabets());
        assertEquals(Arrays.asList("$"), response.getSpecialCharacters());
        assertEquals("Ra", response.getConcatString());
    }

    @Test
    void testExampleB() {
        BfhlRequest request = new BfhlRequest();
        request.setData(Arrays.asList("2", "a", "y", "4", "&", "-", "*", "5", "92", "b"));

        BfhlResponse response = service.processData(request);

        assertTrue(response.isSuccess());
        assertEquals("103", response.getSum());
        assertEquals("ByA", response.getConcatString());
        assertEquals(Arrays.asList("2", "4", "92"), response.getEvenNumbers());
        assertEquals(Arrays.asList("5"), response.getOddNumbers());
        assertEquals(Arrays.asList("A", "Y", "B"), response.getAlphabets());
        assertEquals(Arrays.asList("&", "-", "*"), response.getSpecialCharacters());
    }

    @Test
    void testExampleC() {
        BfhlRequest request = new BfhlRequest();
        request.setData(Arrays.asList("A", "ABCD", "DOE"));

        BfhlResponse response = service.processData(request);

        assertTrue(response.isSuccess());
        assertEquals("0", response.getSum());
        assertEquals("EoDdCbAa", response.getConcatString());
        assertEquals(0, response.getEvenNumbers().size());
        assertEquals(0, response.getOddNumbers().size());
        assertEquals(0, response.getSpecialCharacters().size());
        assertEquals(Arrays.asList("A", "ABCD", "DOE"), response.getAlphabets());
    }
}
