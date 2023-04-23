package com.example.fourthsemestr1.di.annotation

import javax.inject.Qualifier

@Qualifier
@Retention(AnnotationRetention.RUNTIME)
annotation class ApiKey

@Qualifier
@Retention(AnnotationRetention.RUNTIME)
annotation class Units

@Qualifier
@Retention(AnnotationRetention.RUNTIME)
annotation class Lang

@Qualifier
@Retention(AnnotationRetention.RUNTIME)
annotation class Logger
