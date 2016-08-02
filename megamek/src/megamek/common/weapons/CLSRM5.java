/**
 * MegaMek - Copyright (C) 2005 Ben Mazur (bmazur@sev.org)
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
package megamek.common.weapons;

import megamek.common.TechConstants;

/**
 * @author Sebastian Brocks
 */
public class CLSRM5 extends SRMWeapon {

    /**
     *
     */
    private static final long serialVersionUID = 9051765359928076836L;

    /**
     *
     */
    public CLSRM5() {
        super();

        name = "SRM 5";
        setInternalName("CLSRM5");
        addLookupName("Clan SRM-5");
        addLookupName("Clan SRM 5");
        rackSize = 5;
        shortRange = 3;
        mediumRange = 6;
        longRange = 9;
        extremeRange = 12;
        bv = 47;
        flags = flags.or(F_NO_FIRES);
        cost = 80000;
        // Per Herb all ProtoMech launcher use the ProtoMech Chassis progression.
        introDate = 3050;
        techLevel.put(3050, TechConstants.T_CLAN_EXPERIMENTAL);
        techLevel.put(3059, TechConstants.T_CLAN_ADVANCED);
        techLevel.put(3062, TechConstants.T_CLAN_TW);
        availRating = new int[] { RATING_X,RATING_X ,RATING_F ,RATING_D};
        techRating = RATING_F;
        rulesRefs = "231, TM";
    }
}
