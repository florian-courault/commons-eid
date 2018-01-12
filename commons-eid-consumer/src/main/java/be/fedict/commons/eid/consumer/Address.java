/*
 * Commons eID Project.
 * Copyright (C) 2008-2013 FedICT.
 *
 * This is free software; you can redistribute it and/or modify it
 * under the terms of the GNU Lesser General Public License version
 * 3.0 as published by the Free Software Foundation.
 *
 * This software is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this software; if not, see 
 * http://www.gnu.org/licenses/.
 */

package be.fedict.commons.eid.consumer;

import java.io.Serializable;

import be.fedict.commons.eid.consumer.tlv.OriginalData;
import be.fedict.commons.eid.consumer.tlv.TlvField;

/**
 * Holds all the fields within the eID address file. The nationality can be
 * found in the eID identity file.
 * 
 * @author Frank Cornelis
 * @see Identity
 * 
 */
public class Address implements Serializable {

	@TlvField(1)
	public String streetAndNumber;

	@TlvField(2)
	public String zip;

	@TlvField(3)
	public String municipality;

	@OriginalData
	public byte[] data;

	public String getStreetAndNumber() {
		return streetAndNumber;
	}

	public String getZip() {
		return zip;
	}

	public String getMunicipality() {
		return municipality;
	}

	public byte[] getData() {
		return data;
	}
}
