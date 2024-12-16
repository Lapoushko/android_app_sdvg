package com.example.android_app_sdvg.presentation.tasker.filter


import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.android_app_sdvg.presentation.model.task.DatesItem
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class FilterScreenViewModel @Inject constructor(): ViewModel() {
    private var _state = MutableFilterScreenState()
    val state = _state as FilterScreenState

    fun saveFilter(onToBack : () -> Unit){
        onToBack()
    }

    fun addFilter(filter: String){
        _state.filtersPriority.add(filter)
    }

    fun removeFilter(filter: String){
        _state.filtersPriority.remove(filter)
    }

    fun toggleCalendar() {
        _state.showModal = !_state.showModal
    }

    fun selectDate(newDate: Pair<Long?, Long?>) {
        if (newDate.second == null){
            _state.selectedDates = DatesItem(newDate.first ?: 0L, newDate.first ?: 0L)
        } else{
            _state.selectedDates = DatesItem(newDate.first ?: 0L, newDate.second!!)
        }
    }

    private class MutableFilterScreenState: FilterScreenState {
        override var filtersPriority: MutableSet<String> by mutableStateOf(mutableSetOf())
        override var sortParam: String by mutableStateOf("")
        override var selectedDates: DatesItem by mutableStateOf(DatesItem(0,0))
        override var showModal: Boolean by mutableStateOf(false)
    }
}
