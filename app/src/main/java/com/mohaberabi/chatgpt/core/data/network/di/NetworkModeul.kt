package com.mohaberabi.chatgpt.core.data.network.di

import com.mohaberabi.chatgpt.core.data.network.client.KtorMessagesRemoteDataSource
import com.mohaberabi.chatgpt.core.data.network.factory.HttpClientFactory
import com.mohaberabi.chatgpt.core.domain.model.DispatchersProvider
import com.mohaberabi.chatgpt.core.domain.source.remote.MessagesRemoteDataSource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.ktor.client.HttpClient
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {


    @Singleton
    @Provides
    fun provideMesagesRemoteDataSource(
        client: HttpClient,
        disaptchers: DispatchersProvider
    ): MessagesRemoteDataSource = KtorMessagesRemoteDataSource(client, disaptchers)

    @Singleton
    @Provides
    fun provideHttpClient(): HttpClient = HttpClientFactory().create()
}