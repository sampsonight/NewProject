package tehatava.tehtava23;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import tehatava.tehtava23.domain.Tapahtuma;
import tehatava.tehtava23.domain.TapahtumaRepository;
import tehatava.tehtava23.domain.User;
import tehatava.tehtava23.domain.UserRepository;

@SpringBootApplication
public class TapahtumaApplication {

	private static final Logger log = LoggerFactory.getLogger(TapahtumaApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(TapahtumaApplication.class);
	}

	@Bean
	public CommandLineRunner demo(TapahtumaRepository repository, UserRepository urepository) {
		return (args) -> {
			// save a couple of books
			repository.save(new Tapahtuma("Joulukonsertti", "Kuopion kuoro", "Lauluesitys", 5.00, 2001));
			repository.save(new Tapahtuma("Hartwall Areena", "Vesa Matti Loiri", "Lauluesitys", 25.00, 2014));
			

			// fetch all books
			log.info("Books found with findAll():");
			log.info("-------------------------------");
			for (Tapahtuma Book : repository.findAll()) {
				log.info(Book.toString());
			}
			User user1 = new User("user",
					"$2a$06$3jYRJrg0ghaaypjZ/.g4SethoeA51ph3UD4kZi9oPkeMTpjKU5uo6", "USER");
					User user2 = new User("admin",
					"$2a$10$0MMwY.IQqpsVc1jC8u7IJ.2rT8b0Cd3b3sfIBGV2zfgnPGtT4r0.C", "ADMIN");
					urepository.save(user1);
					urepository.save(user2);

			log.info("");

			// fetch an individual book by ID
			Tapahtuma book = repository.findOne(1L);
			log.info("Book found with findOne(1L):");
			log.info("--------------------------------");
			log.info(book.toString());
			log.info("");

			// fetch books by last name
			log.info("Book found with findByLastName('J.K Rowling'):");
			log.info("--------------------------------------------");
			for (Tapahtuma bauer : repository.findByAuthor("J.K Rowlnig")) {
				log.info(bauer.toString());
				
			}
			
			log.info("");
		};
		
	}

}