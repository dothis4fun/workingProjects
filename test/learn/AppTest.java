/**
 * 
 */
package learn;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assumptions.assumeTrue;
import static org.junit.jupiter.api.Assumptions.assumingThat;

import java.time.LocalDateTime;
import java.util.function.Supplier;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.platform.commons.logging.Logger;
import org.junit.platform.commons.logging.LoggerFactory;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;

//
//import org.junit.jupiter.api.BeforeAll;
//import org.junit.jupiter.api.Disabled;
//import org.junit.jupiter.api.DisplayName;
//import org.junit.platform.commons.logging.Logger;
//import org.junit.platform.commons.logging.LoggerFactory;
//import org.junit.platform.runner.JUnitPlatform;
//import org.junit.runner.RunWith;

/**
 * @author Kyle
 *
 */
//@Disabled
@RunWith(JUnitPlatform.class)
@DisplayName("Testing using JUnit 5")
public class AppTest {
	private static final Logger log = LoggerFactory.getLogger(AppTest.class);
	
	//fixture for the class under test
	
	private App classUnderTest;
	
	// run this before any test is run
	@BeforeAll
	public static void init() {
		//Do Something before any test is run in this class
		Supplier<String> i  = ()-> "@BeforeAll: (outer); init()";
		log.info(i);
	}
	
	@BeforeEach
	public void setUp() throws Exception{
		Supplier<String> i  = ()-> "@BeforeEach: (outer); init()";
		log.info(i);
		classUnderTest = new App();
	}
	
	@AfterEach
	public void tearDown() throws Exception{
		Supplier<String> i  = ()-> "@AfterEach: (outer); init()";
		log.info(i);
		classUnderTest = null;
	}
	
	// do something after all tests in this class are run
	@AfterAll
	public static void done() {
		Supplier<String> i  = ()-> "@AfterAll: (outer); init()";
		log.info(i);
		
	}
	
	
	@Disabled
	@DisplayName("Disabled test")
	void testNotRun() {
		
	}
	
	
	@Test
	@DisplayName("When numbers > 0")
	public void testAdd() {
		Supplier<String> i  = ()-> "@Test: (outer); testAdd()";
		log.info(i);
		assertNotNull(classUnderTest);
		assertAll(
		() -> {
			long[] numbersToSum = {1,2,3,4};
			long expectedSum = 10;
			long actualSum = classUnderTest.add(numbersToSum);
			assertEquals(expectedSum, actualSum);
		},
		() ->{
			long[] numbersToSum = {20,934,110};
			long expectedSum = 1064;
			long actualSum = classUnderTest.add(numbersToSum);
			assertEquals(expectedSum, actualSum);
		},
		() -> {
			long[] numbersToSum = {2,4,6};
			long expectedSum = 12;
			long actualSum = classUnderTest.add(numbersToSum);
			assertEquals(expectedSum, actualSum);
		});
	}
	
	@Nested
	@DisplayName("When numbers to add are < 0")
	class NegativeNumbersTest{
		private App classUnderTest;
		@BeforeEach
		public void setUp() throws Exception{
			Supplier<String> i  = ()-> "@BeforeEach: @Nested (NegativeNumbersTest); init()";
			log.info(i);
			classUnderTest = new App();
		}
		
		@AfterEach
		public void tearDown() throws Exception{
			Supplier<String> i  = ()-> "@AfterEach: @Nested (NegativeNumbersTest); init()";
			log.info(i);
			classUnderTest = null;
		}
		
		@Test
		@DisplayName("When numbers are < 0")
		public void testAdd() {
			Supplier<String> i  = ()-> "@Test: @Nested (NegativeNumbersTest); testAdd()";
			log.info(i);
			assertNotNull(classUnderTest);
			assertAll(
			() -> {
				long[] numbersToSum = {-1,-2,-3,-4};
				long expectedSum = -10;
				long actualSum = classUnderTest.add(numbersToSum);
				assertEquals(expectedSum, actualSum);
			},
			() ->{
				long[] numbersToSum = {-20,-934,-110};
				long expectedSum = -1064;
				long actualSum = classUnderTest.add(numbersToSum);
				assertEquals(expectedSum, actualSum);
			},
			() -> {
				long[] numbersToSum = {-2,-4,-6};
				long expectedSum = -12;
				long actualSum = classUnderTest.add(numbersToSum);
				assertEquals(expectedSum, actualSum);
			});
		}
	}
	
	@Nested
	@DisplayName("When numbers to add are both positive and negative")
	class PositiveAndNegativeNumbersTest {
		
