package com.alankurniadi.submission2jatpackpromovie.utils

import com.alankurniadi.submission2jatpackpromovie.data.source.local.entity.NowAiringTv
import com.alankurniadi.submission2jatpackpromovie.data.source.local.entity.NowPlayingMovie
import com.alankurniadi.submission2jatpackpromovie.data.source.local.entity.TrendingWeek
import com.alankurniadi.submission2jatpackpromovie.data.source.remote.response.NowAiringTvResponse
import com.alankurniadi.submission2jatpackpromovie.data.source.remote.response.NowPlayingResponse
import com.alankurniadi.submission2jatpackpromovie.data.source.remote.response.TrendingResponse

object DataDummy {

    //LOCAL
    fun getTrending(): List<TrendingWeek> {
        val trending = ArrayList<TrendingWeek>()
        trending.add(TrendingWeek(
            464052,
            "/srYya1ZlI97Au4jUYAktDe3avyA.jpg",
            "Wonder Woman 1984",
            "Wonder Woman 1984",
            "/8UlWHLMpgZm9bx6QYh0NFoq67TZ.jpg",
            7.3,
            "2020-12-16",
            "2020-12-16",
            "Wonder Woman comes into conflict with the Soviet Union during the Cold War in the 1980s and finds a formidable foe by the name of the Cheetah.",
            "movie",
            true
        ))

        trending.add(TrendingWeek(
            508442,
            "/kf456ZqeC45XTvo6W9pW5clYKfQ.jpg",
            "Soul",
            "Soul",
            "/zSuRX1VmT0PwuHVV7zl4n1mwdL0.jpg",
            8.5,
            "2020-12-16",
            "2019-11-12",
            "Joe Gardner is a middle school teacher with a love for jazz music. After a successful gig at the Half Note Club, he suddenly gets into an accident that separates his soul from his body and is transported to the You Seminar, a center in which souls develop and gain passions before being transported to a newborn child. Joe must enlist help from the other souls-in-training, like 22, a soul who has spent eons in the You Seminar, in order to get back to Earth.",
            "movie",
            true
        ))

        trending.add(TrendingWeek(
            577922,
            "/wzJRB4MKi3yK138bJyuL9nx47y6.jpg",
            "Tenet",
            "Tenet",
            "/k68nPLbIST6NP96JmTxmZijEvCA.jpg",
            7.4,
            "2020-08-22",
            "2020-08-22",
            "Armed with only one word - Tenet - and fighting for the survival of the entire world, the Protagonist journeys through a twilight world of international espionage on a mission that will unfold in something beyond real time.",
            "movie",
            true
        ))

        trending.add(TrendingWeek(
            614911,
            "/dueiWzWc81UAgnbDAyH4Gjqnh4n.jpg",
            "The Midnight Sky",
            "The Midnight Sky",
            "/51JxCk77ZCqLzbLkrDl9Qho6KUh.jpg",
            6.0,
            "2020-12-10",
            "2020-12-10",
            "A lone scientist in the Arctic races to contact a crew of astronauts returning home to a mysterious global catastrophe.",
            "movie",
            true
        ))

        trending.add(TrendingWeek(
            82856,
            "/o7qi2v4uWQ8bZ1tW3KI0Ztn2epk.jpg",
            "The Mandalorian",
            "The Mandalorian",
            "/sWgBv7LV2PRoQgkxwlibdGXKz1S.jpg",
            8.5,
            "2019-11-12",
            "2019-11-12",
            "After the fall of the Galactic Empire, lawlessness has spread throughout the galaxy. A lone gunfighter makes his way through the outer reaches, earning his keep as a bounty hunter.",
            "tv",
            true
        ))

        return trending
    }

