package megamek.common.util;

import junit.framework.TestCase;
import megamek.common.util.NumberHelper;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.junit.Assert;

/**
 * @author James Allred (wildj79 at gmail dot com)
 * @version %Id%
 * @since 7/13/2018 4:45 PM
 */
@RunWith(JUnit4.class)
public class NumberHelperTest {
    /** Regular large numbers - generally not problematic */
    @Test
    public void bigFloat() {
        Assert.assertTrue(NumberHelper.nearlyEqual(1000000f, 1000001f));
        Assert.assertTrue(NumberHelper.nearlyEqual(1000001f, 1000000f));
        Assert.assertFalse(NumberHelper.nearlyEqual(10000f, 10001f));
        Assert.assertFalse(NumberHelper.nearlyEqual(10001f, 10000f));
    }

    @Test
    public void bigDouble() {
        Assert.assertTrue(NumberHelper.nearlyEqual(1000000d, 1000001d));
        Assert.assertTrue(NumberHelper.nearlyEqual(1000001d, 1000000d));
        Assert.assertFalse(NumberHelper.nearlyEqual(10000d, 10001d));
        Assert.assertFalse(NumberHelper.nearlyEqual(10001d, 10000d));
    }

    /** Negative large numbers */
    @Test
    public void bigNegFloat() {
        Assert.assertTrue(NumberHelper.nearlyEqual(-1000000f, -1000001f));
        Assert.assertTrue(NumberHelper.nearlyEqual(-1000001f, -1000000f));
        Assert.assertFalse(NumberHelper.nearlyEqual(-10000f, -10001f));
        Assert.assertFalse(NumberHelper.nearlyEqual(-10001f, -10000f));
    }

    @Test
    public void bigNegDouble() {
        Assert.assertTrue(NumberHelper.nearlyEqual(-1000000d, -1000001d));
        Assert.assertTrue(NumberHelper.nearlyEqual(-1000001d, -1000000d));
        Assert.assertFalse(NumberHelper.nearlyEqual(-10000d, -10001d));
        Assert.assertFalse(NumberHelper.nearlyEqual(-10001d, -10000d));
    }

    /** Numbers around 1 */
    @Test
    public void midFloat() {
        Assert.assertTrue(NumberHelper.nearlyEqual(1.0000001f, 1.0000002f));
        Assert.assertTrue(NumberHelper.nearlyEqual(1.0000002f, 1.0000001f));
        Assert.assertFalse(NumberHelper.nearlyEqual(1.0002f, 1.0001f));
        Assert.assertFalse(NumberHelper.nearlyEqual(1.0001f, 1.0002f));
    }

    @Test
    public void midDouble() {
        Assert.assertTrue(NumberHelper.nearlyEqual(1.0000001d, 1.0000002d));
        Assert.assertTrue(NumberHelper.nearlyEqual(1.0000002d, 1.0000001d));
        Assert.assertFalse(NumberHelper.nearlyEqual(1.0002d, 1.0001d));
        Assert.assertFalse(NumberHelper.nearlyEqual(1.0001d, 1.0002d));
    }

    /** Numbers around -1 */
    @Test
    public void midNegFloat() {
        Assert.assertTrue(NumberHelper.nearlyEqual(-1.000001f, -1.000002f));
        Assert.assertTrue(NumberHelper.nearlyEqual(-1.000002f, -1.000001f));
        Assert.assertFalse(NumberHelper.nearlyEqual(-1.0001f, -1.0002f));
        Assert.assertFalse(NumberHelper.nearlyEqual(-1.0002f, -1.0001f));
    }

    @Test
    public void midNegDouble() {
        Assert.assertTrue(NumberHelper.nearlyEqual(-1.000001d, -1.000002d));
        Assert.assertTrue(NumberHelper.nearlyEqual(-1.000002d, -1.000001d));
        Assert.assertFalse(NumberHelper.nearlyEqual(-1.0001d, -1.0002d));
        Assert.assertFalse(NumberHelper.nearlyEqual(-1.0002d, -1.0001d));
    }

    /** Numbers between 1 and 0 */
    @Test
    public void smallFloat() {
        Assert.assertTrue(NumberHelper.nearlyEqual(0.000000001000001f, 0.000000001000002f));
        Assert.assertTrue(NumberHelper.nearlyEqual(0.000000001000002f, 0.000000001000001f));
        Assert.assertFalse(NumberHelper.nearlyEqual(0.000000000001002f, 0.000000000001001f));
        Assert.assertFalse(NumberHelper.nearlyEqual(0.000000000001001f, 0.000000000001002f));
    }

