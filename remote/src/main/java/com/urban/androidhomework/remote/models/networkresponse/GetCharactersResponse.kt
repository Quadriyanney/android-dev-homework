package com.urban.androidhomework.remote.models.networkresponse

import com.urban.androidhomework.remote.models.charcater.CharacterNetworkModel
import com.urban.androidhomework.remote.models.info.InfoNetworkModel

data class GetCharactersResponse(
    val info: InfoNetworkModel,
    val results: List<CharacterNetworkModel>
)
