package net.slipp.domain.users;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.junit.BeforeClass;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UserTest {
	private static final Logger log = LoggerFactory.getLogger(UserTest.class);

	private static Validator validator;

	@BeforeClass
	public static void setUp() {
		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
		validator = factory.getValidator();
	}
	
	@Test
	public void userIdWhenIsEmpty() throws Exception {
		User user = new User("", "1111", "프리티카라", "prettykara@gmail.com");
		
		Set<ConstraintViolation<User>> constraintViolations = validator.validate(user);
		assertThat(constraintViolations.size(), is(2));
		
		for (ConstraintViolation<User> constraintViolation : constraintViolations) {
			log.debug("violation error message: {}", constraintViolation.getMessage());
		}
	}

	@Test
	public void matchPassword() throws Exception {
		String password = "1111";
		Authenticate authenticate = new Authenticate("prettykara", password);
		User user = new User("prettykara", password, "프리티카라", "prettykara@gmail.com");
		
		assertTrue(user.matchMassword(authenticate));
		
		 authenticate = new Authenticate("prettykara", "2222");
		 assertFalse(user.matchMassword(authenticate));
	}
}
