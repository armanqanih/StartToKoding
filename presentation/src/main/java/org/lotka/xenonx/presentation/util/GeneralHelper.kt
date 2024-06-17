package org.lotka.xenonx.presentation.util

import android.app.Activity
import android.content.Context
import android.os.Build
import android.text.TextUtils
import android.util.Patterns
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.fragment.app.FragmentActivity
import org.ainarm.chat.presentation.R

import java.net.Inet4Address
import java.net.NetworkInterface
import java.net.SocketException
import java.text.DecimalFormat
import java.text.DecimalFormatSymbols
import java.text.NumberFormat
import java.util.Locale
import java.util.regex.Matcher
import java.util.regex.Pattern


object GeneralHelper {


    fun hasZeroFraction(n: Double?) = (n?.rem(1f))?.equals(0.0)
    val threeFractionFormat: DecimalFormat =
        NumberFormat.getCurrencyInstance(Locale.GERMANY) as DecimalFormat
    val threeFormatSymbols: DecimalFormatSymbols = threeFractionFormat.decimalFormatSymbols


    val noFractionFormat: DecimalFormat =
        NumberFormat.getCurrencyInstance(Locale.GERMANY) as DecimalFormat
    val noFormatSymbols: DecimalFormatSymbols = threeFractionFormat.decimalFormatSymbols

    val twoFractionFormat: DecimalFormat =
        NumberFormat.getCurrencyInstance(Locale.GERMANY) as DecimalFormat
    val twoFormatSymbols: DecimalFormatSymbols = threeFractionFormat.decimalFormatSymbols

    init {
        threeFormatSymbols.currencySymbol = "" // Don't use null.
        threeFractionFormat.maximumFractionDigits = 3
        threeFractionFormat.minimumFractionDigits = 3
        threeFractionFormat.decimalFormatSymbols = threeFormatSymbols

        noFormatSymbols.currencySymbol = "" // Don't use null.
        noFractionFormat.maximumFractionDigits = 2
        noFractionFormat.minimumFractionDigits = 0
        noFractionFormat.decimalFormatSymbols = noFormatSymbols

        twoFormatSymbols.currencySymbol = "" // Don't use null.
        twoFractionFormat.maximumFractionDigits = 2
        twoFractionFormat.minimumFractionDigits = 2
        twoFractionFormat.decimalFormatSymbols = noFormatSymbols
    }

    fun hideKeyboard(activity: FragmentActivity) {
        val imm: InputMethodManager =
            activity.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
        //Find the currently focused view, so we can grab the correct window token from it.
        var view: View? = activity.currentFocus
        //If no view currently has focus, create a new one, just so we can grab a window token from it
        if (view == null) {
            view = View(activity)
        }
        imm.hideSoftInputFromWindow(view.windowToken, 0)
    }

    fun getLocalIpAddress(): String? {
        try {
            val en = NetworkInterface.getNetworkInterfaces()
            while (en.hasMoreElements()) {
                val intf = en.nextElement()
                val enumIpAddr = intf.inetAddresses
                while (enumIpAddr.hasMoreElements()) {
                    val inetAddress = enumIpAddr.nextElement()
                    if (!inetAddress.isLoopbackAddress && inetAddress is Inet4Address) {
                        return inetAddress.hostAddress
                    }
                }
            }
        } catch (ex: SocketException) {
            ex.printStackTrace()
        }
        return null
    }

    fun getDeviceName(): String {
        val manufacturer = Build.MANUFACTURER
        val model = Build.MODEL
        return if (model.lowercase(Locale.getDefault())
                .startsWith(manufacturer.lowercase(Locale.getDefault()))
        ) {
            capitalize(model)
        } else {
            capitalize(manufacturer) + " " + model
        }
    }


    private fun capitalize(s: String?): String {
        if (s == null || s.length == 0) {
            return ""
        }
        val first = s[0]
        return if (Character.isUpperCase(first)) {
            s
        } else {
            first.uppercaseChar().toString() + s.substring(1)
        }
    }


    //https://stackoverflow.com/a/23214628/5326003
    fun isValidPassword(password: String?): Boolean {
        val pattern: Pattern
        //val PASSWORD_PATTERN = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=\\S+$).{8,}$"
        val PASSWORD_PATTERN = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}$"
        pattern = Pattern.compile(PASSWORD_PATTERN)
        val matcher: Matcher = pattern.matcher(password)
        return matcher.matches()
    }

    fun isValid(
        context: Context,
        passwordhere: String,
        confirmhere: String,
        errorList: MutableList<String>
    ): Boolean {
        val specailCharPatten = Pattern.compile("[^a-z0-9 ]", Pattern.CASE_INSENSITIVE)
        val UpperCasePatten = Pattern.compile("[A-Z ]")
        val lowerCasePatten = Pattern.compile("[a-z ]")
        val digitCasePatten = Pattern.compile("[0-9 ]")
        errorList.clear()
        var flag = true
        if (passwordhere != confirmhere) {
            errorList.add("password and confirm password does not match")
            flag = false
        }
        if (passwordhere.length < 8) {
            errorList.add(context.getString(R.string.password_validations_at_least_8_chars))
            flag = false
        }
        /*if (!specailCharPatten.matcher(passwordhere).find()) {
            errorList.add("One special character")
            flag = false
        }*/
        if (!UpperCasePatten.matcher(passwordhere).find()) {
            errorList.add(context.getString(R.string.password_validations_at_least_1_upper_char))
            flag = false
        }
        if (!lowerCasePatten.matcher(passwordhere).find()) {
            errorList.add(context.getString(R.string.password_validations_at_least_1_lower_char))
            flag = false
        }
        if (!digitCasePatten.matcher(passwordhere).find()) {
            errorList.add(context.getString(R.string.password_validations_at_least_1_digit_char))
            flag = false
        }
        return flag
    }

    fun isValidEmail(target: CharSequence?): Boolean {
        return !TextUtils.isEmpty(target) && Patterns.EMAIL_ADDRESS.matcher(target).matches()
    }


//	suspend fun renderSinglePage(filePath: String, width: Int) = withContext(Dispatchers.IO) {
//		PdfRenderer(ParcelFileDescriptor.open(File(filePath), ParcelFileDescriptor.MODE_READ_ONLY)).use { renderer ->
//			renderer.openPage(0).renderAndClose(width)
//		}
//	}

    fun String?.isValidEmail() = !isNullOrEmpty() && Patterns.EMAIL_ADDRESS.matcher(this).matches()


}