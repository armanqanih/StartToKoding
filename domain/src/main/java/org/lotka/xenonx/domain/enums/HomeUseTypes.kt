package org.lotka.xenonx.domain.enums

import org.ainarm.chat.domain.R


/*
 Backend determines the ID values
 */
enum class HomeUseTypes(
    val nameEn: String,
    val nameFa: String,
    val index: Int,
    val imageResId: Int,
    val id: Array<Int> = arrayOf()
) {


    ALL(
        nameEn = "ALL",
        nameFa = "همه",
        index = 0,
        imageResId = R.drawable.nd_undercat_tower,
        id = arrayOf(3001, 3005)
    ),
    APARTMENT_TOWER(
        nameEn = "APARTMENT_TOWER",
        nameFa = "آپارتمان/برج",
        index = 1,
        imageResId = R.drawable.nd_undercat_tower2,
        id = arrayOf(2014)
    ),
    VILLA_GARDEN(
        nameEn = "VILLA_GARDEN",
        nameFa = "ویلایی/باغ و باغچه",
        index = 2,
        imageResId = R.drawable.nd_undercat_villa,
        id = arrayOf(2015)
    ),
    PENT_HOUSE(
        nameEn = "PENT_HOUSE",
        nameFa = "پنت هاوس",
        index = 3,
        imageResId = R.drawable.nd_undercat_penthouse,
        id = arrayOf(2013)
    ),
    STORE(
        nameEn = "STORE",
        nameFa = "مغازه",
        index = 5,
        imageResId = R.drawable.nd_undercat_store,
        id = arrayOf(2004)
    ),
    LAND(
        nameEn = "LAND",
        nameFa = "زمین/کلنگی",
        index = 6,
        imageResId = R.drawable.nd_undercat_land,
        id = arrayOf(2003)
    ),
    REAL_ESTATE(
        nameEn = "REAL_ESTATE",
        "مستغلات",
        index = 7,
        imageResId = R.drawable.nd_undercat_town,
        id = arrayOf(2012)
    ),
    FARM(
        nameEn = "FARM",
        nameFa = "کشاورزی",
        index = 8,
        imageResId = R.drawable.nd_undercat_farm,
        id = arrayOf(2017)
    ),
    OTHER(
        nameEn = "OTHER",
        nameFa = "سایر",
        index = 10,
        imageResId = R.drawable.nd_undercat_tower,
        id = arrayOf(2005)
    ),
    OFFICE(
        nameEn = "OFFICE",
        nameFa = "دفتر کار، اتاق اداری و مطب",
        index = 4,
        imageResId = R.drawable.nd_undercat_office,
        id = arrayOf(2008)
    ),
    STORAGE_WORKSHOP_FACTORY(
        nameEn = "STORAGE_WORKSHOP_FACTORY",
        nameFa = "انبار، سوله، کارگاه و کارخانه",
        index = 9,
        imageResId = R.drawable.nd_undercat_factory,
        id = arrayOf(2016)
    ),
    ;

    companion object {

        fun getAllHomeUseTypes(): List<HomeUseTypes> {
            return listOf(
                ALL,
                APARTMENT_TOWER,
                VILLA_GARDEN,
                PENT_HOUSE,
                STORE,
                LAND,
                REAL_ESTATE,
                FARM,
                OTHER,
                OFFICE,
                STORAGE_WORKSHOP_FACTORY,
            )
        }

        fun getResidentialHomeUseTypes(): List<HomeUseTypes> {
            return listOf(
                APARTMENT_TOWER,
                VILLA_GARDEN,
                PENT_HOUSE,
                LAND,
                REAL_ESTATE,
                OTHER
            )
        }

        fun getCommercialHomeUseTypes(): List<HomeUseTypes> {
            return listOf(
                OFFICE,
                STORE,
                LAND,
                REAL_ESTATE,
                FARM,
                STORAGE_WORKSHOP_FACTORY,
                OTHER
            )
        }


        fun getHomeUseTypeNameFaByName(name: String): String? {
            return HomeUseTypes.values().find { it.name == name }?.nameFa
        }

        fun getHomeUseTypeNameFaByNameLat(name: String): String? {
            return HomeUseTypes.values().find { it.nameEn == name }?.nameFa
        }


        fun getHomeUseTypeNameByNameFa(nameFa: String): String? {
            return HomeUseTypes.values().find { it.nameFa == nameFa }?.nameEn
        }

        fun getHomeUseTypeByNameFa(nameFa: String): HomeUseTypes? {
            return HomeUseTypes.values().find { it.nameFa == nameFa }
        }

        fun getHomeUseTypeById(ids: Array<Int>): HomeUseTypes? {
            return HomeUseTypes.values().find { ids.contentEquals(it.id) }
        }


    }
}
