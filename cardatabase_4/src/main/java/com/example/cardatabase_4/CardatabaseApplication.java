package com.example.cardatabase_4;

//import org.springframework.boot.SpringApplication;
//import org.springframework.boot.autoconfigure.SpringBootApplication;
//
//@SpringBootApplication
//public class Cardatabase4Application {
//
//	public static void main(String[] args) {
//		SpringApplication.run(Cardatabase4Application.class, args);
//	}
//
//}
//
import com.example.cardatabase_4.domain.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Arrays;


@SpringBootApplication
public class CardatabaseApplication implements CommandLineRunner {
	private static final Logger logger = LoggerFactory.getLogger(
			CardatabaseApplication.class
	);

	private final CarRepository repository;
	private final OwnerRepository ownerRepository;
	private final AppUserRepository userRepository;

	public CardatabaseApplication(CarRepository repository, OwnerRepository ownerRepository, AppUserRepository userRepository) {
		this.repository = repository;
		this.ownerRepository = ownerRepository;
		this.userRepository = userRepository;
	}


	public static void main(String[] args) {
		SpringApplication.run(CardatabaseApplication.class, args);
		logger.info("Application Started ! / 애플리케이션이 실행되었습니다.");
	}

	//CommandLineRunner 인터페이스의 추상메서드인 run()을 여기서 구현하는거네요.
	@Override
	public void run(String... args) throws Exception {
		// 소유자 객체를 추가
		Owner owner1 = new Owner("일", "김");
		Owner owner2 = new Owner("이", "강");
		// 다수의 객체를 한 번에 저장하는 메서드 처음 사용해보겠습니다.
		ownerRepository.saveAll(Arrays.asList(owner1, owner2));

		// 그리고 Car의 생성자에 field를 추가했기 때문에 오류 나는 것을 막기 위해 owner들을 추가해주겠습니다.
		repository.save(new Car("Kia", "Seltos", "Chacol", "370SU5690", 2020, 30000000, owner1));
		repository.save(new Car("Hyundai", "Sonata", "White", "123456", 2025, 25000000, owner2));
		repository.save(new Car("Honda", "CR-V", "Black-White", "987654", 2024, 45000000, owner2));


		for(Car car: repository.findAll()) {
			logger.info("brand : {}, model : {}", car.getBrand(), car.getBrand());
		}

		// AppUser 더미 데이터를 추가
		// 저 위에 보시면 Owner의 경우에는 owner1 / owner2 만들어가지고 ownerRepository에 저장했었습니다.
		userRepository.save(new AppUser("user", "$2a$12$09VVceH9RYMJcPcqTTAu8OFuQV5UCCXLNOHq3H0xTClCYY8RdrQii", "USER" ));
		userRepository.save(new AppUser("admin", "$2a$12$QSu1fWMkvNjcO7FBj8Qv6ON4dVGFn8OGoTqXBRMMPFtb4KrSKOtzi", "ADMIN"));

	}
}
