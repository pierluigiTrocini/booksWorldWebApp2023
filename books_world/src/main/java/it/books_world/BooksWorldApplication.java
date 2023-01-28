package it.books_world;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

@SpringBootApplication
@ServletComponentScan
public class BooksWorldApplication {

	public static void main(String[] args) {
		SpringApplication.run(BooksWorldApplication.class, args);
	}

}
