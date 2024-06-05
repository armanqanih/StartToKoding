package org.lotka.xenonx.data.model

import com.google.gson.annotations.SerializedName
import org.lotka.xenonx.domain.model.TeamMemberModel

data class TeamMember(
    val id: String,
    val name: String,
    val position: String
)

fun TeamMember.toTeamMemberModel(): TeamMemberModel {
    return TeamMemberModel(
        id = id,
        name = name,
        position = position
    )
}