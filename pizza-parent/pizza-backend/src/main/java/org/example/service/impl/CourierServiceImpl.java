package org.example.service.impl;

import org.example.entity.AbstractEntity;
import org.example.entity.Courier;
import org.example.service.CourierService;

import javax.ejb.Stateless;
import javax.enterprise.context.ApplicationScoped;

@Stateless
public class CourierServiceImpl extends AbstractServiceImpl<Courier> implements CourierService {
}
