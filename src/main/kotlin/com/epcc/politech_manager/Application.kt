package com.epcc.politech_manager

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpMethod
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter
import org.springframework.web.cors.CorsConfiguration
import org.springframework.web.cors.CorsConfigurationSource
import org.springframework.web.cors.CorsUtils
import org.springframework.web.cors.UrlBasedCorsConfigurationSource
import org.springframework.web.servlet.config.annotation.CorsRegistry
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer
import java.util.*


@SpringBootApplication
class Application {
	@Bean
	fun corsConfigurer(): WebMvcConfigurer? {
		return object : WebMvcConfigurer {
			override fun addCorsMappings(registry: CorsRegistry) {
				registry.addMapping("/").allowedOrigins("https://politech-manager.herokuapp.com/")
			}
		}
	}
}

fun main(args: Array<String>) {
	runApplication<Application>(*args)
}

@EnableWebSecurity
@Configuration
internal class WebSecurityConfig : WebSecurityConfigurerAdapter() {
	@Throws(Exception::class)
	override fun configure(http: HttpSecurity) {
		http.cors().configurationSource(corsConfigurationSource()).and().csrf().disable()
				.addFilterAfter(JWTAuthorizationFilter(), UsernamePasswordAuthenticationFilter::class.java)
				.authorizeRequests()
				.requestMatchers(CorsUtils::isPreFlightRequest).permitAll()
				.antMatchers(HttpMethod.POST, "/user/**").permitAll()
				.antMatchers(HttpMethod.PUT, "/user/**").permitAll()
				.anyRequest()
				.authenticated()
	}

	@Bean
	fun corsConfigurationSource(): CorsConfigurationSource? {
		val configuration = CorsConfiguration()
		configuration.allowedOrigins = listOf("https://politech-manager.herokuapp.com/","http://localhost:6000")
		configuration.allowedMethods = listOf("GET", "POST")
		configuration.allowedHeaders = Collections.singletonList("*")
		val source = UrlBasedCorsConfigurationSource()
		source.registerCorsConfiguration("/**", configuration)
		return source
	}
}