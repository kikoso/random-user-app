package com.enrique.randomuserapp.core

sealed class Failure {
    object NetworkConnection : Failure()
    object GeneralFailure: Failure()
    abstract class FeatureFailure: Failure()
}