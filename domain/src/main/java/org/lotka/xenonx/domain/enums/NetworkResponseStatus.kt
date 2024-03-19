package org.lotka.xenonx.domain.enums

enum class NetworkResponseStatus(val value: String) {
    NO_INTERNET("NOINTERNET"), // When neither data nor Wi-Fi is on
    UNAUTHORIZED("UNAUTHORIZED"), // The access token has expired and needs to be renewed
    SERVER_ERROR("SERVERERROR"), // The response that is returned from the server is not valid ,for example when Wi-Fi or data is on but we don't have internet
    CANT_REACH_SERVER("CANTREACHSERVER"), // When Wi-Fi or data is on but we don't have internet
    VPN_IS_ON("VPNISON") // When Wi-Fi or data is on but we don't have internet
}