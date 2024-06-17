package org.lotka.xenonx.presentation.form_validator

import android.webkit.URLUtil
import org.ainarm.chat.presentation.R

import org.lotka.xenonx.presentation.extension.toEng

/*
use isOptional() first
*/

open class FormField {
    var isValid: Boolean = true
}

class IntFormField(val value: Int?) : FormField() {

    fun notNull(): IntFormField {
        isValid = value?.let { isValid } ?: false
        return this
    }
}

class StringFormField(val value: String?) : FormField() {

    var item = value?.trim()
    private var errorMessage: Int? = null
    private var optional: Boolean = false

    fun validate(value: String?): StringFormField {
        item = value
        return this
    }

    fun notNull(): StringFormField {
        isValid = item?.let { isValid } ?: false
        return this
    }

    fun notEmpty(): StringFormField {
        item?.let {
            isValid = if (it.isEmpty()) false else isValid
        }
        return this
    }

    fun notZero(): StringFormField {
        item?.let {
            isValid = if (it == "0") {
                errorMessage = R.string.value_is_not_valid
                false
            } else isValid
        }
        return this
    }


    fun notBlank(): StringFormField {
        item?.let {
            isValid = if (it.isBlank()) false else isValid
        }
        return this
    }

    fun maxLength(maxLength: Int): StringFormField {
        item?.let {
            if (optional && (it.isEmpty() || it.isBlank())) { // field is optional and can be empty or blank, in this situation there is no need to validation
                return this
            } else {
                if (it.length > maxLength) { // field is just contains digits and is valid
                    errorMessage = R.string.length_is_more_than_max
                    isValid = false
                }
            }
        }
        return this
    }

    fun minLength(minLength: Int): StringFormField {
        item?.let {
            if (optional && (it.isEmpty() || it.isBlank())) { // field is optional and can be empty or blank, in this situation there is no need to validation
                return this
            } else {
                if (it.length < minLength) { // field is just contains digits and is valid
                    errorMessage = R.string.length_is_less_than_min
                    isValid = false
                }
            }
        }
        return this
    }

    fun digitsOnly(): StringFormField {
        item?.let {
            if (optional && (it.isEmpty() || it.isBlank())) { // field is optional and can be empty or blank, in this situation there is no need to validation
                return this
            } else {
                if (!(it.toEng().matches("^[0-9]+".toRegex()))
                ) { // field is just contains digits and is valid
                    errorMessage = R.string.just_use_numbers
                    isValid = false
                    return this
                }
            }
        }
        return this
    }


    fun isOptional(): StringFormField {
        optional = true
        return this
    }

    fun isUrl(): StringFormField {
        item?.let {

            if (optional && (it.isEmpty() || it.isBlank())) { // field is optional and can be empty or blank, in this situation there is no need to validation
                return this
            } else {
                isValid = URLUtil.isValidUrl(item)
            }
        }
        return this
    }

    fun throwError(): Int? {
        return errorMessage
    }
}