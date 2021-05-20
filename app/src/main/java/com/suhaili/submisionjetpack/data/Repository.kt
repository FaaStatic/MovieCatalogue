package com.suhaili.submisionjetpack.data

import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.suhaili.submisionjetpack.api.APIConfig
import com.suhaili.submisionjetpack.data.entitiy.MovieEntity
import com.suhaili.submisionjetpack.data.entitiy.TVEntity
import com.suhaili.submisionjetpack.data.model.*
import com.suhaili.submisionjetpack.util.AppExecutors
import com.suhaili.submisionjetpack.util.EspressoIdlingResource
import com.suhaili.submisionjetpack.util.Event
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class Repository constructor(
    private val localData: LocalDataSource,
    private val AppExecute: AppExecutors
) {

    companion object {
        @Volatile
        private var Instance: Repository? = null
        fun getInstance(
            ctx: Context,
            localData: LocalDataSource,
            AppExecute: AppExecutors
        ): Repository =
            Instance ?: synchronized(this) {
                Instance ?: Repository(localData, AppExecute).apply {
                    Instance = this
                }
            }
    }


    private val MovieData = MutableLiveData<ArrayList<MovieGetDataModel>>()
    fun getMovieData(): MutableLiveData<ArrayList<MovieGetDataModel>> = this.MovieData


    private val TVData = MutableLiveData<ArrayList<GetTVDataModel>>()
    fun getTVData(): MutableLiveData<ArrayList<GetTVDataModel>> = this.TVData


    private val MovieDetail = MutableLiveData<MovieGetDataModel>()
    fun getMovieDetailData(): MutableLiveData<MovieGetDataModel> = this.MovieDetail


    private val TVDetail = MutableLiveData<GetTVDataModel>()
    fun getTVDetailData(): MutableLiveData<GetTVDataModel> = this.TVDetail

    private val isLoading = MutableLiveData<Event<Boolean>>(Event(true))
    fun getIsLoad(): MutableLiveData<Event<Boolean>> = this.isLoading


    private val tempTV = ArrayList<GetTVDataModel>()
    private val tempMovie = ArrayList<MovieGetDataModel>()


    fun getAllMovies() {
        EspressoIdlingResource.increment()
        val ApiCLient = APIConfig.GetMovieService().getAllMovie()
        ApiCLient.enqueue(object : Callback<UpcomingMovieModel> {
            override fun onResponse(
                call: Call<UpcomingMovieModel>,
                response: Response<UpcomingMovieModel>
            ) {
                if (response.isSuccessful) {
                    val dataResp = response.body()?.results!!

                    for (i in 0 until dataResp.size) {
                        val id = dataResp[i].id.toString()
                        getMovies(id)
                    }
                    EspressoIdlingResource.decrement()
                    Log.d("TAG", "${dataResp}")
                }
            }

            override fun onFailure(call: Call<UpcomingMovieModel>, t: Throwable) {
                Log.d("TAG", "$t.message")
            }
        })

    }


    fun getAllTV() {
        EspressoIdlingResource.increment()
        val ApiClient = APIConfig.GetTVService().getAllTV()
        ApiClient.enqueue(object : Callback<OnAirTVModel> {
            override fun onResponse(call: Call<OnAirTVModel>, response: Response<OnAirTVModel>) {
                if (response.isSuccessful) {
                    val dataResp = response.body()?.results!!

                    for (i in 0 until dataResp.size) {
                        val id = dataResp[i].id.toString()
                        getTV(id)
                    }
                    EspressoIdlingResource.decrement()
                }
            }

            override fun onFailure(call: Call<OnAirTVModel>, t: Throwable) {
                t.printStackTrace()
            }
        })

    }


    fun getMovies(id: String) {
        EspressoIdlingResource.increment()
        var stat = false
        val API = APIConfig.GetMovieService().getDetailMovies(id)
        API.enqueue(object : Callback<MovieDetailServiceModel> {
            override fun onResponse(
                call: Call<MovieDetailServiceModel>,
                response: Response<MovieDetailServiceModel>
            ) {
                if (response.isSuccessful) {
                    val dataResp = response.body()!!
                    val temp = MovieGetDataModel()
                    temp.id = dataResp.id.toString()
                    temp.homepage = dataResp.homepage
                    temp.genres = dataResp?.genres
                    temp.originalTitle = dataResp.originalTitle
                    temp.overview = dataResp.overview
                    temp.posterPath = dataResp.posterPath
                    temp.popularity = dataResp.popularity
                    temp.productionCompanies = dataResp.productionCompanies
                    temp.releaseDate = dataResp.releaseDate
                    temp.status = dataResp.status
                    temp.voteAverage = dataResp.voteAverage
                    temp.tagline = dataResp.tagline

                    if (tempMovie.size > 0) {
                        for (i in 0 until tempMovie.size) {
                            if (tempMovie[i].id == temp.id) {
                                stat = true
                                break
                            }
                        }
                    }
                    if (stat) {

                    } else {
                        tempMovie.add(temp)
                        MovieData.postValue(tempMovie)

                    }
                    EspressoIdlingResource.decrement()
                }
            }

            override fun onFailure(call: Call<MovieDetailServiceModel>, t: Throwable) {
                t.printStackTrace()
            }
        })

    }


    fun getTV(id: String) {
        EspressoIdlingResource.increment()
        var stat = false
        val ApiClient = APIConfig.GetTVService().getDetailTV(id)
        ApiClient.enqueue(object : Callback<TVDetailServiceModel> {
            override fun onResponse(
                call: Call<TVDetailServiceModel>,
                response: Response<TVDetailServiceModel>
            ) {
                if (response.isSuccessful) {
                    val temp = response.body()
                    val data = GetTVDataModel()
                    data.id = temp?.id
                    data.numberOfSeasons = temp?.numberOfSeasons.toString()
                    data.numberOfEpisodes = temp?.numberOfEpisodes.toString()
                    data.voteAverage = temp?.voteAverage.toString()
                    data.homepage = temp?.homepage
                    data.firstAirDate = temp?.firstAirDate
                    data.lastAirDate = temp?.lastAirDate
                    data.genres = temp?.genres
                    data.name = temp?.name
                    data.overview = temp?.overview
                    data.tagline = temp?.tagline
                    data.status = temp?.status
                    data.popularity = temp?.popularity.toString()
                    data.posterPath = temp?.posterPath
                    data.productionCompanies = temp?.productionCompanies
                    data.networks = temp?.networks
                    data.originalName = temp?.originalName
                    if (tempTV.size > 0) {
                        for (i in 0 until tempTV.size) {
                            if (tempTV[i].id == data.id) {
                                stat = true
                                break
                            }
                        }
                    }
                    if (stat) {

                    } else {
                        tempTV.add(data)
                        TVData.postValue(tempTV)
                    }

                }
                EspressoIdlingResource.decrement()
            }

            override fun onFailure(call: Call<TVDetailServiceModel>, t: Throwable) {
                t.printStackTrace()
            }
        })

    }


    fun getDetailMovie(id: String) {
        EspressoIdlingResource.increment()
        val API = APIConfig.GetMovieService().getDetailMovies(id)
        API.enqueue(object : Callback<MovieDetailServiceModel> {
            override fun onResponse(
                call: Call<MovieDetailServiceModel>,
                response: Response<MovieDetailServiceModel>
            ) {
                if (response.isSuccessful) {
                    val model = MovieGetDataModel()
                    val dataResp = response.body()!!
                    model.id = dataResp.id.toString()
                    model.posterPath = dataResp.posterPath
                    model.tagline = dataResp.tagline
                    model.homepage = dataResp.homepage
                    model.popularity = dataResp.popularity
                    model.overview = dataResp.overview
                    model.releaseDate = dataResp.releaseDate
                    model.voteAverage = dataResp.voteAverage
                    model.status = dataResp.status
                    model.tagline = dataResp.tagline
                    model.originalTitle = dataResp.originalTitle
                    model.productionCompanies = dataResp.productionCompanies
                    model.genres = dataResp.genres
                    MovieDetail.postValue(model)
                    LoadingProgress(Event(false))
                }
                EspressoIdlingResource.decrement()

            }

            override fun onFailure(call: Call<MovieDetailServiceModel>, t: Throwable) {
                t.printStackTrace()
            }
        })

    }

    fun getTVDetail(id: String) {
        EspressoIdlingResource.increment()
        val Api = APIConfig.GetTVService().getDetailTV(id)
        Api.enqueue(object : Callback<TVDetailServiceModel> {
            override fun onResponse(
                call: Call<TVDetailServiceModel>,
                response: Response<TVDetailServiceModel>
            ) {
                if (response.isSuccessful) {
                    val respData = response.body()
                    val model = GetTVDataModel()
                    model.id = respData?.id
                    model.genres = respData?.genres
                    model.homepage = respData?.homepage
                    model.firstAirDate = respData?.firstAirDate
                    model.name = respData?.name
                    model.networks = respData?.networks
                    model.numberOfEpisodes = respData?.numberOfEpisodes.toString()
                    model.numberOfSeasons = respData?.numberOfSeasons.toString()
                    model.originalName = respData?.originalName
                    model.popularity = respData?.popularity.toString()
                    model.voteAverage = respData?.voteAverage.toString()
                    model.tagline = respData?.tagline
                    model.overview = respData?.overview
                    model.lastAirDate = respData?.lastAirDate
                    model.posterPath = respData?.posterPath
                    model.productionCompanies = respData?.productionCompanies
                    model.status = respData?.status
                    TVDetail.postValue(model)
                    LoadingProgress(Event(false))
                }
                EspressoIdlingResource.decrement()
            }

            override fun onFailure(call: Call<TVDetailServiceModel>, t: Throwable) {
                t.printStackTrace()
            }
        })

    }

    fun LoadingProgress(stat: Event<Boolean>) {
        this.isLoading.value = stat
    }

    fun getAllDBTV(): LiveData<List<TVEntity>> = localData.getAllTVList()

    fun getAllDBTVPaging(): LiveData<PagedList<TVEntity>> {
        val config = PagedList.Config.Builder()
            .setEnablePlaceholders(false)
            .setInitialLoadSizeHint(5)
            .setPageSize(5)
            .build()
        return LivePagedListBuilder(localData.getAllTVPaging(), config).build()
    }


    fun insertTV(value: TVEntity) {
        AppExecute.diskIO().execute { localData.insertTV(value) }
    }

    fun deleteTV(value: TVEntity) {
        AppExecute.diskIO().execute { localData.deleteTV(value) }
    }

    fun getAllDBMoviePaging(): LiveData<PagedList<MovieEntity>> {
        val config = PagedList.Config.Builder()
            .setEnablePlaceholders(false)
            .setInitialLoadSizeHint(5)
            .setPageSize(5)
            .build()
        return LivePagedListBuilder(localData.getAllMoviePaging(), config).build()
    }

    fun getAllMovieDB(): LiveData<List<MovieEntity>> = localData.getAllMovieList()


    fun insertMovie(value: MovieEntity) {
        AppExecute.diskIO().execute { localData.insertMovie(value) }
    }

    fun deleteMovie(value: MovieEntity) {
        AppExecute.diskIO().execute { localData.deleteMovie(value) }
    }

}

