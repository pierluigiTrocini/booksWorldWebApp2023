<<<<<<< HEAD
package it.books_world.books_world;
=======
package it.books_world;
>>>>>>> backend_pages

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import jakarta.servlet.http.HttpServletRequest;

@Controller
public class ServletUtil {
<<<<<<< HEAD
	
	@RequestMapping(value = "/views/**", method = {RequestMethod.GET, RequestMethod.POST})
=======

    @RequestMapping(value = "/views/**", method = {RequestMethod.GET, RequestMethod.POST})
>>>>>>> backend_pages
	public String templateHandler(HttpServletRequest request) {
		String resource = request.getRequestURI().substring("/views/".length());
		resource = resource.substring(0, resource.indexOf(".html"));
		return resource;
	}
<<<<<<< HEAD
	
}
=======

}
>>>>>>> backend_pages
