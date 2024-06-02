package step_definitions;

import com.github.javafaker.Faker;

public class JavaFakerDemo {

	
	public static void main(String[] args) {
		
		Faker fake = new Faker();
		
		System.err.println(fake.address().streetAddress());

		System.out.println(fake.animal().name());

		System.out.println(fake.internet().emailAddress());

		
		System.out.println(fake.name().fullName());

		System.out.println(fake.commerce().productName());
	}
	
	
}
