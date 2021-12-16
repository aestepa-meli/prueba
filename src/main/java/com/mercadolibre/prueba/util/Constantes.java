package com.mercadolibre.prueba.util;
/**
 * <p>Contantes<p>
 * Clase de constantes del proyecto
 * @author Jhon
 *
 */
public class Constantes {
	
	/**
	 * Constructor privado pues no se deben crear instancias de esta clase
	 */
	private Constantes() {
		throw new UnsupportedOperationException("This class is an utility class");
	}
	
	public static final String JWT_SECRET = "wris5hIQl6nn97gw6DBIF418NJu6hnL-iiCiT9AjAI-BqOAiht53ONMHpsv12mYFiQxKRDcMWhpwNv4xWwPRUWa2MLUqP019MA8f8wbnRbo8dpMe0Oien3Qig0lKJa61uQG903TQEI6dfNtleo75eGCvWSx8M150C2cCFqzeoeMXs5uToXL-FapdJscG_RzvxlULLHewvQOQNqW9uLSNYuRjwDgKQpYUWb28hxHx_fQCWG2Ymo3n-ih3MNpE3RW66GN8-rs_GG55wgX6hjnYC65vmb6ff9w1YKyHBgrBTgxKrUZOr8LtGdtC7L8-1TvnrBeccRnmlnxhLYp6Tk-7Bg";
	public static final String TOKEN_TYPE = "JWT";
	public static final long TOKEN_EXPIRATION_TIME = 3_600_000;
	public static final String HEADER_AUTHORIZACION_KEY = "Authorization";
	public static final String TOKEN_BEARER_PREFIX = "Bearer ";
	public static final String JWT_AUTHENTICATION_ERROR_TEMPLATE = "Request to parse {0} : {1} failed : {2}";

}
