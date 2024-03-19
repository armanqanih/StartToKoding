package org.lotka.xenonx.domain.enums

enum class ListingStatus(val nameEn: String, val nameFa: String, val index: Int) {
    PENDING(nameEn = "PENDING", nameFa = "در انتظار تایید", index = 0),
    REJECT(nameEn = "REJECT", nameFa = "رد شده", index = 1),
    ACCEPT(nameEn = "ACCEPT", nameFa = "تایید شده", index = 2);

    companion object {
        private val map = values().associateBy(ListingStatus::index)
        fun getValueByIndex(index: Int) = map[index]

        fun getListingStatusNameFaByName(name: String): String? {
            return ListingStatus.values().find { it.nameEn == name }?.nameFa
        }

        fun getListingStatusNameByNameFa(name: String): String? {
            return ListingStatus.values().find { it.nameFa == name }?.nameEn
        }

        fun getListingStatusByNameFa(nameFa: String): ListingStatus? {
            return ListingStatus.values().find { it.nameFa == nameFa }
        }

        fun getListingStatusByNameEn(nameEn: String): ListingStatus? {
            return ListingStatus.values().find { it.nameEn == nameEn }
        }

    }
}