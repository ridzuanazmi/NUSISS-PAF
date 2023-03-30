package nusiss.paf.day28lecture;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import nusiss.paf.day28lecture.Repository.TvShowRepository;

@SpringBootApplication
public class Day28LectureApplication implements CommandLineRunner{

	@Autowired
	private TvShowRepository tvShowRepo;

	public static void main(String[] args) {
		SpringApplication.run(Day28LectureApplication.class, args);
	}

	@Override
	public void run(String... args) {
		// tvShowRepo.findTvShowByType("Scripted");
		// tvShowRepo.groupShowsByTimezone();
		// tvShowRepo.summarizeTvSHowsByType("Scripted");
		tvShowRepo.summarizeTvSHowsByType("Scripted");
	}

}
