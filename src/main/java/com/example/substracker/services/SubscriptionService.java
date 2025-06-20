package com.example.substracker.services;

import com.example.substracker.model.Subscription;
import com.example.substracker.repository.SubscriptionRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class SubscriptionService {
    private final SubscriptionRepository repository;

    public SubscriptionService(SubscriptionRepository repository) {
        this.repository = repository;
    }

    public List<Subscription> getAllSubscriptions(){
        return repository.findAll();
    }
    public Optional<Subscription> getSubscriptionById(long id) {
        return repository.findById(id);
    }

    public Subscription saveSubscription(Subscription subscription) {
        return repository.save(subscription);
    }


    public void deleteSubscription(Subscription subscription) {
        repository.delete(subscription);
    }

    public void deleteSubscriptionById(long id) {
        repository.deleteById(id);
    }

    public List<Subscription> getSubscriptionsDueOn(LocalDate date) {
        return repository.findByNextPaymentDate(date);
    }

}
