package org.lotka.xenonx.domain.enums

enum class SourceTypes(val value: String) {
    INDIVIDUAL("individual"), //آگهی های شخصی
    SELF("self") //آگهی های من
}

enum class StateTypes(val value: String) {
    CONFIRMED("1"),
    NOT_CONFIRMED("2"),
    REJECTED("4")
}