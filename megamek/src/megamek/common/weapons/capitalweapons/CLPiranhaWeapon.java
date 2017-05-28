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
import megamek.common.IGame;
import megamek.common.TechAdvancement;
import megamek.common.ToHitData;
import megamek.common.actions.WeaponAttackAction;
import megamek.common.weapons.AttackHandler;
import megamek.common.weapons.PiranhaHandler;
import megamek.server.Server;

/**
 * @author Jay Lawson
 */
public class CLPiranhaWeapon extends SubCapitalMissileWeapon {
    /**
     * 
     */
    private static final long serialVersionUID = 3827228773282489872L;

    /**
     * 
     */
    public CLPiranhaWeapon() {
        super();
        name = "Piranha (Clan)";
        setInternalName(name);
        addLookupName("CLPiranha");
        heat = 9;
        damage = 3;
        ammoType = AmmoType.T_PIRANHA;
        shortRange = 7;
        mediumRange = 14;
        longRange = 21;
        extremeRange = 28;
        tonnage = 100.0f;
        bv = 670;
        cost = 75000;
        shortAV = 3;
        medAV = 3;
        longAV = 3;
        maxRange = RANGE_LONG;
        flags = flags.or(F_AERO_WEAPON).or(F_MISSILE);
        techAdvancement.setTechBase(TechAdvancement.TECH_BASE_CLAN);
        techAdvancement.setClanAdvancement(DATE_NONE, 3070, 3072);
        techAdvancement.setTechRating(RATING_F);
        techAdvancement.setAvailability( new int[] { RATING_X, RATING_X, RATING_F, RATING_X });
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * megamek.common.weapons.Weapon#getCorrectHandler(megamek.common.ToHitData,
     * megamek.common.actions.WeaponAttackAction, megamek.common.IGame)
     */
    @Override
    protected AttackHandler getCorrectHandler(ToHitData toHit,
            WeaponAttackAction waa, IGame game, Server server) {
        return new PiranhaHandler(toHit, waa, game, server);
    }
}