    @Test
    public void smallDouble() {
        Assert.assertTrue(NumberHelper.nearlyEqual(0.000000001000001d, 0.000000001000002d));
        Assert.assertTrue(NumberHelper.nearlyEqual(0.000000001000002d, 0.000000001000001d));
        Assert.assertFalse(NumberHelper.nearlyEqual(0.000000000001002d, 0.000000000001001d));
        Assert.assertFalse(NumberHelper.nearlyEqual(0.000000000001001d, 0.000000000001002d));
    }

    /** Numbers between -1 and 0 */
    @Test
    public void smallNegFloat() {
        Assert.assertTrue(NumberHelper.nearlyEqual(-0.000000001000001f, -0.000000001000002f));
        Assert.assertTrue(NumberHelper.nearlyEqual(-0.000000001000002f, -0.000000001000001f));
        Assert.assertFalse(NumberHelper.nearlyEqual(-0.000000000001002f, -0.000000000001001f));
        Assert.assertFalse(NumberHelper.nearlyEqual(-0.000000000001001f, -0.000000000001002f));
    }

    @Test
    public void smallNegDouble() {
        Assert.assertTrue(NumberHelper.nearlyEqual(-0.000000001000001d, -0.000000001000002d));
        Assert.assertTrue(NumberHelper.nearlyEqual(-0.000000001000002d, -0.000000001000001d));
        Assert.assertFalse(NumberHelper.nearlyEqual(-0.000000000001002d, -0.000000000001001d));
        Assert.assertFalse(NumberHelper.nearlyEqual(-0.000000000001001d, -0.000000000001002d));
    }

    /** Small differences away from zero */
    @Test
    public void smallDiffsFloat() {
        Assert.assertTrue(NumberHelper.nearlyEqual(0.3f, 0.30000003f));
        Assert.assertTrue(NumberHelper.nearlyEqual(-0.3f, -0.30000003f));
    }

    @Test
    public void smallDiffsDouble() {
        Assert.assertTrue(NumberHelper.nearlyEqual(0.3d, 0.30000003d));
        Assert.assertTrue(NumberHelper.nearlyEqual(-0.3d, -0.30000003d));
    }

    /** Comparisons involving zero */
    @Test
    public void zeroFloat() {
        Assert.assertTrue(NumberHelper.nearlyEqual(0.0f, 0.0f));
        Assert.assertTrue(NumberHelper.nearlyEqual(0.0f, -0.0f));
        Assert.assertTrue(NumberHelper.nearlyEqual(-0.0f, -0.0f));
        Assert.assertFalse(NumberHelper.nearlyEqual(0.00000001f, 0.0f));
        Assert.assertFalse(NumberHelper.nearlyEqual(0.0f, 0.00000001f));
        Assert.assertFalse(NumberHelper.nearlyEqual(-0.00000001f, 0.0f));
        Assert.assertFalse(NumberHelper.nearlyEqual(0.0f, -0.00000001f));

        Assert.assertTrue(NumberHelper.nearlyEqual(0.0f, 1e-40f, 0.01f));
        Assert.assertTrue(NumberHelper.nearlyEqual(1e-40f, 0.0f, 0.01f));
        Assert.assertFalse(NumberHelper.nearlyEqual(1e-40f, 0.0f, 0.000001f));
        Assert.assertFalse(NumberHelper.nearlyEqual(0.0f, 1e-40f, 0.000001f));

        Assert.assertTrue(NumberHelper.nearlyEqual(0.0f, -1e-40f, 0.1f));
        Assert.assertTrue(NumberHelper.nearlyEqual(-1e-40f, 0.0f, 0.1f));
        Assert.assertFalse(NumberHelper.nearlyEqual(-1e-40f, 0.0f, 0.00000001f));
        Assert.assertFalse(NumberHelper.nearlyEqual(0.0f, -1e-40f, 0.00000001f));
    }

