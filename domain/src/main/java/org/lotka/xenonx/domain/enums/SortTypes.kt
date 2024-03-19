package org.lotka.xenonx.dataold.enums

enum class SortTypes(val value: String) {
    DESCENDING("actiondate,desc"), //به روز ترین
    ASCENDING("actiondate,asc"), //قدیمی ترین
    PRICE_ASCENDING("price,asc"), // کمترین قیمت
    PRICE_DESCENDING("price,desc"), //بیشترین قیمت
    UNIT_ASCENDING("unit,asc"), // کمترین قیمت مربع
    UNIT_DESCENDING("unit,desc"), //بیشترین قیمت مربع
    BED_NUM_ASCENDING("noBeds,asc"), //کمترین تعداد خواب
    BED_NUM_DESCENDING("noBeds,desc"), //بیشترین تعداد خواب
}

enum class HomeSeekersSortTypes(val value: String) {
    DESCENDING("date,desc"), //به روز ترین
    ASCENDING("date,asc"), //قدیمی ترین
    ID_DESCENDING("id,desc"), // کد سرنخ (نزولی)
    ID_ASCENDING("id,asc"), //کد سرنخ (صعودی)
}

enum class ActiveAgentsSortTypes(val value: String) {
    DESCENDING("id,desc"), //به روز ترین
    ASCENDING("id,asc"), //قدیمی ترین
    NAME_ASCENDING("first,asc"), // نام (صعودی)
    NAME_DESCENDING("first,desc"), // نام (نزولی)
    FAMILY_ASCENDING("last,asc"), // نام خانوادگی (صعودی)
    FAMILY_DESCENDING("last,desc"), // نام خانوادگی (نزولی)
}
