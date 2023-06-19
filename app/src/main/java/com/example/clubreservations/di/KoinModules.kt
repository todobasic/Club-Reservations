package com.example.clubreservations.di

import android.app.Application
import com.example.clubreservations.data.EventDao
import com.example.clubreservations.data.repository.EventRepository
import com.example.clubreservations.data.repository.EventRepositoryImpl
import com.example.clubreservations.data.room.EventDatabase
import com.example.clubreservations.presentation.event.EventDetailsViewModel
import com.example.clubreservations.presentation.event.EventListViewModel
import com.example.clubreservations.presentation.event.EventNewViewModel
import com.example.clubreservations.presentation.table.TableNewViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module


val databaseModule = module {
    fun provideDatabase(application: Application): EventDatabase {
        return EventDatabase.getDatabase(application)
    }

    fun provideEventDao(database: EventDatabase): EventDao {
        return database.getEventDao()
    }
    single<EventDatabase> { provideDatabase(get()) }
    single<EventDao> { provideEventDao(get()) }
}

val repositoryModule = module {
    single<EventRepository> { EventRepositoryImpl(get()) }

}

val viewmodelModule = module {
    //Event viewModels
    viewModel { EventListViewModel(get()) }
    viewModel { EventNewViewModel(get()) }
    viewModel { EventDetailsViewModel(get()) }
    //Table viewModels
    viewModel { TableNewViewModel(get()) }

}