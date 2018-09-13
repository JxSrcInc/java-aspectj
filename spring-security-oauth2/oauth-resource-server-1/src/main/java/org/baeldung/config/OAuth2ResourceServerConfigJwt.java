package org.baeldung.config;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Map;

import org.apache.commons.io.IOUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;

import com.fasterxml.jackson.databind.ObjectMapper;

import jxsource.aspectj.trace.ThreadTrace;

@Configuration
@EnableResourceServer
public class OAuth2ResourceServerConfigJwt extends ResourceServerConfigurerAdapter {
	private static Logger log = LogManager.getLogger(OAuth2ResourceServerConfigJwt.class);

	@Autowired
	private CustomAccessTokenConverter customAccessTokenConverter;

	@Override
	public void configure(final HttpSecurity http) throws Exception {
		// @formatter:off
		http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED).and().authorizeRequests()
				.anyRequest().permitAll();
		// @formatter:on
	}

	@Override
	public void configure(final ResourceServerSecurityConfigurer config) {
		config.tokenServices(tokenServices());
	}

	@Bean
	public TokenStore tokenStore() {
		return new JwtTokenStore(accessTokenConverter());
	}

	// @Bean
	// public JwtAccessTokenConverter accessTokenConverter() {
	// final JwtAccessTokenConverter converter = new JwtAccessTokenConverter();
	// converter.setAccessTokenConverter(customAccessTokenConverter);
	//
	// converter.setSigningKey("123");
	// // final Resource resource = new ClassPathResource("public.txt");
	// // String publicKey = null;
	// // try {
	// // publicKey = IOUtils.toString(resource.getInputStream());
	// // } catch (final IOException e) {
	// // throw new RuntimeException(e);
	// // }
	// // converter.setVerifierKey(publicKey);
	// return converter;
	// }

	@Bean
	public JwtAccessTokenConverter accessTokenConverter() {
		final JwtAccessTokenConverter converter = new JwtAccessTokenConverter();
		converter.setAccessTokenConverter(customAccessTokenConverter);

		String publicKey = null;
		try {
			// get public key from local file
//			 final Resource resource = new ClassPathResource("public.txt");
//			 InputStream in = resource.getInputStream();
//			 publicKey = IOUtils.toString(in);

			// get public key from authorization server
			URL url = new URL("http://localhost:8081/spring-security-oauth-server/oauth/token_key");
			InputStream in = url.openStream();
			publicKey = getPublicKey(in);

			log.debug("pubkey = " + publicKey);
		} catch (final IOException e) {
			throw new RuntimeException(e);
		}
		converter.setVerifierKey(publicKey);

		return converter;
	}

	private String getPublicKey(InputStream in) throws IOException {
		String token = IOUtils.toString(in);
		ObjectMapper mapper = new ObjectMapper();
		// convert token (in json format) to Map
		Map<String, String> map = mapper.readValue(token, Map.class);
		String pKey = map.get("value");
		return pKey;
	}

	@Bean
	@Primary
	public DefaultTokenServices tokenServices() {
		final DefaultTokenServices defaultTokenServices = new DefaultTokenServices();
		defaultTokenServices.setTokenStore(tokenStore());
		return defaultTokenServices;
	}

}
