package com.dongeul.adpager.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dongeul.adpager.model.Content
import com.dongeul.adpager.model.Result
import com.dongeul.adpager.repository.PagerRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PagerViewModel @Inject constructor(
    private val pagingRepository: PagerRepository
) : ViewModel() {

    val content = MutableLiveData<Content>()

    fun hideContent() {
        viewModelScope.launch {
            content.value?.let { pagingRepository.deleteContent(content = it) }
        }
    }

    fun toggleLike() {
        viewModelScope.launch {
            val contentLiveData = content?.value
            contentLiveData?.isLiked = contentLiveData?.isLiked != true
            contentLiveData?.let {
                content.postValue(it)
            }
            content.value?.let { pagingRepository.updateContent(content= it) }
        }
    }

    val post: StateFlow<Result<List<Content>>> = pagingRepository.getRemoteData()
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(1000),
            initialValue = Result.Uninitialized
        )
}