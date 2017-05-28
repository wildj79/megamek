/*
 * MegaMek - Copyright (C) 2004,2005 Ben Mazur (bmazur@sev.org)
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
/*
 * Created on Sep 25, 2004
 *
 */
package megamek.common.weapons.capitalweapons;

import megamek.common.weapons.NavalPPCWeapon;

/**
 * @author Jay Lawson
 */
public class ISNPPCWeaponMedium extends NavalPPCWeapon {
    /**
    * 
    */
    private static final long serialVersionUID = 8756042527483383101L;

    /**
    * 
    */
    public ISNPPCWeaponMedium() {
        super();
        this.name = "Naval PPC (Medium)";
        this.setInternalName(this.name);
        this.addLookupName("MediumNPPC");
        this.heat = 135;
        this.damage = 9;
        this.shortRange = 12;
        this.mediumRange = 24;
        this.longRange = 36;
        this.extremeRange = 48;
        this.tonnage = 1800.0f;
        this.bv = 2268;
        this.cost = 3250000;
        this.shortAV = 9;
        this.medAV = 9;
        this.longAV = 9;
        this.extAV = 9;
        this.maxRange = RANGE_EXT;
        rulesRefs = "333,TO";
        techAdvancement.setTechBase(TECH_BASE_ALL)
        	.setIntroLevel(false)
        	.setUnofficial(false)
            .setTechRating(RATING_D)
            .setAvailability(RATING_D, RATING_X, RATING_E, RATING_E)
            .setISAdvancement(2350, 2356, DATE_NONE, 2950, 3052)
            .setISApproximate(true, true, false, true, false)
            .setClanAdvancement(2350, 2356, DATE_NONE, DATE_NONE, DATE_NONE)
            .setClanApproximate(true, true, false,false, false)
            .setProductionFactions(F_TH)
            .setReintroductionFactions(F_DC);
    }
}