    fun getNowPlayingMovie(): List<NowPlayingMovie> {
        val movie = ArrayList<NowPlayingMovie>()

        movie.add(NowPlayingMovie(
            464052,
            "/srYya1ZlI97Au4jUYAktDe3avyA.jpg",
            "Wonder Woman comes into conflict with the Soviet Union during the Cold War in the 1980s and finds a formidable foe by the name of the Cheetah.",
            "/8UlWHLMpgZm9bx6QYh0NFoq67TZ.jpg",
            "2020-12-16",
            "Wonder Woman 1984",
            7.3,
            true
        ))

        movie.add(NowPlayingMovie(
            508442,
            "/kf456ZqeC45XTvo6W9pW5clYKfQ.jpg",
            "Joe Gardner is a middle school teacher with a love for jazz music. After a successful gig at the Half Note Club, he suddenly gets into an accident that separates his soul from his body and is transported to the You Seminar, a center in which souls develop and gain passions before being transported to a newborn child. Joe must enlist help from the other souls-in-training, like 22, a soul who has spent eons in the You Seminar, in order to get back to Earth.",
            "/zSuRX1VmT0PwuHVV7zl4n1mwdL0.jpg",
            "2020-12-25",
            "Soul",
            8.5,
            true
        ))

        movie.add(NowPlayingMovie(
            529203,
            "/cjaOSjsjV6cl3uXdJqimktT880L.jpg",
            "Searching for a safer habitat, the prehistoric Crood family discovers an idyllic, walled-in paradise that meets all of its needs. Unfortunately, they must also learn to live with the Bettermans -- a family that's a couple of steps above the Croods on the evolutionary ladder. As tensions between the new neighbors start to rise, a new threat soon propels both clans on an epic adventure that forces them to embrace their differences, draw strength from one another, and survive together.",
            "/tK1zy5BsCt1J4OzoDicXmr0UTFH.jpg",
            "2020-11-25",
            "The Croods: A New Age",
            7.8,
            true
        ))

        movie.add(NowPlayingMovie(
            553604,
            "/jIJfDVGKM8GXgpJc2XH2x7e11wm.jpg",
            "A bank robber tries to turn himself in because he's falling in love and wants to live an honest life...but when he realizes the Feds are more corrupt than him, he must fight back to clear his name.",
            "/zeD4PabP6099gpE0STWJrJrCBCs.jpg",
            "2020-09-03",
            "Honest Thief",
            6.8,
            true
        ))

        movie.add(NowPlayingMovie(
            614911,
            "/dueiWzWc81UAgnbDAyH4Gjqnh4n.jpg",
            "A lone scientist in the Arctic races to contact a crew of astronauts returning home to a mysterious global catastrophe.",
            "/51JxCk77ZCqLzbLkrDl9Qho6KUh.jpg",
            "2020-12-10",
            "The Midnight Sky",
            6.0,
            true
        ))
        return movie
    }

    fun getAiringTvShow(): List<NowAiringTv> {
        val tvShow = ArrayList<NowAiringTv>()

        tvShow.add(NowAiringTv(
            89844,
            "/uSvncE20Mh3seJLIHLEQnt0OcBv.jpg",
            "2020-11-29",
            "30 Coins",
            "Father Vergara—an exorcist, boxer and ex-convict—lives in a remote village in Spain. Hoping to be lost and forgotten, Vergaras demons catch up to him.",
            "/b2i9aaV6ncULl9jYXEoPi7VFJg8.jpg",
            6.3,
            true
        ))

        tvShow.add(NowAiringTv(
            456,
            "/hpU2cHC9tk90hswCFEpf5AtbqoL.jpg",
            "1989-12-16",
            "The Simpsons",
            "Set in Springfield, the average American town, the show focuses on the antics and everyday adventures of the Simpson family; Homer, Marge, Bart, Lisa and Maggie, as well as a virtual cast of thousands. Since the beginning, the series has been a pop culture icon, attracting hundreds of celebrities to guest star. The show has also made name for itself in its fearless satirical take on politics, media and American life in general.",
            "/2IWouZK4gkgHhJa3oyYuSWfSqbG.jpg",
            7.8,
            true
        ))

        tvShow.add(NowAiringTv(
            111494,
            "/auBeh4qK54vC83ZkvVWxTXKPhLl.jpg",
            "2020-12-27",
            "Física o química: El reencuentro",
            "ajdfaj;df",
            "/k7YdhfvoCn0BB5eOLvvqG7lFWv1.jpg",
            4.0,
            true
        ))

        tvShow.add(NowAiringTv(
            96884,
            "/rhXkhRXxaqNFJssbEskwo9UVR5g.jpg",
            "2020-10-02",
            "Dragon Quest: The Adventure of Dai",
            "A long time ago, there was a valiant swordsman who came to be known simply as \"the hero.\" There was a demon who has caused people suffering.",
            "/i28TVlLozR09hqkGsSlu5wa1X2J.jpg",
            7.0,
            true
        ))

        tvShow.add(NowAiringTv(
            33238,
            "/rHuXgDmrv4vMKgQZ6pu2E2iLJnM.jpg",
            "2010-07-11",
            "Running Man",
            "A landmark representing Korea, how much do you know?",
            "/2Wmmu1MkqxJ48J7aySET9EKEjXz.jpg",
            8.2,
            true
        ))
        return tvShow
    }

