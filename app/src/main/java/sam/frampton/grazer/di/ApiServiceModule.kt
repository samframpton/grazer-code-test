package sam.frampton.grazer.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import sam.frampton.grazer.data.api.GrazerService
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object ApiServiceModule {

    @Singleton
    @Provides
    fun provideGrazerService() = GrazerService.create()
}
