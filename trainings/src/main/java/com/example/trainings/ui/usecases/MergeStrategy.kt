package com.example.trainings.ui.usecases

import com.example.trainings.data.RequestResult
import kotlinx.coroutines.flow.Flow

class MergeStrategy<T: Any> {

//    fun  merge(
//        remote: RequestResult<T>,
//        cached: RequestResult<T>
//    ): RequestResult<T> {
//        remote is RequestResult.InProgress && cached is RequestResult.InProgress -> merge(remote, cached)
//        remote is RequestResult.Success && cached is RequestResult.InProgress -> merge(remote, cached)
//        remote is RequestResult.InProgress && cached is RequestResult.Success -> merge(remote, cached)
//        remote is RequestResult.Success && cached is RequestResult.Success -> merge(remote, cached)
//        remote is RequestResult.Success && cached is Error -> merge(remote, cached)
//        remote is RequestResult.InProgress && cached is Error -> merge(remote, cached)
//        remote is Error && cached is RequestResult.InProgress -> merge(remote, cached)
//        remote is Error && cached is RequestResult.Success -> merge(remote, cached)
//    }
}