package com.example.childrens_world.service;

import com.example.childrens_world.model.dto.ChargeRequest;
import com.stripe.Stripe;
import com.stripe.exception.*;
import com.stripe.model.Charge;
import org.springframework.beans.factory.annotation.Value;

import javax.annotation.PostConstruct;

public interface PaymentService {

    Charge pay(ChargeRequest chargeRequest) throws APIConnectionException, APIException, AuthenticationException, InvalidRequestException, CardException;
}
