package com.android.elk.common

@Target(
    AnnotationTarget.FUNCTION,
    AnnotationTarget.EXPRESSION
)
@Retention(AnnotationRetention.SOURCE)
annotation class Given (
    val description: String
)

@Target(
    AnnotationTarget.FUNCTION,
    AnnotationTarget.EXPRESSION
)
@Retention(AnnotationRetention.SOURCE)
annotation class When (
    val description: String
)

@Target(
    AnnotationTarget.FUNCTION,
    AnnotationTarget.EXPRESSION
)
@Retention(AnnotationRetention.SOURCE)
annotation class Then (
    val description: String
)

@Target(
    AnnotationTarget.FUNCTION,
    AnnotationTarget.EXPRESSION
)
@Retention(AnnotationRetention.SOURCE)
annotation class And (
    val description: String
)
