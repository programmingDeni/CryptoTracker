package com.crypto.tracker.config;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;

import com.crypto.tracker.security.UserPrincipal;
import com.crypto.tracker.security.UserFeature.UserModel;
import com.crypto.tracker.security.UserFeature.UserRepository;
import com.crypto.tracker.services.CoinGeckoService;
import com.crypto.tracker.services.CoinService;
import com.crypto.tracker.services.PriceHistorySchedulerService;

@Profile("!test")
@Component
public class CoinDataInitializer implements CommandLineRunner {

    @Autowired
    private CoinGeckoService coinGeckoService;

    @Autowired
    private CoinService coinService;

    @Autowired
    private UserRepository userRepo;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private PriceHistorySchedulerService priceHistorySchedulerService;

    @Override
    public void run(String... args) throws Exception {
        System.out.println("Erstelle Admin-User...");
        if (userRepo.findByEmail("admin@example.com").isEmpty()) {
            // Admin-User erstellen
            UserModel adminUser = new UserModel();
            adminUser.setEmail("admin@example.com");
            adminUser.setPassword(passwordEncoder.encode("admin123")); // BCrypt-Hash
            adminUser.setFirstName("Admin");
            adminUser.setLastName("User");
            adminUser = userRepo.save(adminUser);
            System.out.println("Admin-User erstellt: " + adminUser.getEmail());
        } else {
            System.out.println("Admin-User existiert bereits, überspringe...");
        }

        // Admin aus DB laden für Templates/Machines
        UserModel adminUser = userRepo.findByEmail("admin@example.com").get();

        UserPrincipal userPrincipal = new UserPrincipal(adminUser.getId(), adminUser.getEmail());
        // Mock Authentication für Admin-User setzen
        // Ermöglicht die Nutzung der Services, die SecurityUtils.getCurrentUserId()
        // verwenden
        UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(userPrincipal, null,
                Collections.emptyList());
        SecurityContextHolder.getContext().setAuthentication(auth);

        if (coinService.getAllCoins().isEmpty()) {
            coinService.fetchAndPersistTopCoins(10, 1, "eur"); // z.B. Top 10 Coins in EUR
            System.out.println("Coin-Daten wurden initial geladen!");
        } else {
            System.out.println("Coin-Daten bereits vorhanden, Initialisierung übersprungen.");
        }

        SecurityContextHolder.clearContext();
    }
}