    @Test
    public void zeroDouble() {
        Assert.assertTrue(NumberHelper.nearlyEqual(0.0d, 0.0d));
        Assert.assertTrue(NumberHelper.nearlyEqual(0.0d, -0.0d));
        Assert.assertTrue(NumberHelper.nearlyEqual(-0.0d, -0.0d));
        Assert.assertFalse(NumberHelper.nearlyEqual(0.00000001d, 0.0d));
        Assert.assertFalse(NumberHelper.nearlyEqual(0.0d, 0.00000001d));
        Assert.assertFalse(NumberHelper.nearlyEqual(-0.00000001d, 0.0d));
        Assert.assertFalse(NumberHelper.nearlyEqual(0.0d, -0.00000001d));

        Assert.assertTrue(NumberHelper.nearlyEqual(0.0d, 1e-40d, 0.01d));
        Assert.assertTrue(NumberHelper.nearlyEqual(1e-40d, 0.0d, 0.01d));
        Assert.assertFalse(NumberHelper.nearlyEqual(1e-40d, 0.0d, 0.000001d));
        Assert.assertFalse(NumberHelper.nearlyEqual(0.0d, 1e-40d, 0.000001d));

        Assert.assertTrue(NumberHelper.nearlyEqual(0.0d, -1e-40d, 0.1d));
        Assert.assertTrue(NumberHelper.nearlyEqual(-1e-40d, 0.0d, 0.1d));
        Assert.assertFalse(NumberHelper.nearlyEqual(-1e-40d, 0.0d, 0.0000001d));
        Assert.assertFalse(NumberHelper.nearlyEqual(0.0d, -1e-40d, 0.00000001d));
    }

    /**
     * Comparisons involving extreme values (overflow potential)
     */
    @Test
    public void extremeMaxFloat() {
        Assert.assertTrue(NumberHelper.nearlyEqual(Float.MAX_VALUE, Float.MAX_VALUE));
        Assert.assertFalse(NumberHelper.nearlyEqual(Float.MAX_VALUE, -Float.MAX_VALUE));
        Assert.assertFalse(NumberHelper.nearlyEqual(-Float.MAX_VALUE, Float.MAX_VALUE));
        Assert.assertFalse(NumberHelper.nearlyEqual(Float.MAX_VALUE, Float.MAX_VALUE / 2));
        Assert.assertFalse(NumberHelper.nearlyEqual(Float.MAX_VALUE, -Float.MAX_VALUE / 2));
        Assert.assertFalse(NumberHelper.nearlyEqual(-Float.MAX_VALUE, Float.MAX_VALUE / 2));
    }

    @Test
    public void extremeMaxDouble() {
        Assert.assertTrue(NumberHelper.nearlyEqual(Double.MAX_VALUE, Double.MAX_VALUE));
        Assert.assertFalse(NumberHelper.nearlyEqual(Double.MAX_VALUE, -Double.MAX_VALUE));
        Assert.assertFalse(NumberHelper.nearlyEqual(-Double.MAX_VALUE, Double.MAX_VALUE));
        Assert.assertFalse(NumberHelper.nearlyEqual(Double.MAX_VALUE, Double.MAX_VALUE / 2));
        Assert.assertFalse(NumberHelper.nearlyEqual(Double.MAX_VALUE, -Double.MAX_VALUE / 2));
        Assert.assertFalse(NumberHelper.nearlyEqual(-Double.MAX_VALUE, Double.MAX_VALUE / 2));
    }

    /**
     * Comparisons involving infinities
     */
    @Test
    public void infinitiesFloat() {
        Assert.assertTrue(NumberHelper.nearlyEqual(Float.POSITIVE_INFINITY, Float.POSITIVE_INFINITY));
        Assert.assertTrue(NumberHelper.nearlyEqual(Float.NEGATIVE_INFINITY, Float.NEGATIVE_INFINITY));
        Assert.assertFalse(NumberHelper.nearlyEqual(Float.NEGATIVE_INFINITY, Float.POSITIVE_INFINITY));
        Assert.assertFalse(NumberHelper.nearlyEqual(Float.POSITIVE_INFINITY, Float.MAX_VALUE));
        Assert.assertFalse(NumberHelper.nearlyEqual(Float.NEGATIVE_INFINITY, -Float.MAX_VALUE));
    }

    @Test
    public void infinitiesDouble() {
        Assert.assertTrue(NumberHelper.nearlyEqual(Double.POSITIVE_INFINITY, Double.POSITIVE_INFINITY));
        Assert.assertTrue(NumberHelper.nearlyEqual(Double.NEGATIVE_INFINITY, Double.NEGATIVE_INFINITY));
        Assert.assertFalse(NumberHelper.nearlyEqual(Double.NEGATIVE_INFINITY, Double.POSITIVE_INFINITY));
        Assert.assertFalse(NumberHelper.nearlyEqual(Double.POSITIVE_INFINITY, Double.MAX_VALUE));
        Assert.assertFalse(NumberHelper.nearlyEqual(Double.NEGATIVE_INFINITY, -Double.MAX_VALUE));
    }

