package com.archi4zlo.shoppinglist.di

import android.content.Context
import androidx.room.Room
import com.archi4zlo.shoppinglist.data.local.ShoppingDao
import com.archi4zlo.shoppinglist.data.local.ShoppingItem
import com.archi4zlo.shoppinglist.data.local.ShoppingItemDatabase
import com.archi4zlo.shoppinglist.data.remote.response.PixabayAPI
import com.archi4zlo.shoppinglist.other.Constants.BASE_URL
import com.archi4zlo.shoppinglist.other.Constants.DATABASE_NAME
import com.archi4zlo.shoppinglist.repositories.DefaultShoppingRepository
import com.archi4zlo.shoppinglist.repositories.ShoppingRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideShoppingItemDatabase(
        @ApplicationContext context: Context
    ) = Room.databaseBuilder(context,ShoppingItemDatabase::class.java,DATABASE_NAME).build()


    @Singleton
    @Provides
    fun provideDefaultShoppingRepository(
        dao: ShoppingDao,
        api: PixabayAPI
    ) = DefaultShoppingRepository(dao,api) as ShoppingRepository
    @Singleton
    @Provides
    fun provideShoppingDao(
        database: ShoppingItemDatabase
    ) = database.shoppingDao()

    @Singleton
    @Provides
    fun providePixabayAPI(): PixabayAPI{
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BASE_URL)
            .build()
            .create(PixabayAPI::class.java)
    }


}