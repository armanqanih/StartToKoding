package org.lotka.xenonx.domain.enums

enum class HomeConditionsEnum(
    val id: Int,
    val text: String,
    val nameFa: String,
    val nameLat: String
) {

    MOSHAREKATI(1, "مشارکتی", "مشارکتی", "مشارکتی"),
    MOAVEZE(2, "معاوضه", "معاوضه", "معاوضه"),
    QABELETABDIL(3, "قابل تبدیل", "قابل تبدیل", "قابل تبدیل"),
    RAHNEKAMEL(4, "رهن کامل", "رهن کامل", "رهن کامل"),
    PISHFOROSH(5, "پیش فروش", "پیش فروش", "پیش فروش"),
    MOQETEDARI(6, "موقعیت اداری", "موقعیت اداری", "موقعیت اداری"),
    VAMDAR(7, "وام دار", "وام دار", "وام دار"),
    MABLAGHTAVAFOGHI(8, "مبلغ توافقی", "مبلغ توافقی", "مبلغ توافقی"),
    NOSAZ(9, "نوساز", "نوساز", "نوساز"),
    QADROSAHM(12, "قدر السهم", "قدر السهم", "قدر السهم"),

}

fun getAllHomeConditionsText(): List<String> {
    return HomeConditionsEnum.values().map { it.text }
}

fun getConditionsById(id: Int): HomeConditionsEnum? {
    return enumValues<HomeConditionsEnum>().find { it.id == id }
}

fun getConditionNameFaByEnum(conditionEnum: HomeConditionsEnum): String {
    return conditionEnum.nameFa
}