    /**
     * Comparisons involving NaN values
     */
    @Test
    public void nanFloat() {
        Assert.assertFalse(NumberHelper.nearlyEqual(Float.NaN, Float.NaN));
        Assert.assertFalse(NumberHelper.nearlyEqual(Float.NaN, 0.0f));
        Assert.assertFalse(NumberHelper.nearlyEqual(-0.0f, Float.NaN));
        Assert.assertFalse(NumberHelper.nearlyEqual(Float.NaN, -0.0f));
        Assert.assertFalse(NumberHelper.nearlyEqual(0.0f, Float.NaN));
        Assert.assertFalse(NumberHelper.nearlyEqual(Float.NaN, Float.POSITIVE_INFINITY));
        Assert.assertFalse(NumberHelper.nearlyEqual(Float.POSITIVE_INFINITY, Float.NaN));
        Assert.assertFalse(NumberHelper.nearlyEqual(Float.NaN, Float.NEGATIVE_INFINITY));
        Assert.assertFalse(NumberHelper.nearlyEqual(Float.NEGATIVE_INFINITY, Float.NaN));
        Assert.assertFalse(NumberHelper.nearlyEqual(Float.NaN, Float.MAX_VALUE));
        Assert.assertFalse(NumberHelper.nearlyEqual(Float.MAX_VALUE, Float.NaN));
        Assert.assertFalse(NumberHelper.nearlyEqual(Float.NaN, -Float.MAX_VALUE));
        Assert.assertFalse(NumberHelper.nearlyEqual(-Float.MAX_VALUE, Float.NaN));
        Assert.assertFalse(NumberHelper.nearlyEqual(Float.NaN, Float.MIN_VALUE));
        Assert.assertFalse(NumberHelper.nearlyEqual(Float.MIN_VALUE, Float.NaN));
        Assert.assertFalse(NumberHelper.nearlyEqual(Float.NaN, -Float.MIN_VALUE));
        Assert.assertFalse(NumberHelper.nearlyEqual(-Float.MIN_VALUE, Float.NaN));
    }

    @Test
    public void nanDouble() {
        Assert.assertFalse(NumberHelper.nearlyEqual(Double.NaN, Double.NaN));
        Assert.assertFalse(NumberHelper.nearlyEqual(Double.NaN, 0.0d));
        Assert.assertFalse(NumberHelper.nearlyEqual(-0.0d, Double.NaN));
        Assert.assertFalse(NumberHelper.nearlyEqual(Double.NaN, -0.0d));
        Assert.assertFalse(NumberHelper.nearlyEqual(0.0d, Double.NaN));
        Assert.assertFalse(NumberHelper.nearlyEqual(Double.NaN, Double.POSITIVE_INFINITY));
        Assert.assertFalse(NumberHelper.nearlyEqual(Double.POSITIVE_INFINITY, Double.NaN));
        Assert.assertFalse(NumberHelper.nearlyEqual(Double.NaN, Double.NEGATIVE_INFINITY));
        Assert.assertFalse(NumberHelper.nearlyEqual(Double.NEGATIVE_INFINITY, Double.NaN));
        Assert.assertFalse(NumberHelper.nearlyEqual(Double.NaN, Double.MAX_VALUE));
        Assert.assertFalse(NumberHelper.nearlyEqual(Double.MAX_VALUE, Double.NaN));
        Assert.assertFalse(NumberHelper.nearlyEqual(Double.NaN, -Double.MAX_VALUE));
        Assert.assertFalse(NumberHelper.nearlyEqual(-Double.MAX_VALUE, Double.NaN));
        Assert.assertFalse(NumberHelper.nearlyEqual(Double.NaN, Double.MIN_VALUE));
        Assert.assertFalse(NumberHelper.nearlyEqual(Double.MIN_VALUE, Double.NaN));
        Assert.assertFalse(NumberHelper.nearlyEqual(Double.NaN, -Double.MIN_VALUE));
        Assert.assertFalse(NumberHelper.nearlyEqual(-Double.MIN_VALUE, Double.NaN));
    }

