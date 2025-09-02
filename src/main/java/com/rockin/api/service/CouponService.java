package com.rockin.api.service;

import com.rockin.api.domain.coupon.Coupon;
import com.rockin.api.domain.coupon.CouponRequestDTO;
import com.rockin.api.domain.event.Event;
import com.rockin.api.repositories.CouponRepository;
import com.rockin.api.repositories.EventRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CouponService {


    private CouponRepository couponRepository;


    private EventRepository eventRepository;

    public Coupon addCouponToEvent(UUID eventId, CouponRequestDTO couponData) {
        Event event = eventRepository.findById(eventId)
                .orElseThrow(() -> new IllegalArgumentException("Event not found"));

        Coupon coupon = new Coupon();
        coupon.setCode(couponData.code());
        coupon.setDiscount(couponData.discount());
        coupon.setValid(new Date(couponData.valid()));
        coupon.setEvent(event);

        return couponRepository.save(coupon);
    }

    public List<Coupon> consultCoupons(UUID eventId, Date currentDate) {
        return couponRepository.findByEventIdAndValidAfter(eventId, currentDate);
    }

}
