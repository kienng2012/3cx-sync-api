/**
 * https://openplanning.net/11705/tao-ung-dung-login-voi-spring-boot-spring-security-jpa#a15047054
 * https://levunguyen.com/laptrinhspring/2020/04/21/su-dung-spring-security-trong-spring/
 * <p>
 * Binding using thymeleaf : https://www.baeldung.com/thymeleaf-list
 * Control in thymleaf : https://frontbackend.com/thymeleaf/thymeleaf-utility-methods-for-strings
 * Process form : https://www.codejava.net/frameworks/spring-boot/spring-boot-thymeleaf-form-handling-tutorial
 * <p>
 * Config url in thymeleaf :https://stackoverflow.com/questions/33753975/thymeleaf-using-path-variables-to-thhref
 * https://www.thymeleaf.org/doc/articles/standardurlsyntax.html
 * Paging : https://www.javacodegeeks.com/2013/03/implement-bootstrap-pagination-with-spring-data-and-thymeleaf.html
 * <p>
 * Load Ajax : https://riptutorial.com/thymeleaf/example/28530/replacing-fragments-with-ajax
 */

package com.java;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class KStartupApplication {
    @Value("${api.external.addressBaseUrl}")
    private String addressBaseUrl;

    public static void main(String[] args) {
        SpringApplication.run(KStartupApplication.class, args);
    }

}
