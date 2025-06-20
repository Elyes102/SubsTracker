package com.example.substracker.repository;
import com.example.substracker.model.Subscription;
import org.springframework.data.jpa.repository.JpaRepository;
import java.time.LocalDate;
import java.util.List;

public interface SubscriptionRepository extends JpaRepository<Subscription, Long> {
    List<Subscription> findByNextPaymentDate(LocalDate Date);
}
