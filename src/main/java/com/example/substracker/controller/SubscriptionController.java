package com.example.substracker.controller;
import org.springframework.security.core.Authentication;
import com.example.substracker.model.User;
import com.example.substracker.model.Subscription;
import com.example.substracker.services.SubscriptionService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/subscriptions")
public class SubscriptionController {
        public final SubscriptionService service;

        public SubscriptionController(SubscriptionService service) {
            this.service = service;
        }

    @GetMapping
    public List<Subscription> getAll(Authentication authentication) {
        User user = (User) authentication.getPrincipal();
        return service.getSubscriptionsByUser(user);
    }

    @PostMapping
    public Subscription create(@RequestBody Subscription subscription, Authentication authentication) {
        User user = (User) authentication.getPrincipal();
        subscription.setUser(user);
        return service.saveSubscription(subscription);
    }


    @GetMapping("/{id}")
    public ResponseEntity<Subscription> getById(@PathVariable Long id) {
            return service.getSubscriptionById(id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
            if(service.getSubscriptionById(id).isEmpty()) {
                return ResponseEntity.notFound().build();
            }
            service.deleteSubscriptionById(id);
            return ResponseEntity.noContent().build();
    }
}
