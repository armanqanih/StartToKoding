package org.lotka.xenonx.domain.enums

enum class LandUseTypes(
    val nameEn: String,
    val nameFa: String,
    val index: Int,
    val id: Array<Int> = arrayOf()
) {

    ALL(nameEn = "ALL", nameFa = "همه", index = 0, id = arrayOf(3001, 3005)),
    RESIDENTIAL(nameEn = "RESIDENTIAL", nameFa = "مسکونی", index = 1, id = arrayOf(3001)),
    COMMERCIAL(nameEn = "COMMERCIAL", nameFa = "اداری تجاری", index = 2, id = arrayOf(3005)),
    ;  // Backend determines the ID value

    companion object {
        private val map = values().associateBy(LandUseTypes::index)
        fun getValueById(index: Int) = map[index]

        fun getLandUseTypeNameFaByName(name: String): String? {
            return LandUseTypes.values().find { it.name == name }?.nameFa
        }

        fun getLandUseTypeNameByNameFa(name: String): String? {
            return LandUseTypes.values().find { it.nameFa == name }?.name
        }

        fun getLandUseTypeByNameFa(nameFa: String): LandUseTypes? {
            return LandUseTypes.values().find { it.nameFa == nameFa }
        }

        fun getLandUseTypeByID(id: Array<Int>): LandUseTypes? {
            return LandUseTypes.values().find { it.id.contentEquals(id) }
        }

    }
}