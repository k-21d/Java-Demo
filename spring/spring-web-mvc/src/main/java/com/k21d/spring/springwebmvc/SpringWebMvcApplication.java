package com.k21d.spring.springwebmvc;

@SpringBootApplication
@ServletComponentScan("com.k21d.spring.springwebmvc.async")
public class SpringWebMvcApplication implements ErrorPageRegistrar {

    public static void main(String[] args) {
        ClassPathXmlApplicationContext classPathXmlApplicationContext = new ClassPathXmlApplicationContext();
        SpringApplication.run(SpringWebMvcApplication.class, args);
    }

    @Override
    public void registerErrorPages(ErrorPageRegistry registry) {
        registry.addErrorPages(new ErrorPage(HttpStatus.NOT_FOUND,"/404.html"));
    }
}
