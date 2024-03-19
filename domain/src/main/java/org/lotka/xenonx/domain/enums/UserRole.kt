package org.lotka.xenonx.dataold.enums

enum class UserRole(val role: String, val agencyId: String? = null) {
    MANAGER("ROLE_AGENCY_MANAGER", null), // manager
    AGENT("ROLE_AGENCY_USER", null), // agent
    FREELANCE("ROLE_AGENCY_USER", "999999"), // freelance
}