<<<<<<< HEAD

package it.books_world;

=======
package it.books_world;
>>>>>>> backend_pages

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
