package edu.craptocraft.nakamapower.entity;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class CountriesTest {
    
    private Countries country;
    
    @Before
    public void setUp() {
        // Create a country object for testing
        country = new Countries("US", "United States");
    }

    @Test
    public void testGetCode() {
        assertEquals("US", country.getCode());
    }

    @Test
    public void testSetCode() {
        country.setCode("UK");
        assertEquals("UK", country.getCode());
    }

    @Test
    public void testGetCountry() {
        assertEquals("United States", country.getCountry());
    }

    @Test
    public void testSetCountry() {
        country.setCountry("United Kingdom");
        assertEquals("United Kingdom", country.getCountry());
    }

    @Test
    public void testEquals() {
        Countries sameCountry = new Countries("US", "United States");
        Countries differentCountry = new Countries("UK", "United Kingdom");

        assertTrue(country.equals(sameCountry));
        assertFalse(country.equals(differentCountry));
    }

    @Test
    public void testHashCode() {
        Countries sameCountry = new Countries("US", "United States");

        assertEquals(country.hashCode(), sameCountry.hashCode());
    }
}
