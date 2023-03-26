package com.nikhil.sportzapi.data.remote

import com.nikhil.sportzapi.model.SportzResponse
import com.nikhil.sportzapi.utils.Constants
import retrofit2.Response
import retrofit2.http.GET

interface SportzService {

    @GET(Constants.SPORTZDATA)
    suspend fun getSportzData(): Response<SportzResponse>
}
