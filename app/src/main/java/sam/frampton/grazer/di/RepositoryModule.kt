package sam.frampton.grazer.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import sam.frampton.grazer.data.auth.AuthDataStoreRepository
import sam.frampton.grazer.data.auth.AuthRepository
import sam.frampton.grazer.data.users.DefaultUsersRepository
import sam.frampton.grazer.data.users.UsersRepository
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
abstract class RepositoryModule {

    @Singleton
    @Binds
    abstract fun bindAuthRepository(
        authRepository: AuthDataStoreRepository
    ): AuthRepository

    @Singleton
    @Binds
    abstract fun bindUsersRepository(
        usersRepository: DefaultUsersRepository
    ): UsersRepository
}