		@Test
		@DisplayName("When numbers are both > and < 0")
		public void testAdd() {
			Supplier<String> i  = ()-> "@Test: @Nested: (PositiveAndNegativeNumbersTest); testAdd()";
			log.info(i);
			assertNotNull(classUnderTest);
			assertAll(
			() -> {
				long[] numbersToSum = {-1,2,-3,4};
				long expectedSum = 2;
				long actualSum = classUnderTest.add(numbersToSum);
				assertEquals(expectedSum, actualSum);
			},
			() ->{
				long[] numbersToSum = {-20,934,-110};
				long expectedSum = 804;
				long actualSum = classUnderTest.add(numbersToSum);
				assertEquals(expectedSum, actualSum);
			},
			() -> {
				long[] numbersToSum = {-2,-4,6};
				long expectedSum = 0;
				long actualSum = classUnderTest.add(numbersToSum);
				assertEquals(expectedSum, actualSum);
			});
		}
		
		@Test
		@DisplayName("This test only runs on Friday")
		public void testAdd_OnlyOnFriday() {
			Supplier<String> i  = ()-> "@Test: @Nested: (PositiveAndNegativeNumbersTest); testAdd_OnlyOnFriday()";
			log.info(i);
			LocalDateTime ldt = LocalDateTime.now();
			assumeTrue(ldt.getDayOfWeek().getValue() == 5);
			long[] operands = {1,2,3,4,5};
			long expectedSum = 15;
			long actualSum = classUnderTest.add(operands);
			assertEquals(expectedSum, actualSum);
		}
		
		
		public void testAdd_OnlyOnFriday_withLambda() {
			Supplier<String> i  = ()-> "@Test: @Nested: (PositiveAndNegativeNumbersTest); testAdd_OnlyOnFriday_WithLambda()";
			log.info(i);
			LocalDateTime ldt = LocalDateTime.now();
			assumingThat(ldt.getDayOfWeek().getValue() == 5,
					() -> {
						long[] operands = {1,2,3,4,5};
						long expectedSum = 15;
						long actualSum = classUnderTest.add(operands);
						assertEquals(expectedSum, actualSum);
					});
			// rest of code that executes even if not true
		}
	}
	@Nested
	@DisplayName("When using a single operand")
	class JUnit5AppSingleOperandTest{
		
		@Test
		@DisplayName("Numbers > 0")
		public void testAdd_NumbersGt0() {
			assertNotNull(classUnderTest);
			//Supplier<String> i  = ()-> "@Test: @Nested: (PositiveAndNegativeNumbersTest); testAdd()";
			//log.info(i);
			assertNotNull(classUnderTest);
			assertAll(
			() -> {
				long[] numbersToSum = {0};
				long expectedSum = 0;
				long actualSum = classUnderTest.add(numbersToSum);
				assertEquals(expectedSum, actualSum);
			},
			() ->{
				long[] numbersToSum = {1};
				long expectedSum = 1;
				long actualSum = classUnderTest.add(numbersToSum);
				assertEquals(expectedSum, actualSum);
			});
		}
		
		@Test
		@DisplayName("Numbers > 0")
		public void testAdd_NumbersLt0() {
			assertNotNull(classUnderTest);
			//Supplier<String> i  = ()-> "@Test: @Nested: (PositiveAndNegativeNumbersTest); testAdd()";
			//log.info(i);
			assertNotNull(classUnderTest);
			assertAll(
			() -> {
				long[] numbersToSum = {-1};
				long expectedSum = -1;
				long actualSum = classUnderTest.add(numbersToSum);
				assertEquals(expectedSum, actualSum);
			},
			() ->{
				long[] numbersToSum = {-10};
				long expectedSum = -10;
				long actualSum = classUnderTest.add(numbersToSum);
				assertEquals(expectedSum, actualSum);
			});
		}
	}
	
	@Nested
	@DisplayName("When zero or null operands")
	class Junit5AppZeroOperandsTest {
		
		@Test
		@DisplayName("Empty argument")
		public void testAdd_ZeroOperands_EmpyArgument() {
			assertNotNull(classUnderTest);
			long[] operands = {};
			assertThrows(IllegalArgumentException.class, () -> classUnderTest.add(operands));
		}
		
		
		@Test
		@DisplayName("Null argument")
		public void testAdd_ZeroOperands_NullArgument() {
			assertNotNull(classUnderTest);
			long[] operands = null;
			assertThrows(IllegalArgumentException.class, () -> classUnderTest.add(operands));
		}
	}
	
	
}
