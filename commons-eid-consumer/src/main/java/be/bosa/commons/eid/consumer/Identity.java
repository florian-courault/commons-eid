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

package be.bosa.commons.eid.consumer;

import be.bosa.commons.eid.consumer.tlv.ChipNumberDataConvertor;
import be.bosa.commons.eid.consumer.tlv.ConvertData;
import be.bosa.commons.eid.consumer.tlv.DateOfBirthDataConvertor;
import be.bosa.commons.eid.consumer.tlv.DocumentTypeConvertor;
import be.bosa.commons.eid.consumer.tlv.GenderDataConvertor;
import be.bosa.commons.eid.consumer.tlv.OriginalData;
import be.bosa.commons.eid.consumer.tlv.SpecialOrganisationConvertor;
import be.bosa.commons.eid.consumer.tlv.SpecialStatusConvertor;
import be.bosa.commons.eid.consumer.tlv.TlvField;
import be.bosa.commons.eid.consumer.tlv.ValidityDateDataConvertor;

import java.io.Serializable;
import java.util.GregorianCalendar;

/**
 * Holds all fields within the eID identity file. The annotations are used by
 * the TLV parser to parse the identity file as stored in the eID card to an
 * object of this class.
 * 
 * @author Frank Cornelis
 * @see Address
 * @see TlvField
 * @see ConvertData
 * 
 */
public class Identity implements Serializable {
	@TlvField(1)
	public String cardNumber;

	@TlvField(2)
	@ConvertData(ChipNumberDataConvertor.class)
	public String chipNumber;

	@TlvField(3)
	@ConvertData(ValidityDateDataConvertor.class)
	public GregorianCalendar cardValidityDateBegin;

	@TlvField(4)
	@ConvertData(ValidityDateDataConvertor.class)
	public GregorianCalendar cardValidityDateEnd;

	@TlvField(5)
	public String cardDeliveryMunicipality;

	@TlvField(6)
	public String nationalNumber;

	@TlvField(7)
	public String name;

	@TlvField(8)
	public String firstName;

	@TlvField(9)
	public String middleName;

	@TlvField(10)
	public String nationality;

	@TlvField(11)
	public String placeOfBirth;

	@TlvField(12)
	@ConvertData(DateOfBirthDataConvertor.class)
	public GregorianCalendar dateOfBirth;

	@TlvField(13)
	@ConvertData(GenderDataConvertor.class)
	public Gender gender;

	/**
	 * Optional Noble Condition.
	 */
	@TlvField(14)
	public String nobleCondition;

	@TlvField(15)
	@ConvertData(DocumentTypeConvertor.class)
	public DocumentType documentType;

	@TlvField(16)
	@ConvertData(SpecialStatusConvertor.class)
	public SpecialStatus specialStatus;

	@TlvField(17)
	public byte[] photoDigest;

	@TlvField(18)
	public String duplicate;

	@TlvField(19)
	@ConvertData(SpecialOrganisationConvertor.class)
	public SpecialOrganisation specialOrganisation;

	@TlvField(20)
	public boolean memberOfFamily;

	@OriginalData
	public byte[] data;

	public String getCardNumber() {
		return this.cardNumber;
	}

	public String getChipNumber() {
		return this.chipNumber;
	}

	public GregorianCalendar getCardValidityDateBegin() {
		return this.cardValidityDateBegin;
	}

	public GregorianCalendar getCardValidityDateEnd() {
		return this.cardValidityDateEnd;
	}

	public String getCardDeliveryMunicipality() {
		return this.cardDeliveryMunicipality;
	}

	public String getNationalNumber() {
		return this.nationalNumber;
	}

	public String getName() {
		return this.name;
	}

	public String getFirstName() {
		return this.firstName;
	}

	public String getMiddleName() {
		return this.middleName;
	}

	public String getNationality() {
		return this.nationality;
	}

	public String getPlaceOfBirth() {
		return this.placeOfBirth;
	}

	public GregorianCalendar getDateOfBirth() {
		return this.dateOfBirth;
	}

	public Gender getGender() {
		return this.gender;
	}

	public String getNobleCondition() {
		return this.nobleCondition;
	}

	public DocumentType getDocumentType() {
		return this.documentType;
	}

	public byte[] getPhotoDigest() {
		return this.photoDigest;
	}

	public SpecialStatus getSpecialStatus() {
		return this.specialStatus;
	}

	public String getDuplicate() {
		return this.duplicate;
	}

	public boolean isMemberOfFamily() {
		return this.memberOfFamily;
	}

	public SpecialOrganisation getSpecialOrganisation() {
		return this.specialOrganisation;
	}

	public byte[] getData() {
		return this.data;
	}

	@Override
	public String toString() {
		return "[" + this.name + " " + this.firstName + "]";
	}
}
