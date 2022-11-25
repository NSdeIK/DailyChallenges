package hu.inf.unideb.dailychallenges.restapi

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface BoredInterface {

    @GET("/api/activity")
    suspend fun getActivity(
        @Query(value="type") type: String, ): Response<BoredEntity>
}