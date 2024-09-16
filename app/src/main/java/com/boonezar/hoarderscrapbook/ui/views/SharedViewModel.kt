package com.boonezar.hoarderscrapbook.ui.views

import androidx.lifecycle.ViewModel
import com.boonezar.hoarderscrapbook.storage.repositories.MemoryRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

// Based on "Scoping shared View Model dependencies in Single Activity Android Apps using Hilt",
// which solved a couple issues I was running into.
// https://medium.com/@itjdeveloper/scoping-shared-view-model-dependencies-in-single-activity-android-apps-using-hilt-5793bea7a389
@HiltViewModel
class SharedViewModel @Inject constructor(
    val memoryRepository: MemoryRepository
): ViewModel()