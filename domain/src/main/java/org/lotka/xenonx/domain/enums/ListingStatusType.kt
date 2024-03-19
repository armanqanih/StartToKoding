package org.lotka.xenonx.domain.enums

enum class ListingStatusType(val nameEn: String, val nameFa: String, val index: Int) {
    ALL(nameEn = "ALL", nameFa = "همه", 0),
    PENDING(nameEn = "PENDING", nameFa = "در حال بررسی", 1),
    REJECT(nameEn = "REJECT", nameFa = "رد شده", 3),
    ACCEPT(nameEn = "ACCEPT", nameFa = "تایید شده", 2),
    ;

    companion object {
        private val mapByIndex = values().associateBy(ListingStatusType::index)
        private val mapByValue = values().associateBy(ListingStatusType::nameFa)
        fun getValueByIndex(index: Int): ListingStatusType? = mapByIndex[index]
        fun getValueByName(value: String): ListingStatusType? = mapByValue[value]
        fun getByFaName(nameFa: String): ListingStatusType? = mapByValue[nameFa]
    }
}