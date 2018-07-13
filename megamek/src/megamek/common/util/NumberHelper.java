/*
 * MegaMek - Copyright (C) 2018 - The MegaMek Team
 *
 * This program is free software; you can redistribute it and/or modify it under
 * the terms of the GNU General Public License as published by the Free Software
 * Foundation; either version 2 of the License, or (at your option) any later
 * version.
 *
 * This program is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU General Public License for more
 * details.
 */

package megamek.common.util;

/**
 * A collection of useful methods used with numbers.
 *  
 * @author James Allred (wildj79 at gmail dot com)
 * @version %Id%
 * @since 7/13/2018 1:51 PM
 */
@SuppressWarnings("FloatingPointEquality")
public class NumberHelper {

    /**
     * Determines if two floating point values are close enough to each other to be considered equal.
     * 
     * Equality comparisons on floating point numbers are problematic. By there nature, they are imprecise 
     * and difficult to compare to each other. Two numbers that look like they should be equal might not return
     * true when using a standard equality comparison. For example:
     * 
     * <code>
     *     float a = 0.15 + 0.15;
     *     float b = 0.1 + 0.2;
     *     
     *     if (a == b) // can be false!
     *     if (a >= b) // can also be false!
     * </code>
     * 
     * This method is derived from code presented at 
     * <a href="https://floating-point-gui.de/errors/comparison/">The Floating-Point Guide</a>.
     * 
     * @param a The first floating point number to compare.
     * @param b The second floating point number to compare.
     * @param epsilon The domain specific floating point number to use in the comparison.
     *                This is the smallest possible margin of error that the two numbers
     *                have to be between and still be considered "equal".
     * @return <code>True</code> if the two numbers are close enough to each other to still
     *         be considered equal.
     */
    public static boolean nearlyEqual(float a, float b, float epsilon) {
        final float absA = Math.abs(a);
        final float absB = Math.abs(b);
        final float diff = Math.abs(a - b);
        
        if (a == b) {
            // Shortcut, handles infinities
            return true;
        } else if (a == 0f || b == 0f || diff < Float.MIN_NORMAL) {
            // a or b is zero or both are extremely close to it
            // relative error is less meaningful here
            return diff < (epsilon * Float.MIN_NORMAL);
        } else {
            // use relative error
            return diff / Math.min((absA + absB), Float.MAX_VALUE) < epsilon;
        }
    }

    /**
     * Determines if two floating point values are close enough to each other to be considered equal.
     *
     * Equality comparisons on floating point numbers are problematic. By there nature, they are imprecise 
     * and difficult to compare to each other. Two numbers that look like they should be equal might not return
     * true when using a standard equality comparison. For example:
     *
     * <code>
     *     float a = 0.15 + 0.15;
     *     float b = 0.1 + 0.2;
     *
     *     if (a == b) // can be false!
     *     if (a >= b) // can also be false!
     * </code>
     *
     * This method is derived from code presented at 
     * <a href="https://floating-point-gui.de/errors/comparison/">The Floating-Point Guide</a>.
     *
     * @param a The first floating point number to compare.
     * @param b The second floating point number to compare.
     * @return <code>True</code> if the two numbers are close enough to each other to still
     *         be considered equal.
     */
    public static boolean nearlyEqual(float a, float b) {
        return nearlyEqual(a, b, 0.00001f);
    }
    
    public static boolean nearlyEqual(double a, double b, double epsilon) {
        final double absA = Math.abs(a);
        final double absB = Math.abs(b);
        final double diff = Math.abs(a - b);
        
        if (a == b) {
            // Shortcut, handles infinities
            return true;
        } else if (a == 0D || b == 0D || diff < Double.MIN_NORMAL) {
            // a or b is zero or both are extremely close to it
            // relative error is less meaningful here
            return diff < (epsilon * Double.MIN_NORMAL);
        } else {
            // use relative error
            return diff / Math.min((absA + absB), Double.MAX_VALUE) < epsilon;
        }
    }
    
    public static boolean nearlyEqual(double a, double b) {
        return nearlyEqual(a, b, 0.00001d);
    }
}
