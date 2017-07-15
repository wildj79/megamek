/**
 * MegaMek -
 * Copyright (C) 2000,2001,2002,2003,2004,2005,2006,2007 Ben Mazur (bmazur@sev.org)
 *
 *  This program is free software; you can redistribute it and/or modify it
 *  under the terms of the GNU General Public License as published by the Free
 *  Software Foundation; either version 2 of the License, or (at your option)
 *  any later version.
 *
 *  This program is distributed in the hope that it will be useful, but
 *  WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY
 *  or FITNESS FOR A PARTICULAR PURPOSE. See the GNU General Public License
 *  for more details.
 */
package megamek.common.weapons.primitive;

import megamek.common.TechAdvancement;
import megamek.common.weapons.ppc.PPCWeapon;

/**
 * @author Deric "Netzilla" Page (deric dot page at usa dot net)
 */
public class ISPPCPrimitive extends PPCWeapon {
    /**
     *
     */
    private static final long serialVersionUID = 1767670595802648539L;

    public ISPPCPrimitive() {
        super();

        name = "Primitive Prototype PPC";
        setInternalName(name);
        addLookupName("Particle Cannon Primitive");
        addLookupName("IS PPCp");
        addLookupName("ISPPCp");
        heat = 15;
        damage = 10;
        minimumRange = 3;
        shortRange = 6;
        mediumRange = 12;
        longRange = 18;
        extremeRange = 24;
        waterShortRange = 4;
        waterMediumRange = 7;
        waterLongRange = 10;
        waterExtremeRange = 14;
        setModes(new String[]{"Field Inhibitor ON", "Field Inhibitor OFF"});
        tonnage = 7.0f;
        criticals = 3;
        bv = 176;
        cost = 200000;
        shortAV = 10;
        medAV = 10;
        maxRange = RANGE_MED;
        // with a capacitor
        explosive = true;
        //IO Doesn't strictly define when these weapons stop production so assigning a value of ten years.
        rulesRefs = "217, IO";
        techAdvancement.setTechBase(TechAdvancement.TECH_BASE_IS);
        techAdvancement.setISAdvancement(2439, DATE_NONE, DATE_NONE, 2470);
        techAdvancement.setTechRating(RATING_C);
        techAdvancement.setAvailability( new int[] { RATING_F, RATING_X, RATING_X, RATING_X });
    }
}