package com.example.bfhl.service;

import com.example.bfhl.dto.BfhlRequest;
import com.example.bfhl.dto.BfhlResponse;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

@Service
public class BFHLServiceImpl implements BFHLService {

    private static final String FULL_NAME = "keshav_patidar";
    private static final String DOB_FORMATTED = "04072004"; // Example format: ddmmyyyy
    private static final String EMAIL = "Ksv@gmail.com";
    private static final String ROLL_NUMBER = "0827AB6747";

    @Override
    public BfhlResponse processData(BfhlRequest request) {
        BfhlResponse response = new BfhlResponse();
        response.setSuccess(true);
        response.setUserId(FULL_NAME + "_" + DOB_FORMATTED);
        response.setEmail(EMAIL);
        response.setRollNumber(ROLL_NUMBER);

        List<String> oddNumbers = new ArrayList<>();
        List<String> evenNumbers = new ArrayList<>();
        List<String> alphabets = new ArrayList<>();
        List<String> specialCharacters = new ArrayList<>();
        BigInteger sum = BigInteger.ZERO;
        
        StringBuilder allAlphabeticChars = new StringBuilder();

        if (request.getData() != null) {
            for (String item : request.getData()) {
                if (item == null || item.trim().isEmpty()) {
                    continue;
                }
                
                // Track purely numerical entries
                if (item.matches("^\\d+$")) {
                    BigInteger number = new BigInteger(item);
                    sum = sum.add(number);
                    
                    // Check odd/even using remainder
                    if (number.remainder(BigInteger.TWO).equals(BigInteger.ZERO)) {
                        evenNumbers.add(item);
                    } else {
                        oddNumbers.add(item);
                    }
                } 
                // Track purely alphabetical entries
                else if (item.matches("^[a-zA-Z]+$")) {
                    alphabets.add(item.toUpperCase());
                } 
                // Everything else goes to special characters
                else {
                    specialCharacters.add(item);
                }
                
                // Extract individual characters for the concat string operation
                for (char c : item.toCharArray()) {
                    if (Character.isLetter(c)) {
                        allAlphabeticChars.append(c);
                    }
                }
            }
        }

        response.setOddNumbers(oddNumbers);
        response.setEvenNumbers(evenNumbers);
        response.setAlphabets(alphabets);
        response.setSpecialCharacters(specialCharacters);
        response.setSum(sum.toString());
        response.setConcatString(generateAlternatingReverseString(allAlphabeticChars.toString()));

        return response;
    }

    private String generateAlternatingReverseString(String input) {
        // Reverse the extracted characters
        String reversed = new StringBuilder(input).reverse().toString();
        
        StringBuilder result = new StringBuilder();
        boolean toUpper = true;
        
        // Apply alternating caps: Upper, Lower, Upper, Lower...
        for (char c : reversed.toCharArray()) {
            if (toUpper) {
                result.append(Character.toUpperCase(c));
            } else {
                result.append(Character.toLowerCase(c));
            }
            toUpper = !toUpper;
        }
        
        return result.toString();
    }
}
