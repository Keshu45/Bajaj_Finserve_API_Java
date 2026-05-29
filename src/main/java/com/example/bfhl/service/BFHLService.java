package com.example.bfhl.service;

import com.example.bfhl.dto.BfhlRequest;
import com.example.bfhl.dto.BfhlResponse;

public interface BFHLService {
    BfhlResponse processData(BfhlRequest request);
}
