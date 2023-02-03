package com.example.clubreservations.di

import android.app.Application
import com.example.clubreservations.data.EventDao
import com.example.clubreservations.data.TableDao
import com.example.clubreservations.data.repository.EventRepository
import com.example.clubreservations.data.repository.EventRepositoryImpl
import com.example.clubreservations.data.repository.TableRepository
import com.example.clubreservations.data.repository.TableRepositoryImpl
import com.example.clubreservations.data.room.EventDatabase
import com.example.clubreservations.data.room.TableDatabase
import com.example.clubreservations.presentation.event.EventDetailsViewModel
import com.example.clubreservations.presentation.event.EventListViewModel
import com.example.clubreservations.presentation.event.EventNewViewModel
import com.example.clubreservations.presentation.table.TableDetailsViewModel
import com.example.clubreservations.presentation.table.TableListViewModel
import com.example.clubreservations.presentation.table.TableNewViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import java.lang.reflect.Array.get


val databaseModule = module {
    fun provideDatabase(application: Application): EventDatabase {
        return EventDatabase.getDatabase(application)
    }
    fun provideEventDao(database: EventDatabase): EventDao {
        return database.getEventDao()
    }
    single<EventDatabase> { provideDatabase(get()) }
    single<EventDao> { provideEventDao(get()) }

    fun provideDatabase2(application: Application): TableDatabase {
        return TableDatabase.getDatabase(application)
    }
    fun provideTableDao(database: TableDatabase): TableDao {
        return database.getTableDao()
    }
    single<TableDatabase> { provideDatabase2(get()) }
    single<TableDao> { provideTableDao(get()) }
}

val repositoryModule = module {
    single<EventRepository> { EventRepositoryImpl(get()) }

    single<TableRepository> { TableRepositoryImpl(get()) }
}

val viewmodelModule = module {
    //Event viewModels
    viewModel { EventListViewModel(get()) }
    viewModel { EventNewViewModel(get()) }
    viewModel { EventDetailsViewModel(get()) }
    //Table viewModels
    viewModel { TableListViewModel(get()) }
    viewModel { TableNewViewModel(get()) }
    viewModel { TableDetailsViewModel(get()) }

}