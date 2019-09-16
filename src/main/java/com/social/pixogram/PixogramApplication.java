package com.social.pixogram;

import java.util.stream.Stream;

import javax.annotation.Resource;

import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.social.pixogram.model.Blocked;
import com.social.pixogram.model.Follow;
import com.social.pixogram.model.Media;
import com.social.pixogram.model.User;
import com.social.pixogram.repo.BlockedRepo;
import com.social.pixogram.repo.FollowRepo;
import com.social.pixogram.repo.MediaRepo;
import com.social.pixogram.repo.UserRepo;
import com.social.pixogram.service.StorageService;

@SpringBootApplication
public class PixogramApplication implements CommandLineRunner {

	@Resource
	StorageService storageService;
	@Resource
	MediaRepo mediaRepo;

	public static void main(String[] args) {
		SpringApplication.run(PixogramApplication.class, args);
	}
	
	@Bean
	ApplicationRunner init(UserRepo userRepo) {
		return args -> {
			Stream.of(new User("Deepa", "deepa@gmail.com", "deepa"), new User("Sahit", "sahit@gmail.com", "sahit"),
					new User("Dharani", "dharu@gmail.com", "dharani"), new User("Dhana", "Dhana@gmail.com", "dhana"))
					.forEach(user -> {
						userRepo.save(user);
					});
			userRepo.findAll().forEach(System.out::println);
		};
	}

	@Bean
	ApplicationRunner init1(MediaRepo mediaRepo, StorageService storageService) {
		return args -> {
			Stream.of(new Media(1, "dummy1.jpg", -1, 45, 8), new Media(1, "dummy2.jpg", -1, 56, 7)).forEach(media -> {
						mediaRepo.save(media);
					});
			mediaRepo.findAll().forEach(System.out::println);
		};
	}

	@Bean
	ApplicationRunner init2(FollowRepo followRepo) {
		return args -> {
			Stream.of(new Follow(6, 2), new Follow(2,6), new Follow(1, 4), new Follow(1, 10), new Follow(1, 3))
					.forEach(follow -> {
						followRepo.save(follow);
					});
			followRepo.findAll().forEach(System.out::println);
		};
	}

	@Bean
	ApplicationRunner init3(BlockedRepo blockedRepo) {
		return args -> {
			Stream.of(new Blocked(1, 6), new Blocked(1, 3), new Blocked(2, 9), new Blocked(2, 7), new Blocked(10, 1),
					new Blocked(10, 3), new Blocked(9, 1), new Blocked(8, 10)).forEach(blocked -> {
						blockedRepo.save(blocked);
					});
			blockedRepo.findAll().forEach(System.out::println);
		};
	}

	@Override
	public void run(String... arg) throws Exception {
		//storageService.deleteAll();
		storageService.init();
	}

}
