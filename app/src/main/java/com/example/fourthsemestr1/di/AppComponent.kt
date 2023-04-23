package com.example.fourthsemestr1.di

import com.example.fourthsemestr1.WeatherApp
import com.example.fourthsemestr1.di.module.AppModule
import com.example.fourthsemestr1.di.module.NetModule
import com.example.fourthsemestr1.di.module.RepoModule
import com.example.fourthsemestr1.di.module.ViewModelModule
import com.example.fourthsemestr1.presentation.MainActivity
import com.example.fourthsemestr1.presentation.fragments.cities.SearchFragment
import com.example.fourthsemestr1.presentation.fragments.weather.DetailsFragment
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AppModule::class,
        NetModule::class,
        RepoModule::class,
        ViewModelModule::class
    ]
)
interface AppComponent {

    fun inject(mainActivity: MainActivity)
    fun inject(searchFragment: SearchFragment)
    fun inject(detailsFragment: DetailsFragment)

    @Component.Builder
    interface Builder {
        fun build(): AppComponent

        @BindsInstance
        fun application(application: WeatherApp): Builder
    }
}
