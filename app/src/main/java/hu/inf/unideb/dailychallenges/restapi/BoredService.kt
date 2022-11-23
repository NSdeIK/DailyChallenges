package hu.inf.unideb.dailychallenges.restapi

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

private const val API_URL = "https://www.boredapi.com/"

private val moshi = Moshi.Builder().add(KotlinJsonAdapterFactory()).build()

private val retrofit =
    Retrofit.Builder().addConverterFactory(MoshiConverterFactory.create(moshi))
        .baseUrl(API_URL)
        .build();

object BoredService {
    val boredInterface: BoredInterface by lazy {
        retrofit.create(BoredInterface::class.java)
    }
}