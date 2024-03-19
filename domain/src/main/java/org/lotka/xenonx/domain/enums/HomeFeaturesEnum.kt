package org.lotka.xenonx.domain.enums

enum class HomeFeaturesEnum(
    val id: Int,
    val text: String,
    val nameFa: String,
    val nameLat: String
) {

    LOBBY(1, "لابی", "لابی", "lobby"),
    STORAGE(2, "انباری", "انباری", "storage"),
    SPORT(3, "سالن ورزش", "سالن ورزش", "sport"),
    SECURITY(4, "نگهبان", "نگهبان", "security"),
    ELEVATOR(5, "آسانسور", "آسانسور", "elevator"),
    BALCONY(6, "بالکن", "بالکن", "balcony"),
    POOL(7, "استخر", "استخر", "pool"),
    SAUNA(8, "سونا", "سونا", "sauna"),
    HVAC(9, "تهویه مطبوع", "تهویه مطبوع", "hvac"),
    COMMON(10, "سالن اجتماعات", "سالن اجتماعات", "common"),
    ROOF(11, "روف گاردن", "روف گاردن", "roof"),
    REMOTE_DOOR(12, "درب ریموت", "درب ریموت", "remote_door"),
    JACUZZI(13, "جکوزی", "جکوزی", "jacuzzi"),
    CENTRAL_ANTENNA(14, "آنتن مرکزی", "آنتن مرکزی", "central_antenna"),
    PATIO(16, "پاسیو", "پاسیو", "patio"),
    SECURITY_DOOR(17, "درب ضدسرقت", "درب ضدسرقت", "security_door"),
    FURNISHED(18, "مبله", "مبله", "furnished"),
    FITTED_KITCHEN(19, "آشپزخانه فرنیش", "آشپزخانه فرنیش", "fitted_kitchen"),
    HOME_CINEMA(20, "سینما خانگی", "سینما خانگی", "home_cinema"),
    SMART_HOME(21, "خانه هوشمند", "خانه هوشمند", "smart_home"),
    CENTRAL_INTERNET(22, "اینترنت مرکزی", "اینترنت مرکزی", "central_internet"),
    VIDEO_DOOR_OPEN(23, "آیفون تصویری", "آیفون تصویری", "video_door_open"),
    GARBAGE_SHOOT(24, "شوتینگ زباله", "شوتینگ زباله", "garbage_shoot"),
}

fun getAllHomeFeaturesText(): List<String> {
    return HomeFeaturesEnum.values().map { it.text }
}

fun getFeatureById(id: Int): HomeFeaturesEnum? {
    return enumValues<HomeFeaturesEnum>().find { it.id == id }
}

fun getFeaturesByIds(ids: List<Int>): List<HomeFeaturesEnum> {
    return enumValues<HomeFeaturesEnum>().filter { it.id in ids }
}

fun getFeatureNameFaByEnum(featureEnum: HomeFeaturesEnum): String {
    return featureEnum.nameFa
}

fun getHomeFeatureByString(featureString: String): HomeFeaturesEnum? {
    return HomeFeaturesEnum.values().first { it.nameFa == featureString }
}

fun getAvailableFeatures(features: List<String?>?): List<HomeFeaturesEnum> {
    return features?.mapNotNull { featureName ->
        HomeFeaturesEnum.values().firstOrNull { it.nameFa == featureName }
    } ?: listOf()
}

