/*
 * Commons eID Project.
 * Copyright (C) 2014 - 2018 BOSA.
 *
 * This is free software; you can redistribute it and/or modify it under the
 * terms of the GNU Lesser General Public License version 3.0 as published by
 * the Free Software Foundation.
 *
 * This software is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License
 * for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with this software; if not, see https://www.gnu.org/licenses/.
 */

package be.bosa.commons.eid.client.tests.integration;

import be.bosa.commons.eid.client.BeIDCard;
import be.bosa.commons.eid.client.CancelledException;
import be.bosa.commons.eid.client.impl.CCID;
import org.junit.Test;

public class CCIDExercises extends BeIDTest {
	
	@Test
	public void listCCIDFeatures() throws CancelledException {
		BeIDCard beIDCard = getBeIDCard();
		beIDCard.addCardListener(new TestBeIDCardListener());

		for (CCID.FEATURE feature : CCID.FEATURE.values()) {
			System.out.println(feature.name() + "\t" + (beIDCard.cardTerminalHasCCIDFeature(feature) ? "AVAILABLE" : "NOT AVAILABLE"));
		}
	}

	@Test
	public void listCCIDFeaturesWithPPDU() throws CancelledException {
		CCID.setRiskPPDU(true);
		BeIDCard beIDCard = getBeIDCard();
		beIDCard.addCardListener(new TestBeIDCardListener());

		for (CCID.FEATURE feature : CCID.FEATURE.values()) {
			System.out.println(feature.name() + "\t" + (beIDCard.cardTerminalHasCCIDFeature(feature) ? "AVAILABLE" : "NOT AVAILABLE"));
		}
	}

}
