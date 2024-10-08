package Authenticator;
import java.util.Date;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.Claims;

public class Authenticator {
	//Chave secreta para assinatura de token - trocar localização
    private static final String SECRET_KEY = "SecretKey";

	public static String createToken(String email) {
	    SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;

		String token = Jwts.builder()
				.setSubject(email)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 6000000)) // 100 minutos
                .signWith(signatureAlgorithm, SECRET_KEY)
                .compact();
		return token;
	}
	
	public static Claims validateToken(String token) {
			Claims claims = Jwts.parser()
	                .setSigningKey(SECRET_KEY)
	                .parseClaimsJws(token)
	                .getBody();
			
			return claims;
			}
		
	public static boolean isTokenExpired(Claims claims) {
        return claims.getExpiration().before(new Date());
    }
}
