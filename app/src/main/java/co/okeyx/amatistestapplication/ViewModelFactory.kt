package co.okeyx.amatistestapplication

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import co.okeyx.amatistestapplication.repository.MainRepository
import co.okeyx.amatistestapplication.viewModel.MainViewModel

class ViewModelFactory(private val repository: MainRepository): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
            MainViewModel(this.repository) as T
        } else {
            throw IllegalArgumentException("ViewModel Not Found")
        }
    }
}