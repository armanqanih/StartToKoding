package org.lotka.xenonx.domain.enums

enum class FilterTypes(val nameFa: String, val nameEn: String, val serverKey: String) {
    LISTING_TYPE(nameFa = "نوع آگهی", nameEn = "LISTING_TYPE", serverKey = "listingTypeId"),
    LAND_USE_TYPE(nameFa = "نوع کاربری", nameEn = "LAND_USE_TYPE", serverKey = "landuseTypeIds"),
    HOME_USE_TYPE(nameFa = "نوع ملک", nameEn = "HOME_USE_TYPE", serverKey = "propertyTypeIds"),
    SALE_PRICE_RANGE(nameFa = "بازه  قیمت خرید", nameEn = "SALE_PRICE_RANGE", serverKey = "price"),
    RENT_PRICE_RANGE(nameFa = "بازه اجاره", nameEn = "RENT_PRICE_RANGE", serverKey = "rent"),
    DEPOSIT_PRICE_RANGE(nameFa = "بازه رهن", nameEn = "DEPOSIT_PRICE_RANGE", serverKey = "deposit"),
    LOCATION(nameFa = "محدوده", nameEn = "LOCATION", serverKey = "location");

    companion object {
        fun getFilterTypeByServerKey(serverKey: String): org.lotka.xenonx.domain.enums.FilterTypes? {
            return values().find { it.serverKey == serverKey }
        }
    }
}