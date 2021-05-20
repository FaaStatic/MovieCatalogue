package com.suhaili.submisionjetpack.util

import com.suhaili.submisionjetpack.data.entitiy.MovieEntity
import com.suhaili.submisionjetpack.data.entitiy.TVEntity
import com.suhaili.submisionjetpack.data.model.GetTVDataModel
import com.suhaili.submisionjetpack.data.model.MovieGetDataModel

object DataDummy {


    fun getAllMovieDB(): List<MovieEntity> {
        val movie = ArrayList<MovieEntity>()

        movie.add(
            MovieEntity(
                1,
                "460465",
                "Mortal Kombat",
                "2021",
                "7.7",
                "/6Wdl9N6dL0Hi0T1qJLWSz6gMLbd.jpg",
            )
        )
        return movie
    }

    fun getAllTVDB(): List<TVEntity> {
        val dataTV = ArrayList<TVEntity>()

        dataTV.add(
            TVEntity(
                1,
                "88396",
                "The Falcon and the Winter Soldier",
                "6",
                "1",
                "2021-03-19",
                "2021-04-23",
                "7.9",
                "/6kbAMLteGO8yyewYau6bJ683sw7.jpg",
            )
        )
        return dataTV
    }


    fun getAllMovies(): ArrayList<MovieGetDataModel> {
        val Movie = ArrayList<MovieGetDataModel>()

        Movie.add(
            MovieGetDataModel(
                " 460465",
                "Get over here.",
                "https://www.mortalkombatmovie.net",
                "Washed-up MMA fighter Cole Young, unaware of his heritage, and hunted by Emperor Shang Tsung's best warrior, Sub-Zero, seeks out and trains with Earth's greatest champions as he prepares to stand against the enemies of Outworld in a high stakes battle for the universe.",
                "Mortal Kombat",
                "/6Wdl9N6dL0Hi0T1qJLWSz6gMLbd.jpg",
                null,
                "Released",
                7.7,
                5441.414,
                null,
                "2021-04-07",
            )
        )

        Movie.add(
            MovieGetDataModel(
                "399566",
                "One Will Fall",
                "https://www.godzillavskong.net/",
                "In a time when monsters walk the Earth, humanity’s fight for its future sets Godzilla and Kong on a collision course that will see the two most powerful forces of nature on the planet collide in a spectacular battle for the ages.",
                "Godzilla vs. Kong",
                "/pgqgaUx1cJb5oZQQ5v0tNARCeBp.jpg",
                null,
                "Released",
                8.1,
                2676.239,
                null,
                "2021-03-24"
            )
        )
        Movie.add(
            MovieGetDataModel(
                "615457",
                "Never underestimate a nobody.",
                "https://www.nobody.movie",
                "Hutch Mansell, a suburban dad, overlooked husband, nothing neighbor — a \\\"nobody.\\\" When two thieves break into his home one night, Hutch's unknown long-simmering rage is ignited and propels him on a brutal path that will uncover dark secrets he fought to leave behind.",
                "Nobody",
                "/oBgWY00bEFeZ9N25wWVyuQddbAo.jpg",
                null,
                "Released",
                null,
                null,
                null,
                "2021-03-26"
            )
        )
        Movie.add(
            MovieGetDataModel(
                "567189",
                "",
                "https://www.amazon.com/dp/B08VFD1Y3B",
                "An elite Navy SEAL uncovers an international conspiracy while seeking justice for the murder of his pregnant wife.",
                "Tom Clancy's Without Remorse",
                "/rEm96ib0sPiZBADNKBHKBv5bve9.jpg",
                null,
                "Released",
                null,
                null,
                null,
                "2021-04-29"
            )
        )
        Movie.add(
            MovieGetDataModel(
                "804435",
                "She's got one night to save her life.",
                "https://www.lionsgate.com/movies/vanquish",
                "Victoria is a young mother trying to put her dark past as a Russian drug courier behind her, but retired cop Damon forces Victoria to do his bidding by holding her daughter hostage. Now, Victoria must use guns, guts and a motorcycle to take out a series of violent gangsters—or she may never see her child again.",
                "Vanquish",
                "/AoWY1gkcNzabh229Icboa1Ff0BM.jpg",
                null,
                "Released",
                6.4,
                2254.539,
                null,
                "2021-04-16"
            )
        )

        return Movie
    }


    fun getAllTV(): ArrayList<GetTVDataModel> {
        val TV = ArrayList<GetTVDataModel>()
        TV.add(
            GetTVDataModel(
                88396,
                "The Falcon and the Winter Soldier",
                "Honor the shield.",
                "2021-03-19",
                "2021-04-23",
                "Following the events of “Avengers: Endgame”, the Falcon, Sam Wilson and the Winter Soldier, Bucky Barnes team up in a global adventure that tests their abilities, and their patience.",
                "6",
                "1",
                null,
                "/6kbAMLteGO8yyewYau6bJ683sw7.jpg",
                null,
                null,
                "The Falcon and the Winter Soldier",
                "1789.883",
                "7.9",
                "https://www.disneyplus.com/series/the-falcon-and-the-winter-soldier/4gglDBMx8icA",
                "Ended",
            )
        )

        return TV
    }


    fun getTV(id: String): GetTVDataModel {
        val TVList = getAllTV()
        var dataGet = GetTVDataModel()
        for (i in 0 until TVList.size) {
            if (TVList[i].id.toString() == id) {
                dataGet = TVList[i]
                break
            }
        }
        return dataGet
    }

    fun getMovie(id: String): MovieGetDataModel {
        val MovieList = getAllMovies()
        var dataGet = MovieGetDataModel()
        for (i in 0 until MovieList.size) {
            if (MovieList[i].id == id) {
                dataGet = MovieList[i]
                break
            }
        }
        return dataGet
    }


}