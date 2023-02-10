package co.okeyx.amatistestapplication.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import co.okeyx.amatistestapplication.RetrofitService
import co.okeyx.amatistestapplication.model.CoinResponseModel

class CoinPagingSource(
    private val apiService: RetrofitService
) : PagingSource<Int, CoinResponseModel>() {

    override fun getRefreshKey(state: PagingState<Int, CoinResponseModel>): Int? {
        return null
    }

    override suspend fun load(params: LoadParams<Int>):
            LoadResult<Int, CoinResponseModel> {

        return try {
            val currentPage = params.key ?: 1
            val response = apiService.callApi(currentPage, 30)
            val responseData = mutableListOf<CoinResponseModel>()
            val data = response.body()?.result
            responseData.addAll(data)

            LoadResult.Page(
                data = responseData,
                prevKey = if (currentPage == 1) null else -1,
                nextKey = currentPage.plus(1)
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }

    }
}