    //REMOTE
    fun getTrendingRemote(): List<TrendingResponse> {
        val trending = ArrayList<TrendingResponse>()
        trending.add(
            TrendingResponse(
                464052,
                "/srYya1ZlI97Au4jUYAktDe3avyA.jpg",
                "Wonder Woman 1984",
                "Wonder Woman 1984",
                "/8UlWHLMpgZm9bx6QYh0NFoq67TZ.jpg",
                7.3,
                "2020-12-16",
                "2020-12-16",
                "Wonder Woman comes into conflict with the Soviet Union during the Cold War in the 1980s and finds a formidable foe by the name of the Cheetah.",
                "movie"
        ))

        trending.add(TrendingResponse(
            508442,
            "/kf456ZqeC45XTvo6W9pW5clYKfQ.jpg",
            "Soul",
            "Soul",
            "/zSuRX1VmT0PwuHVV7zl4n1mwdL0.jpg",
            8.5,
            "2020-12-16",
            "2019-11-12",
            "Joe Gardner is a middle school teacher with a love for jazz music. After a successful gig at the Half Note Club, he suddenly gets into an accident that separates his soul from his body and is transported to the You Seminar, a center in which souls develop and gain passions before being transported to a newborn child. Joe must enlist help from the other souls-in-training, like 22, a soul who has spent eons in the You Seminar, in order to get back to Earth.",
            "movie"
        ))

        trending.add(TrendingResponse(
            577922,
            "/wzJRB4MKi3yK138bJyuL9nx47y6.jpg",
            "Tenet",
            "Tenet",
            "/k68nPLbIST6NP96JmTxmZijEvCA.jpg",
            7.4,
            "2020-08-22",
            "2020-08-22",
            "Armed with only one word - Tenet - and fighting for the survival of the entire world, the Protagonist journeys through a twilight world of international espionage on a mission that will unfold in something beyond real time.",
            "movie"
        ))

        trending.add(TrendingResponse(
            614911,
            "/dueiWzWc81UAgnbDAyH4Gjqnh4n.jpg",
            "The Midnight Sky",
            "The Midnight Sky",
            "/51JxCk77ZCqLzbLkrDl9Qho6KUh.jpg",
            6.0,
            "2020-12-10",
            "2020-12-10",
            "A lone scientist in the Arctic races to contact a crew of astronauts returning home to a mysterious global catastrophe.",
            "movie"))

        trending.add(TrendingResponse(
            82856,
            "/o7qi2v4uWQ8bZ1tW3KI0Ztn2epk.jpg",
            "The Mandalorian",
            "The Mandalorian",
            "/sWgBv7LV2PRoQgkxwlibdGXKz1S.jpg",
            8.5,
            "2019-11-12",
            "2019-11-12",
            "After the fall of the Galactic Empire, lawlessness has spread throughout the galaxy. A lone gunfighter makes his way through the outer reaches, earning his keep as a bounty hunter.",
            "tv"
        ))
        return trending
    }

