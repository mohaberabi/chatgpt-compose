package com.mohaberabi.chatgpt.core.data.di

import com.mohaberabi.chatgpt.core.data.repository.OfflineFirstChatRepository
import com.mohaberabi.chatgpt.core.data.repository.OfflineFirstMessagesRepository
import com.mohaberabi.chatgpt.core.domain.model.DefaultDispatchersProvider
import com.mohaberabi.chatgpt.core.domain.model.DispatchersProvider
import com.mohaberabi.chatgpt.core.domain.repository.ChatRepository
import com.mohaberabi.chatgpt.core.domain.repository.MessagesRepository
import com.mohaberabi.chatgpt.core.domain.source.local.ChatLocalDataSource
import com.mohaberabi.chatgpt.core.domain.source.local.MessagesLocalDataSource
import com.mohaberabi.chatgpt.core.domain.source.remote.MessagesRemoteDataSource
import com.mohaberabi.chatgpt.core.domain.usecase.CreateChatUseCase
import com.mohaberabi.chatgpt.core.domain.usecase.GetChatsUseCase
import com.mohaberabi.chatgpt.core.domain.usecase.GetMessagesUseCase
import com.mohaberabi.chatgpt.core.domain.usecase.SendMessageUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object CoreModule {


    @Singleton
    @Provides
    fun provideDispatchers(): DispatchersProvider = DefaultDispatchersProvider()

    @Singleton
    @Provides
    fun provideChatRepository(
        localDataSource: ChatLocalDataSource
    ): ChatRepository = OfflineFirstChatRepository(localDataSource)

    @Singleton
    @Provides
    fun provideMessagesRepository(
        localSoruce: MessagesLocalDataSource,
        remoteSoruce: MessagesRemoteDataSource
    ): MessagesRepository = OfflineFirstMessagesRepository(remoteSoruce, localSoruce)


    @Singleton
    @Provides
    fun provideCreateChat(
        repo: ChatRepository
    ) = CreateChatUseCase(repo)

    @Singleton
    @Provides
    fun provideGetChat(
        repo: ChatRepository
    ) = GetChatsUseCase(repo)


    @Singleton
    @Provides
    fun provideSendMessage(
        repo: MessagesRepository
    ) = SendMessageUseCase(repo)

    @Singleton
    @Provides
    fun provideGetMessages(
        repo: MessagesRepository
    ) = GetMessagesUseCase(repo)
}