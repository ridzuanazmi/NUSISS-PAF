package nusiss.paf.revisionday28workshop;

import java.util.List;

import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import nusiss.paf.revisionday28workshop.Repository.GameCommentRepository;

@SpringBootApplication
public class RevisionDay28workshopApplication implements CommandLineRunner{

	@Autowired
	private GameCommentRepository gcRepo;

	public static void main(String[] args) {
		SpringApplication.run(RevisionDay28workshopApplication.class, args);
	}

	@Override
	public void run(String... args) {

		// To check if the method getAvgRating is working
		// Double avg = gcRepo.getAvgRating(40);
		// System.out.printf("The average rating is %f for gid of %d",avg, 40);

		// Check if method getHighesRatedComment is working
		List<Document> comment = gcRepo.getGamesWithHighestLowestRatings("highest");
		System.out.printf(">>>> Highes rated %s",comment);
	}
}