    fun getNowPlayingMovieRemote(): List<NowPlayingResponse> {

        val movie = ArrayList<NowPlayingResponse>()

        movie.add(NowPlayingResponse(
            464052,
            "/srYya1ZlI97Au4jUYAktDe3avyA.jpg",
            "Wonder Woman comes into conflict with the Soviet Union during the Cold War in the 1980s and finds a formidable foe by the name of the Cheetah.",
            "/8UlWHLMpgZm9bx6QYh0NFoq67TZ.jpg",
            "2020-12-16",
            "Wonder Woman 1984",
            7.3
        ))

        movie.add(NowPlayingResponse(
            508442,
            "/kf456ZqeC45XTvo6W9pW5clYKfQ.jpg",
            "Joe Gardner is a middle school teacher with a love for jazz music. After a successful gig at the Half Note Club, he suddenly gets into an accident that separates his soul from his body and is transported to the You Seminar, a center in which souls develop and gain passions before being transported to a newborn child. Joe must enlist help from the other souls-in-training, like 22, a soul who has spent eons in the You Seminar, in order to get back to Earth.",
            "/zSuRX1VmT0PwuHVV7zl4n1mwdL0.jpg",
            "2020-12-25",
            "Soul",
            8.5
        ))

        movie.add(NowPlayingResponse(
            529203,
            "/cjaOSjsjV6cl3uXdJqimktT880L.jpg",
            "Searching for a safer habitat, the prehistoric Crood family discovers an idyllic, walled-in paradise that meets all of its needs. Unfortunately, they must also learn to live with the Bettermans -- a family that's a couple of steps above the Croods on the evolutionary ladder. As tensions between the new neighbors start to rise, a new threat soon propels both clans on an epic adventure that forces them to embrace their differences, draw strength from one another, and survive together.",
            "/tK1zy5BsCt1J4OzoDicXmr0UTFH.jpg",
            "2020-11-25",
            "The Croods: A New Age",
            7.8
        ))

        movie.add(NowPlayingResponse(
            553604,
            "/jIJfDVGKM8GXgpJc2XH2x7e11wm.jpg",
            "A bank robber tries to turn himself in because he's falling in love and wants to live an honest life...but when he realizes the Feds are more corrupt than him, he must fight back to clear his name.",
            "/zeD4PabP6099gpE0STWJrJrCBCs.jpg",
            "2020-09-03",
            "Honest Thief",
            6.8
        ))

        movie.add(NowPlayingResponse(
            614911,
            "/dueiWzWc81UAgnbDAyH4Gjqnh4n.jpg",
            "A lone scientist in the Arctic races to contact a crew of astronauts returning home to a mysterious global catastrophe.",
            "/51JxCk77ZCqLzbLkrDl9Qho6KUh.jpg",
            "2020-12-10",
            "The Midnight Sky",
            6.0
        ))

        return movie
    }

    fun getAiringTvShowRemote(): List<NowAiringTvResponse> {
        val tvShow = ArrayList<NowAiringTvResponse>()

        tvShow.add(NowAiringTvResponse(
            89844,
            "/uSvncE20Mh3seJLIHLEQnt0OcBv.jpg",
            "2020-11-29",
            "30 Coins",
            "Father Vergara—an exorcist, boxer and ex-convict—lives in a remote village in Spain. Hoping to be lost and forgotten, Vergaras demons catch up to him.",
            "/b2i9aaV6ncULl9jYXEoPi7VFJg8.jpg",
            6.3
        ))

        tvShow.add(NowAiringTvResponse(
            456,
            "/hpU2cHC9tk90hswCFEpf5AtbqoL.jpg",
            "1989-12-16",
            "The Simpsons",
            "Set in Springfield, the average American town, the show focuses on the antics and everyday adventures of the Simpson family; Homer, Marge, Bart, Lisa and Maggie, as well as a virtual cast of thousands. Since the beginning, the series has been a pop culture icon, attracting hundreds of celebrities to guest star. The show has also made name for itself in its fearless satirical take on politics, media and American life in general.",
            "/2IWouZK4gkgHhJa3oyYuSWfSqbG.jpg",
            7.8
        ))

        tvShow.add(NowAiringTvResponse(
            111494,
            "/auBeh4qK54vC83ZkvVWxTXKPhLl.jpg",
            "2020-12-27",
            "Física o química: El reencuentro",
            "ajdfaj;df",
            "/k7YdhfvoCn0BB5eOLvvqG7lFWv1.jpg",
            4.0
        ))

        tvShow.add(NowAiringTvResponse(
            96884,
            "/rhXkhRXxaqNFJssbEskwo9UVR5g.jpg",
            "2020-10-02",
            "Dragon Quest: The Adventure of Dai",
            "A long time ago, there was a valiant swordsman who came to be known simply as \"the hero.\" There was a demon who has caused people suffering.",
            "/i28TVlLozR09hqkGsSlu5wa1X2J.jpg",
            7.0
        ))

        tvShow.add(NowAiringTvResponse(
            33238,
            "/rHuXgDmrv4vMKgQZ6pu2E2iLJnM.jpg",
            "2010-07-11",
            "Running Man",
            "A landmark representing Korea, how much do you know?",
            "/2Wmmu1MkqxJ48J7aySET9EKEjXz.jpg",
            8.2
        ))
        return tvShow
    }

}

