package org.lotka.xenonx.presentation.extension

import android.util.Patterns
import android.webkit.URLUtil
import com.google.i18n.phonenumbers.NumberParseException
import com.google.i18n.phonenumbers.PhoneNumberUtil
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.RequestBody.Companion.toRequestBody
import org.ainarm.chat.presentation.R


fun String.toRequestBody() = this.toRequestBody("text/plain".toMediaType())


// ------------------------------String Related Extensions ----------------------------------

fun String.isIranMobileNumberValid(): Boolean {
    // Define a regex pattern for a valid Iran mobile number
    val pattern = Regex("^09\\d{9}$")
    // Use the matches function to check if the string matches the pattern
    return pattern.matches(this)
}


fun String.toFarsi(): String =
    this.replace("0".toRegex(), "۰")
        .replace("1".toRegex(), "۱")
        .replace("2".toRegex(), "۲")
        .replace("3".toRegex(), "۳")
        .replace("4".toRegex(), "۴")
        .replace("5".toRegex(), "۵")
        .replace("6".toRegex(), "۶")
        .replace("7".toRegex(), "۷")
        .replace("8".toRegex(), "۸")
        .replace("9".toRegex(), "۹")
        .replace("٠".toRegex(), "۰")
        .replace("١".toRegex(), "۱")
        .replace("٢".toRegex(), "۲")
        .replace("٣".toRegex(), "۳")
        .replace("٤".toRegex(), "۴")
        .replace("٥".toRegex(), "۵")
        .replace("٦".toRegex(), "۶")
        .replace("٧".toRegex(), "۷")
        .replace("٨".toRegex(), "۸")
        .replace("٩".toRegex(), "۹")

fun String.toEng(): String =
    this.replace("۰".toRegex(), "0")
        .replace("۱".toRegex(), "1")
        .replace("۲".toRegex(), "2")
        .replace("۳".toRegex(), "3")
        .replace("۴".toRegex(), "4")
        .replace("۵".toRegex(), "5")
        .replace("۶".toRegex(), "6")
        .replace("۷".toRegex(), "7")
        .replace("۸".toRegex(), "8")
        .replace("۹".toRegex(), "9")
        .replace("٠".toRegex(), "0")
        .replace("١".toRegex(), "1")
        .replace("٢".toRegex(), "2")
        .replace("٣".toRegex(), "3")
        .replace("٤".toRegex(), "4")
        .replace("٥".toRegex(), "5")
        .replace("٦".toRegex(), "6")
        .replace("٧".toRegex(), "7")
        .replace("٨".toRegex(), "8")
        .replace("٩".toRegex(), "9")

fun String.addToman() = "$this تومان "

// ------------------------------Form Related Extensions ----------------------------------

private fun String.isFarsiPhrase(): Boolean =
    try {
        this.matches(
            "^[\u0622\u0627\u0628\u067E\u062A-\u062C\u0686\u062D-\u0632\u0698\u0633-\u063A\u0641\u0642\u06A9\u06AF\u0644-\u0648\u06CC]*$".toFarsi()
                .toRegex()
        )
    } catch (e: Exception) {
        false
    }

private fun String.isEnglishPhrase(): Boolean = this.matches("^[a-zA-Z]*$".toRegex())
private fun String.isNumber(): Boolean = this.toEng().matches("^[0-9]+$".toRegex())
private fun String.isFarsiPhraseOrNumber(): Boolean = this.filter {
    it.toString().isFarsiPhrase() || it.toString().toEng().isNumber() || it.toString().isBlank()
}.length == this.length

private fun String.isEnglishPhraseOrNumber(): Boolean = this.filter {
    it.toString().isEnglishPhrase() || it.toString().toEng().isNumber() || it.toString().isBlank()
}.length == this.length

private fun String.isValidMobile() =
    try {
        val phoneUtil = PhoneNumberUtil.getInstance()
        val iranNumberProto = phoneUtil.parse(this, "IR")
        phoneUtil.isValidNumber(iranNumberProto)
    } catch (e: NumberParseException) {
        false
    }

