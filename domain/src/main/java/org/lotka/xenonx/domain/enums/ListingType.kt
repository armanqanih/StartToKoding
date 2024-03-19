package org.lotka.xenonx.domain.enums

enum class ListingType(val nameEn: String, val nameFa: String, val id: Int) {


    SALE_AND_PRE_SALE(nameEn = "BUY", nameFa = "خرید", 1),
    MORTGAGE_AND_RENT(nameEn = "RENT", nameFa = "رهن/اجاره", 2);


    companion object {
        private val mapByIndex = values().associateBy(ListingType::id)
        private val mapByValue = values().associateBy(ListingType::nameFa)
        fun getValueById(id: Int): ListingType? = mapByIndex[id]
        fun getValueByName(value: String): ListingType? = mapByValue[value]
        fun getByFaName(nameFa: String): ListingType? = mapByValue[nameFa]
        fun getIdOf(listingType: ListingType): Int = listingType.id

        fun getByNameEn(nameEn: String): ListingType? {
            return values().find { it.nameEn.equals(nameEn, ignoreCase = true) }
        }
    }
}