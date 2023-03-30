package nusiss.paf.day28airbnb;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import nusiss.paf.day28airbnb.Repository.AirbnbRepository;

@SpringBootApplication
public class Day28AirbnbApplication implements CommandLineRunner{

	@Autowired
	private AirbnbRepository airbnbRepo;

	public static void main(String[] args) {
		SpringApplication.run(Day28AirbnbApplication.class, args);
	}

	@Override
	public void run(String... args) {
		airbnbRepo.findAirbnbByCountry("Brazil");
	}

}
