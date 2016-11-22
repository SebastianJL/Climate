package ch.uzh.ifi.climate.shared;

import static org.junit.Assert.*;

import org.junit.Test;

public class CoordinatesTest {
	private final float TEST_LATITUDE = 3.2f;
	private final float TEST_LONGITUDE = 8.0f;

	@Test
	public void testCoordinates() {
		Coordinates coord = new Coordinates();
		assertNotNull(coord);
	}

	@Test
	public void testCoordinatesFloatFloat() {
		Coordinates coord = new Coordinates(TEST_LATITUDE, TEST_LONGITUDE);
		assertNotNull(coord);
	}

	@Test
	public void testGetLatitude() {
		Coordinates coord = new Coordinates(TEST_LATITUDE, TEST_LONGITUDE);
		assertEquals(TEST_LATITUDE, coord.getLatitude(), 0);
	}

	@Test
	public void testGetLongitude() {
		Coordinates coord = new Coordinates(TEST_LATITUDE, TEST_LONGITUDE);
		assertEquals(TEST_LONGITUDE, coord.getLongitude(), 0);
	}

}
