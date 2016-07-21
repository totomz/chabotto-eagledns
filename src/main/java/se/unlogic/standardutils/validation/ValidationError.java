/*******************************************************************************
 * Copyright (c) 2010 Robert "Unlogic" Olofsson (unlogic@unlogic.se).
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the GNU Lesser Public License v3
 * which accompanies this distribution, and is available at
 * http://www.gnu.org/licenses/lgpl-3.0-standalone.html
 ******************************************************************************/
package se.unlogic.standardutils.validation;

import se.unlogic.standardutils.xml.GeneratedElementable;
import se.unlogic.standardutils.xml.XMLElement;

//TODO fix case...
@XMLElement(name="validationError")
public class ValidationError extends GeneratedElementable{

	@XMLElement
	private final String fieldName;

	@XMLElement
	private final String displayName;

	@XMLElement
	private final ValidationErrorType validationErrorType;

	@XMLElement
	private final String messageKey;

	public ValidationError(String fieldName, ValidationErrorType validationErrorType, String messageKey) {
		super();
		this.fieldName = fieldName;
		this.validationErrorType = validationErrorType;
		this.messageKey = messageKey;
		this.displayName = null;
	}

	public ValidationError(String fieldName, ValidationErrorType validationErrorType) {
		super();
		this.fieldName = fieldName;
		this.validationErrorType = validationErrorType;
		this.displayName = null;
		this.messageKey = null;
	}

	public ValidationError(String fieldName, String displayName, ValidationErrorType validationErrorType) {
		super();
		this.fieldName = fieldName;
		this.displayName = displayName;
		this.validationErrorType = validationErrorType;
		this.messageKey = null;
	}

	public ValidationError(String messageKey) {
		super();

		this.fieldName = null;
		this.displayName = null;
		this.validationErrorType = null;

		this.messageKey = messageKey;
	}

	public String getFieldName() {
		return fieldName;
	}

	public ValidationErrorType getValidationErrorType() {
		return validationErrorType;
	}

	public String getMessageKey() {
		return messageKey;
	}

	@Override
	public String toString() {

		return "ValidationError [" + (fieldName != null ? "fieldName=" + fieldName + ", " : "") + (validationErrorType != null ? "validationErrorType=" + validationErrorType + ", " : "") + (messageKey != null ? "messageKey=" + messageKey : "") + "]";
	}


	public String getDisplayName() {

		return displayName;
	}
}
