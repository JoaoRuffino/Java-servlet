package Authenticator;
import java.util.Date;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.SignatureException;

public class Authenticator {
	//Chave secreta para assinatura de token - trocar localização
    private static final String SECRET_KEY = "SecretKey";

	public String CreateToken(String email) {
	    SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;

		String token = Jwts.builder()
				.setSubject(email)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 6000000)) // 100 minutos
                .signWith(SignatureAlgorithm.HS256, SECRET_KEY)
                .compact();
		return token;
	}
	
	public boolean ValidateToken(String token) {
		try {
			Claims claims = Jwts.parser()
	                .setSigningKey(SECRET_KEY)
	                .parseClaimsJws(token)
	                .getBody();
		}catch (SignatureException e) {
			return false;
        }
		
		return false;
	}
}
