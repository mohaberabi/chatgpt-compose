package com.mohaberabi.chatgpt.core.data.database.di

import android.content.Context
import androidx.room.Room
import com.mohaberabi.chatgpt.core.data.database.AppDatabase
import com.mohaberabi.chatgpt.core.data.database.dao.ChatDao
import com.mohaberabi.chatgpt.core.data.database.dao.MessageDao
import com.mohaberabi.chatgpt.core.data.database.source.RoomChatLocalDataSource
import com.mohaberabi.chatgpt.core.data.database.source.RoomMessagesLocalDataSource
import com.mohaberabi.chatgpt.core.domain.model.DispatchersProvider
import com.mohaberabi.chatgpt.core.domain.source.local.ChatLocalDataSource
import com.mohaberabi.chatgpt.core.domain.source.local.MessagesLocalDataSource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)


object DatabaseModule {


    @Singleton
    @Provides
    fun provideDatabase(@ApplicationContext context: Context): AppDatabase = Room.databaseBuilder(
        context,
        AppDatabase::class.java,
        "chatgpt.db"
    ).build()

    @Singleton
    @Provides
    fun provideMessageDao(db: AppDatabase): MessageDao = db.messagesDao()

    @Singleton
    @Provides
    fun provideChatDao(db: AppDatabase): ChatDao = db.chatDao()

    @Singleton
    @Provides
    fun provideChatLocalDataSource(
        dao: ChatDao,
        dispatchersProvider: DispatchersProvider
    ): ChatLocalDataSource =
        RoomChatLocalDataSource(dispatchersProvider, dao)

    @Singleton
    @Provides
    fun provideMessagesLocalDataSource(
        dao: MessageDao,
        dispatchersProvider: DispatchersProvider
    ): MessagesLocalDataSource =
        RoomMessagesLocalDataSource(dispatchersProvider, dao)
}