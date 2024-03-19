package org.lotka.xenonx.dataold.enums

enum class SignUpTabsType(val value: String, val index: Int) {
    AGECNY_MANAGER("مدیر آژانس", 0),
    FREELANCE_AGENT("مشاور", 1);

    companion object {
        private val map = values().associateBy(SignUpTabsType::index)
        fun getValueByIndex(index: Int) = map[index]
    }
}