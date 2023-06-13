package app.auth;

import java.io.IOException;

import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class CorsFilter extends OncePerRequestFilter {

//	Versione 1 con Wildcard
//	@Override
//	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
//			throws ServletException, IOException {
//		response.setHeader("Access-Control-Allow-Origin", "*");
//		
//	}

//	Versione 2
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		// L'endopoint dovremmo inserirla nell'application.properties
		// "http://localhost:3000"
		response.setHeader("Access-Control-Allow-Origin", "http://localhost:3000");
		response.setHeader("Access-Control-Allow-Headers", "*");
		// Abilita alcuni metodi piuttosto che altri o tutti con wildcard
		response.setHeader("Access-Control-Allow-methods", "*");
		// Per dieci minuti non fare la prerichiesta per controllare se il cors è
		// abilitato o meno
		response.setHeader("Access-Control-Allow-Max-Age", "600");

		if (request.getMethod().equalsIgnoreCase("OPTIONS")) {
			response.setStatus(HttpServletResponse.SC_OK);
		} else {
			filterChain.doFilter(request, response);

		}
	}

}
