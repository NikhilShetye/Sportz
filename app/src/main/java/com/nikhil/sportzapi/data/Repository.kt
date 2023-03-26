package com.nikhil.sportzapi.data

import com.nikhil.sportzapi.data.remote.RemoteDataSource
import com.nikhil.sportzapi.model.BaseApiResponse
import com.nikhil.sportzapi.model.SportzResponse
import com.nikhil.sportzapi.utils.NetworkResult
import dagger.hilt.android.scopes.ActivityRetainedScoped
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject


@ActivityRetainedScoped
class Repository @Inject constructor(
    private val remoteDataSource: RemoteDataSource
) : BaseApiResponse() {

    suspend fun getSportzData(): Flow<NetworkResult<SportzResponse>> {
        return flow<NetworkResult<SportzResponse>> {
            emit(safeApiCall { remoteDataSource.getDog() })
        }.flowOn(Dispatchers.IO)
    }
}