    /** Comparisons of numbers on opposite sides of 0 */
    @Test
    public void oppositeFloat() {
        Assert.assertFalse(NumberHelper.nearlyEqual(1.000000001f, -1.0f));
        Assert.assertFalse(NumberHelper.nearlyEqual(-1.0f, 1.000000001f));
        Assert.assertFalse(NumberHelper.nearlyEqual(-1.000000001f, 1.0f));
        Assert.assertFalse(NumberHelper.nearlyEqual(1.0f, -1.000000001f));
        Assert.assertTrue(NumberHelper.nearlyEqual(10 * Float.MIN_VALUE, 10 * -Float.MIN_VALUE));
        Assert.assertFalse(NumberHelper.nearlyEqual(10000 * Float.MIN_VALUE, 10000 * -Float.MIN_VALUE));
    }

    @Test
    public void oppositeDouble() {
        Assert.assertFalse(NumberHelper.nearlyEqual(1.000000001d, -1.0d));
        Assert.assertFalse(NumberHelper.nearlyEqual(-1.0d, 1.000000001d));
        Assert.assertFalse(NumberHelper.nearlyEqual(-1.000000001d, 1.0d));
        Assert.assertFalse(NumberHelper.nearlyEqual(1.0d, -1.000000001d));
        Assert.assertTrue(NumberHelper.nearlyEqual(10 * Double.MIN_VALUE, 10 * -Double.MIN_VALUE));
        Assert.assertFalse(NumberHelper.nearlyEqual(10000 * Double.MIN_VALUE, 10000 * -Double.MIN_VALUE));
    }

    /**
     * The really tricky part - comparisons of numbers very close to zeroFloat.
     */
    @Test
    public void ulpFloat() {
        Assert.assertTrue(NumberHelper.nearlyEqual(Float.MIN_VALUE, Float.MIN_VALUE));
        Assert.assertTrue(NumberHelper.nearlyEqual(Float.MIN_VALUE, -Float.MIN_VALUE));
        Assert.assertTrue(NumberHelper.nearlyEqual(-Float.MIN_VALUE, Float.MIN_VALUE));
        Assert.assertTrue(NumberHelper.nearlyEqual(Float.MIN_VALUE, 0));
        Assert.assertTrue(NumberHelper.nearlyEqual(0, Float.MIN_VALUE));
        Assert.assertTrue(NumberHelper.nearlyEqual(-Float.MIN_VALUE, 0));
        Assert.assertTrue(NumberHelper.nearlyEqual(0, -Float.MIN_VALUE));

        Assert.assertFalse(NumberHelper.nearlyEqual(0.000000001f, -Float.MIN_VALUE));
        Assert.assertFalse(NumberHelper.nearlyEqual(0.000000001f, Float.MIN_VALUE));
        Assert.assertFalse(NumberHelper.nearlyEqual(Float.MIN_VALUE, 0.000000001f));
        Assert.assertFalse(NumberHelper.nearlyEqual(-Float.MIN_VALUE, 0.000000001f));
    }

    @Test
    public void ulpDouble() {
        Assert.assertTrue(NumberHelper.nearlyEqual(Double.MIN_VALUE, Double.MIN_VALUE));
        Assert.assertTrue(NumberHelper.nearlyEqual(Double.MIN_VALUE, -Double.MIN_VALUE));
        Assert.assertTrue(NumberHelper.nearlyEqual(-Double.MIN_VALUE, Double.MIN_VALUE));
        Assert.assertTrue(NumberHelper.nearlyEqual(Double.MIN_VALUE, 0));
        Assert.assertTrue(NumberHelper.nearlyEqual(0, Double.MIN_VALUE));
        Assert.assertTrue(NumberHelper.nearlyEqual(-Double.MIN_VALUE, 0));
        Assert.assertTrue(NumberHelper.nearlyEqual(0, -Double.MIN_VALUE));

        Assert.assertFalse(NumberHelper.nearlyEqual(0.000000001d, -Double.MIN_VALUE));
        Assert.assertFalse(NumberHelper.nearlyEqual(0.000000001d, Double.MIN_VALUE));
        Assert.assertFalse(NumberHelper.nearlyEqual(Double.MIN_VALUE, 0.000000001d));
        Assert.assertFalse(NumberHelper.nearlyEqual(-Double.MIN_VALUE, 0.000000001d));
    }
}
