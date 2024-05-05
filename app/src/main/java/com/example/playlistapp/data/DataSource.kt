package com.example.playlistapp.data

import com.example.playlistapp.R
import com.example.playlistapp.models.Song

fun createPlaylist(): List<Song> {

    val pictures = listOf(
        R.drawable.cantor_metal,
        R.drawable.cantor_pop,
        R.drawable.cantor_rock,
        R.drawable.cantor_sertanejo,
        R.drawable.cantor_samba,
        R.drawable.cantora_kpop,
    )

    val musics = listOf(

        "Stairway to Heaven" to "Led Zeppelin",

        "Smells Like Teen Spirit" to "Nirvana",

        "Shape of You" to "Ed Sheeran",

        "Rolling in the Deep" to "Adele",

        "Ai Se Eu Te Pego" to "Michel Teló",

        "Evidências" to "Chitãozinho & Xororó",

        "Mas Que Nada" to "Sérgio Mendes ft.Black Eyed Peas",

        "Aquarela do Brasil" to "Ary Barroso",

        "Take Me Out" to "Franz Ferdinand",

        "Do I Wanna Know?" to "Arctic Monkeys",

        "Bad Romance" to "Lady Gaga",

        "Do Fundo da Grota" to "Baitaca",

        "Livin' On a Prayer" to "Bon Jovi",

        "Yellow" to "Coldplay",

        "Evidências" to "Chitãozinho e Xororó",

        "Na Linha do Tempo" to "Victor e Leo",

        "Cheia de Manias" to "Raça Negra"
    )

    val songs = musics.mapIndexed { index, (title, artist) ->
        Song(picture = pictures[index % pictures.size], title = title, artist = artist)
    }

    return songs

}