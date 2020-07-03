package com.urban.androidhomework.remote.model.networkresponse

import com.urban.androidhomework.remote.model.CharacterNetworkModel
import com.urban.androidhomework.remote.model.InfoNetworkModel

data class GetCharactersResponse(
    val info: InfoNetworkModel,
    val results: List<CharacterNetworkModel>
)