private fun String.isValidPhone() =
    this.matches("^[0-9]+$".toRegex()) && this.length > 3 && this.length < 10

private fun String.isValidPassword() = length > 3
private fun String.isValidEmail() = Patterns.EMAIL_ADDRESS.matcher(StringBuffer(this)).matches()
private fun String.isValidUrl() = URLUtil.isValidUrl(this) || this.isEmpty()
fun String.addMeter() = this.replace("متر", "").plus("متر")
fun String.removeDecimal() = this.replaceAfter(".", "").replace(".", "")

// ------------------------------Form Validation ----------------------------------

fun String.isValidFarsiNameOrThrowError() =
    if (this.isFarsiPhraseOrNumber()) null else R.string.farsi_name_is_not_valid

fun String.isValidFarsiLastNameOrThrowError() =
    if (this.isFarsiPhraseOrNumber()) null else R.string.last_name_is_not_valid

fun String.isValidEnglishNameOrThrowError() =
    if (this.isEnglishPhraseOrNumber()) null else R.string.english_name_is_not_valid

fun String.isValidEmailOrThrowError() =
    if (this.isValidEmail()) null else R.string.agency_email_is_not_valid

fun String.isValidPasswordOrThrowError() =
    if (this.isValidPassword()) null else R.string.password_length_must_be_at_least_four

fun String.isValidRepeatPasswordOrThrowError(value: String) =
    if (value == this) null else R.string.password_and_repeat_are_not_equal

fun String.isValidPhoneOrThrowError() =
    if (this.isValidPhone()) null else R.string.phone_number_is_not_valid

fun String.isValidMobileOrThrowError() =
    if (this.isValidMobile()) null else R.string.mobile_number_is_not_valid

fun String.isValidInternalNumbersOrThrowError() =
    if (this.isNumber()) null else R.string.internal_number_is_not_valid

fun String.isValidFloorAreaOrThrowError() =
    if (!this.isEmpty() && !this.isBlank() && this.isNumber()) null else R.string.floor_area_is_not_valid

fun String.isValidParkingNumOrThrowError() =
    if (this.isNullOrEmpty() || this.isNullOrBlank() || this.isNumber()) null else R.string.parking_number_is_not_valid

fun String.isValidPriceOrThrowError() = if (this.isNumber()) null else R.string.price_is_not_valid
fun String.isValidUnitPriceOrThrowError() =
    if (this.isNullOrBlank() || this.isNumber()) null else R.string.price_is_not_valid

fun String.isValidUnitShareOrThrowError() = if (this.isNumber()) null else R.string.just_use_numbers
fun String.isValidLoanOrThrowError() = if (this.isNumber()) null else R.string.just_use_numbers
fun String.isValidTitleOrThrowError() =
    if (this.length in 1..75) null else R.string.title_is_not_valid

fun String.isValidAge() =
    if (this.isNullOrBlank() || this.isNumber()) null else R.string.home_age_is_not_valid

fun String.isValidDescriptionOrThrowError() =
    if (this.length in 1..1000) null else R.string.description_is_not_valid

// ------------------------------Form Validation ----------------------------------

fun Long.formatAsCurrency(): String {
    return this.toString().addThousandsSeparator().addToman() // Ensure these functions handle the formatting correctly
}

fun String.addThousandsSeparator(): String {
    if (this.length <= 3) return this
    else {
        var temp = this
        for (i in this.length - 3 downTo 1 step 3) {
            temp = temp.substring(0, i) + "," + temp.substring(i, temp.length)
        }
        return temp
    }
}

fun String.removeThousandsSeparator() = this.replace(",", "")
fun String.toReadableCurrency() =
    this.removeThousandsSeparator().addThousandsSeparator()

fun String.removePrefixZero() = this.replaceFirst("^0+(?!$)".toRegex(), "")
fun String.isBlankOrEmpty() = this.isBlank() || this.isEmpty()