package com.demo.example.student_library_management_system.controller;

import com.demo.example.student_library_management_system.requestdto.TransactionRequestDto;
import com.demo.example.student_library_management_system.service.TransactionServcie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/transaction/apis")
public class TransactionController {

    @Autowired
    private TransactionServcie transactionServcie;

    @PostMapping("/save")
    public String saveTransaction(@RequestBody TransactionRequestDto transactionRequestDto){
        String response = transactionServcie.addTransaction(transactionRequestDto);
        return response;
    }
}
