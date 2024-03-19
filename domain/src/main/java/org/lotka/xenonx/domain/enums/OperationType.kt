package org.lotka.xenonx.domain.enums

enum class OperationType(val value: String) {

    // user
    REMOVE_USER("removeUser"),
    CHANGE_ROLE("changeRole"),

    // file
    REMOVE_FILE("removeFile"),
    UPDATE_EXPIRY("archiveFile"),
    LADDER_UP("boostFile"),
    OCCASION("occasion")
}