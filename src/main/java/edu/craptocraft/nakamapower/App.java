package edu.craptocraft.nakamapower;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.dao.DataIntegrityViolationException;

import edu.craptocraft.nakamapower.entity.Countries;
import edu.craptocraft.nakamapower.entity.Friendships;
import edu.craptocraft.nakamapower.entity.Users;
import edu.craptocraft.nakamapower.service.CountriesService;
import edu.craptocraft.nakamapower.service.FriendshipsService;
import edu.craptocraft.nakamapower.service.UsersService;

@SpringBootApplication
public class App implements CommandLineRunner 
{
    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }

    @Autowired
    private CountriesService serviceCountries;

    @Autowired
    private UsersService serviceUsers;

    @Autowired
    private FriendshipsService serviceFriendships;
    
    @Override
    public void run(String... args) throws Exception {
        try {
            Countries country1 = new Countries("ES", "Spain");
            Countries country2 = new Countries("DE", "Germany");
            Countries country3 = new Countries("RU", "Russia");
            serviceCountries.create(country1);
            serviceCountries.create(country2);
            serviceCountries.create(country3);

            Users user1 = new Users("dragnvindr@favonius.mond", "Diluc Rangvindr", "password", new Countries("DE"));
            Users user2 = new Users("kalberich@favonius.mond", "Kaeya Alberich", "password", new Countries("DE"));
            Users user3 = new Users("atartaglia@fatui.sn", "Tartaglia", "password", new Countries("RU"));
            serviceUsers.create(user1);
            serviceUsers.create(user2);
            serviceUsers.create(user3);

            Friendships friendship1 = new Friendships(new Users(3), new Users(2));
            Friendships friendship2 = new Friendships(new Users(3), new Users(1));
            serviceFriendships.create(friendship1);
            serviceFriendships.create(friendship2);
        } catch (DataIntegrityViolationException e) {
            // Initial data has already been created.
            // Do nothing.
        }
    }

}
