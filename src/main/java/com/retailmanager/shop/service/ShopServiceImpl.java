package com.retailmanager.shop.service;

import java.util.List;

import javax.inject.Inject;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import com.retailmanager.shop.domain.Shop;
import com.retailmanager.shop.repository.ShopRepository;
import com.retailmanager.shop.service.exception.ShopAlreadyExistsException;

@Service
@Validated
public class ShopServiceImpl implements ShopService {

    private static final Logger LOGGER = LoggerFactory.getLogger(ShopServiceImpl.class);
    private final ShopRepository repository;

    @Inject
    public ShopServiceImpl(final ShopRepository repository) {
        this.repository = repository;
    }

    @Override
    @Transactional
    public Shop save(@NotNull @Valid Shop shop) {
        LOGGER.debug("Creating {}", shop);
        /*Shop existing = repository.findOne(shop.toString());
        if (existing != null) {
            throw new ShopAlreadyExistsException(
                    String.format("There already exists a shop with id=%s", shop.getId()));
        }*/
        return repository.save(shop);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Shop> getList() {
        LOGGER.debug("Retrieving the list of all shops");
        return repository.findAll();
    }
    

}
