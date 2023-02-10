package co.okeyx.amatistestapplication

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.NestedScrollView
import androidx.lifecycle.ViewModelProvider
import co.okeyx.amatistestapplication.databinding.ActivityMainBinding
import co.okeyx.amatistestapplication.repository.MainRepository
import co.okeyx.amatistestapplication.viewModel.MainViewModel


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    lateinit var viewModel: MainViewModel
    private val adapter = Adapter()
    var page = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initViewModel(page)

        binding.nestedView.setOnScrollChangeListener(NestedScrollView.OnScrollChangeListener { v, scrollX, scrollY, oldScrollX, oldScrollY ->
            // on scroll change we are checking when users scroll as bottom.
            if (scrollY == v.getChildAt(0).measuredHeight - v.measuredHeight) {
                // in this method we are incrementing page number,
                // making progress bar visible and calling get data method.
                page = page.plus(1)
                binding.loading.visibility = View.VISIBLE
                initViewModel(page)
            }
        })
    }

    private fun initViewModel(pageNo: Int) {
        Log.i("TAG", "initViewModel: $pageNo")
        viewModel = ViewModelProvider(
            this,
            ViewModelFactory(MainRepository(MyApplication.getRetrofitService(), pageNo, 30))
        )[MainViewModel::class.java]

        binding.recyclerView.adapter = adapter

        viewModel.coinList.observe(this) {
            adapter.setList(it)
        }
        viewModel.errorMessage.observe(this) {
            Log.e("TAG", "onCreate: $it")
        }

        viewModel.getAllCoins()

    }

    override fun onStart() {
        super.onStart()
        MyApplication.currentActivity = this
    }

    override fun onResume() {
        super.onResume()
        MyApplication.currentActivity = this
    }
}