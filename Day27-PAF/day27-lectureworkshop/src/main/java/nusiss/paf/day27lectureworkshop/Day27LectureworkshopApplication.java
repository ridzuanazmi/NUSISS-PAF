package nusiss.paf.day27lectureworkshop;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import nusiss.paf.day27lectureworkshop.Repository.ShowsRepository;

@SpringBootApplication
public class Day27LectureworkshopApplication implements CommandLineRunner{

	@Autowired
	private ShowsRepository showsRepo;

	public static void main(String[] args) {
		SpringApplication.run(Day27LectureworkshopApplication.class, args);
	}

	@Override
	public void run(String... args) {
		System.out.printf(">>> %s\n", showsRepo.findAllTvShow());	
	}
}
