package tech.chorume.bot.commands;

import java.util.logging.Logger;

import org.junit.jupiter.api.*;


public class CodigoBuilderTest {
	
	private static final Logger log = Logger.getLogger(CodigoBuilderTest.class.getName());

	@BeforeAll
	static void setup() {
	    log.info("@BeforeAll - executes once before all test methods in this class");
	}

	@BeforeEach
	void init() {
	    log.info("@BeforeEach - executes before each test method in this class");
	}
	

}
