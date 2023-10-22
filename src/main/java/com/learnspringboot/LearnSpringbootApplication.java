package com.learnspringboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.learnspringboot.controllers.HeroController;
import com.learnspringboot.loaders.LazyLoader;
import com.learnspringboot.models.Dragon;
import com.learnspringboot.models.Hero;
import com.learnspringboot.models.Weapon;
import com.learnspringboot.scopes.PrototypeBean;
import com.learnspringboot.scopes.SingletonBean;
import com.learnspringboot.services.HeroService;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;

@SpringBootApplication
@OpenAPIDefinition(info = @Info(title = "Library System Rest API", version = "1.0.0", description = "This Project is about system in the library", termsOfService = "VikIService", contact = @Contact(name = "Viki", email = "viki.fernando2@gmail.com"), license = @License(name = "License", url = "Viki.com")))

public class LearnSpringbootApplication {

	public static void main(String[] args) {
		SpringApplication.run(LearnSpringbootApplication.class, args);
	}

	public static void test() {
		// var context = SpringApplication.run(LearnSpringbootApplication.class, args);

		// HeroController heroController2 = (HeroController)
		// context.getBean("heroController");
		// HeroService heroService = context.getBean(HeroService.class);
		// LazyLoader lazyLoader = context.getBean(LazyLoader.class);

		// System.out.println(heroController2.getAllHero());
		// System.out.println(heroController2.getHeroName());

		// System.out.println("--------------------");
		// heroController2.saveHeroIntoFile();
		// heroController2.loadHeroFromFile();

		// System.out.println("--------------------");
		// SingletonBean singletonBean1 = context.getBean(SingletonBean.class);
		// System.out.println(singletonBean1.hashCode());
		// SingletonBean singletonBean2 = context.getBean(SingletonBean.class);
		// System.out.println(singletonBean2.hashCode());
		// SingletonBean singletonBean3 = context.getBean(SingletonBean.class);
		// System.out.println(singletonBean3.hashCode());

		// System.out.println("--------------------");
		// PrototypeBean prototypeBean1 = context.getBean(PrototypeBean.class);
		// System.out.println(prototypeBean1.hashCode());
		// PrototypeBean prototypeBean2 = context.getBean(PrototypeBean.class);
		// System.out.println(prototypeBean2.hashCode());
		// PrototypeBean prototypeBean3 = context.getBean(PrototypeBean.class);
		// System.out.println(prototypeBean3.hashCode());

		// Weapon weapon = context.getBean(Weapon.class);
		// Weapon weapon2 = new Weapon();
		// Dragon dragon = context.getBean(Dragon.class);

		// SystemVariable variable = context.getBean(SystemVariable.class);
		// System.out.println(Weapon.weapon1.name());
		// System.out.println(weapon2.name());
		// System.out.println(variable.javaHome());
		// System.out.println(variable.tempDir());
		// System.out.println(variable.email());
		// System.out.println(heroController2.getLegendaryHeroes());

		// System.out.println(dragon.getName());
		// System.out.println(dragon.getPower());
		// System.out.println(dragon.getRarity());
	}
}
