package com.api.service;

import com.api.entity.Registration;
import com.api.exception.ResourceNotFoundException;
import com.api.payload.RegistrationDto;
import com.api.repository.RegistrationRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RegistrationService {
    private RegistrationRepository registrationRepository;
    private ModelMapper modelMapper;
    public RegistrationService(RegistrationRepository registrationRepository, ModelMapper modelMapper) {
        this.registrationRepository = registrationRepository;
        this.modelMapper = modelMapper;
    }


    public List<RegistrationDto> getRegistration(){
         List<Registration> registration = registrationRepository.findAll();
        List<RegistrationDto> dtos = registration.stream().map(r -> mapToDto(r)).collect(Collectors.toList());
        return dtos;

        //return registrationRepository.findAll();
    }

    public RegistrationDto createRegistration(RegistrationDto registrationDto) {
        //copy from dto to entity
        Registration registration = mapToEntity(registrationDto);
//        Registration registration = new Registration();
//        registration.setName(registrationDto.getName());
//        registration.setEmail(registrationDto.getEmail());
//        registration.setMobile(registrationDto.getMobile());

        Registration savedEntity = registrationRepository.save(registration);
        //copy entity to Dto
        RegistrationDto dto = mapToDto(savedEntity);
//        RegistrationDto dto = new RegistrationDto();
//        dto.setName(savedEntity.getName());
//        dto.setEmail(savedEntity.getEmail());
//        dto.setMobile(savedEntity.getMobile());
        return dto;
    }

    public void deleteRegistration(long id) {
        registrationRepository.deleteById(id);
    }


    public RegistrationDto updateRegistration(long id, RegistrationDto registrationDto) {

        Registration registration = new Registration();
        registration.setName(registrationDto.getName());
        registration.setEmail(registrationDto.getEmail());
        registration.setMobile(registrationDto.getMobile());

        Registration reg = registrationRepository.findById(id).get();
        reg.setName(registrationDto.getName());
        reg.setEmail(registrationDto.getEmail());
        reg.setMobile(registrationDto.getMobile());

        Registration savedEntity = registrationRepository.save(reg);

        registrationDto.setName(savedEntity.getName());
        registrationDto.setEmail(savedEntity.getEmail());
        registrationDto.setMobile(savedEntity.getMobile());
        
        return registrationDto;
    }

    Registration mapToEntity(RegistrationDto registrationDto){
        Registration registration = modelMapper.map(registrationDto, Registration.class);
//        Registration registration = new Registration();
//        registration.setName(registrationDto.getName());
//        registration.setEmail(registrationDto.getEmail());
//        registration.setMobile(registrationDto.getMobile());
        return registration;
    }
    RegistrationDto mapToDto(Registration registration){
        RegistrationDto dto = modelMapper.map(registration, RegistrationDto.class);
//        RegistrationDto dto = new RegistrationDto();
//        dto.setName(registration.getName());
//        dto.setEmail(registration.getEmail());
//        dto.setMobile(registration.getMobile());
        return dto;
    }

    public RegistrationDto getRegById(long id) {
        Registration registration = registrationRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Record not found")
        );
        return mapToDto(registration);
    }
}
