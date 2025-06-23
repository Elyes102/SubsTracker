package com.example.substracker.repository;

import com.example.substracker.model.Subscription;
import com.example.substracker.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface SubscriptionRepository extends JpaRepository<Subscription, Long> {
    List<Subscription> findByNextPaymentDate(LocalDate date);
    List<Subscription> findByUser(User user);

    User user(User user);
}