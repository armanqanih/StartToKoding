package org.lotka.xenonx.domain.enums

enum class ListingConditionType(val nameEn: String, val nameFa: String, val index: Int) {
    PENDING(nameEn = "PENDING", nameFa = "در حال بررسی", 1),
    REJECT(nameEn = "REJECT", nameFa = "رد شده", 3),
    ACCEPT(nameEn = "ACCEPT", nameFa = "تایید شده", 2),
    ALL(nameEn = "ALL", nameFa = "همه", 0);

    companion object {
        private val mapByIndex = values().associateBy(ListingConditionType::index)
        private val mapByValue = values().associateBy(ListingConditionType::nameFa)
        fun getValueByIndex(index: Int): ListingConditionType? = mapByIndex[index]
        fun getValueByName(value: String): ListingConditionType? = mapByValue[value]
        fun getByFaName(nameFa: String): ListingConditionType? = mapByValue[nameFa]
    }
}