package com.nikhil.sportzapi.data.remote

import javax.inject.Inject

class RemoteDataSource @Inject constructor(private val sportzService: SportzService) {

    suspend fun getDog() =
        sportzService.getSportzData()

}