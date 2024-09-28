package com.api.controller;

import com.api.entity.Registration;
import com.api.payload.RegistrationDto;
import com.api.service.RegistrationService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/registration")
public class RegistrationController {

    private RegistrationService registrationService;

    public RegistrationController(RegistrationService registrationService) {
        this.registrationService = registrationService;
    }

    @GetMapping
    public ResponseEntity<List<RegistrationDto>> getAllRegistration(){
        List<RegistrationDto> dtos = registrationService.getRegistration();
        return new ResponseEntity<>(dtos, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> createRegistration(@Valid @RequestBody RegistrationDto registrationDto, BindingResult result){
    if(result.hasErrors()){
        return new ResponseEntity<>(result.getFieldError().getDefaultMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
    }

        RegistrationDto savedReg = registrationService.createRegistration(registrationDto);

        return new ResponseEntity<>(savedReg,HttpStatus.CREATED);
    }

    @DeleteMapping
    public ResponseEntity<String> deleteRegistration(@RequestParam long id){
        registrationService.deleteRegistration(id);

        return new ResponseEntity<>("Deleted",HttpStatus.OK);
    }
    @PutMapping("/{id}")
    public ResponseEntity<RegistrationDto> updateRegistration(@PathVariable long id, @RequestBody RegistrationDto registrationDto)
    {
        RegistrationDto reg = registrationService.updateRegistration(id, registrationDto);
        return new ResponseEntity<>(reg,HttpStatus.OK);
    }
    @GetMapping("/{id}")
    public ResponseEntity<RegistrationDto> getRegistrationById(@PathVariable long id){
            RegistrationDto dtos = registrationService.getRegById(id);
            return new ResponseEntity<>(dtos,HttpStatus.OK);

    }

}

