/* MegaMek - Copyright (C) 2004,2005 Ben Mazur (bmazur@sev.org)
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

import megamek.common.AmmoType;
import megamek.common.TechAdvancement;
import megamek.common.weapons.NavalGaussWeapon;

/**
 * @author Jay Lawson
 */
public class CLNGaussWeaponHeavy extends NavalGaussWeapon {
    /**
     * 
     */
    private static final long serialVersionUID = 8756042527483383101L;

    /**
     * 
     */
    public CLNGaussWeaponHeavy() {
        super();
        this.name = "Heavy N-Gauss (Clan)";
        this.setInternalName(this.name);
        this.addLookupName("CLHeavyNGauss");
        this.heat = 18;
        this.damage = 30;
        this.ammoType = AmmoType.T_HEAVY_NGAUSS;
        this.shortRange = 12;
        this.mediumRange = 24;
        this.longRange = 36;
        this.extremeRange = 48;
        this.tonnage = 7 - 00.0f;
        this.bv = 6048;
        this.cost = 50050000;
        this.shortAV = 30;
        this.medAV = 30;
        this.longAV = 30;
        this.extAV = 30;
        this.maxRange = RANGE_EXT;
        techAdvancement.setTechBase(TechAdvancement.TECH_BASE_CLAN);
        techAdvancement.setClanAdvancement(DATE_NONE, 2820, DATE_NONE);
        techAdvancement.setTechRating(RATING_E);
        techAdvancement.setAvailability( new int[] { RATING_X, RATING_E, RATING_E, RATING_X });
    }
}
//Commented out in Weapontype. Tech progression in IS weapon covers these.