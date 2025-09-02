package com.rockin.api.service;

import com.rockin.api.domain.address.Address;
import com.rockin.api.domain.event.Event;
import com.rockin.api.domain.event.EventRequestDTO;
import com.rockin.api.repositories.AddressRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AddressService {


    public AddressRepository addressRepository;

    public Address createAddress(EventRequestDTO data, Event event) {
        Address address = new Address();
        address.setCity(data.city());
        address.setUf(data.state());
        address.setEvent(event);

        return addressRepository.save(address);
    }


}
