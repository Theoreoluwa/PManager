package com.theoreoluwa.property.propertymanagement.service.impl;

import com.theoreoluwa.property.propertymanagement.DTO.PropertyDTO;
import com.theoreoluwa.property.propertymanagement.converter.PropertyConverter;
import com.theoreoluwa.property.propertymanagement.entity.PropertyEntity;
import com.theoreoluwa.property.propertymanagement.repository.PropertyRepository;
import com.theoreoluwa.property.propertymanagement.service.PropertyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PropertyServiceImpl implements PropertyService {

    @Value("${pms.dummy:}")
    private String dummy;

    @Value("${spring.datasource.url:}")
    private String dbUrl;

    @Autowired
    private PropertyRepository propertyRepository;

    @Autowired
    private PropertyConverter propertyConverter;

    @Override
    public PropertyDTO createProperty(PropertyDTO propertyDTO) {

        PropertyEntity pe = propertyConverter.convertDTOtoEntity(propertyDTO);
        pe = propertyRepository.save(pe);

        propertyDTO = propertyConverter.convertEntityToDTO(pe);
        return propertyDTO;
    }

    @Override
    public List<PropertyDTO> getAllProperties() {

        System.out.println("Inside service" + dummy);

        List<PropertyEntity> listOfProperties = (List<PropertyEntity>) propertyRepository.findAll();
        List<PropertyDTO> propsList = new ArrayList<>();

        for(PropertyEntity pe : listOfProperties){
            PropertyDTO dto = propertyConverter.convertEntityToDTO(pe);
            propsList.add(dto);
        }

        return propsList;
    }

    @Override
    public PropertyDTO updateProperty(PropertyDTO propertyDTO, Long propertyId) {
        Optional<PropertyEntity> optionalEntity = propertyRepository.findById(propertyId);
        PropertyDTO dto = null;

        if(optionalEntity.isPresent()){
            PropertyEntity pe = optionalEntity.get();//get existing data from database
            pe.setTitle(propertyDTO.getTitle());
            pe.setDescription(propertyDTO.getDescription());
            pe.setAddress(propertyDTO.getAddress());
            pe.setPrice(propertyDTO.getPrice());
            pe.setOwnerName(propertyDTO.getOwnerName());
            pe.setOwnerEmail(propertyDTO.getOwnerEmail());
            dto = propertyConverter.convertEntityToDTO(pe);
            propertyRepository.save(pe);
        }

        return dto;
    }

    @Override
    public PropertyDTO updatePropertyDescription(PropertyDTO propertyDTO, Long propertyId) {
        Optional<PropertyEntity> optionalEntity = propertyRepository.findById(propertyId);
        PropertyDTO dto = null;
        if(optionalEntity.isPresent()){
            PropertyEntity pe = optionalEntity.get();//get data from db
            pe.setDescription(propertyDTO.getDescription());
            dto = propertyConverter.convertEntityToDTO(pe);
            propertyRepository.save(pe);
        }
        return dto;
    }

    @Override
    public PropertyDTO updatePropertyPrice(PropertyDTO propertyDTO, Long propertyId) {
        Optional<PropertyEntity> optionalEntity = propertyRepository.findById(propertyId);
        PropertyDTO dto = null;
        if(optionalEntity.isPresent()){
            PropertyEntity pe = optionalEntity.get();//get entity from db
            pe.setPrice(propertyDTO.getPrice());
            dto = propertyConverter.convertEntityToDTO(pe);
            propertyRepository.save(pe);
        }
        return dto;
    }

    @Override
    public void deleteProperty(Long propertyId) {
        propertyRepository.deleteById(propertyId);
    }
}
