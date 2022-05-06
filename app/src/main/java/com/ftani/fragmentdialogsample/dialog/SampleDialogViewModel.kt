package com.ftani.fragmentdialogsample.dialog

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class SampleDialogViewModel : ViewModel() {
    private val _state: MutableLiveData<SampleDialogState> = MutableLiveData(SampleDialogState())
    val state: LiveData<SampleDialogState> = _state

    fun onClickPositive() {
        val currentEvents = _state.value?.events ?: return
        val newEvents = mutableListOf<Event>()
        newEvents.addAll(currentEvents)
        newEvents.add(Event.POSITIVE)
        _state.value = SampleDialogState(newEvents)
    }

    fun onClickNegative() {
        val currentEvents = _state.value?.events ?: return
        val newEvents = mutableListOf<Event>()
        newEvents.addAll(currentEvents)
        newEvents.add(Event.NEGATIVE)
        _state.value = SampleDialogState(newEvents)
    }

    fun consumeEvent(event: Event) {
        val currentEvents = _state.value?.events ?: return
        val newEvents = currentEvents.filterNot { it == event }
        _state.value = SampleDialogState(newEvents)
    }

    data class SampleDialogState(
        val events: List<Event> = emptyList()
    )
}