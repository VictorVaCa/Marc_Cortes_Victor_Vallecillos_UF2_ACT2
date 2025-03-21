package com.example.marc_cortes_victor_vallecillos_uf2_act2

import android.media.SoundPool
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageButton

class MainActivity : AppCompatActivity() {

    private lateinit var soundPool: SoundPool
    private val noteSounds = mutableMapOf<String, Int>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Inicialitzar SoundPool
        soundPool = SoundPool.Builder().setMaxStreams(10).build()

        // Carregar les notes al SoundPool
        loadNotes()

        // Assignar un listener a cada botó
        val buttons = arrayOf(
            R.id.c2, R.id.cs2, R.id.d2, R.id.ds2, R.id.e2, R.id.f2, R.id.fs2,
            R.id.g2, R.id.gs2, R.id.a2, R.id.as2, R.id.b2, R.id.c3, R.id.cs3,
            R.id.d3, R.id.ds3, R.id.e3, R.id.f3, R.id.fs3, R.id.g3, R.id.gs3,
            R.id.a3, R.id.as3, R.id.b3, R.id.c4
        )

        for (buttonId in buttons) {
            val button = findViewById<ImageButton>(buttonId)
            button.setOnClickListener {
                val noteName = resources.getResourceEntryName(buttonId)
                playNote(noteName)
            }
        }
    }

    private fun loadNotes() {
        noteSounds["c2"] = soundPool.load(this, R.raw.c2, 1)
        noteSounds["cs2"] = soundPool.load(this, R.raw.cs2, 1)
        noteSounds["d2"] = soundPool.load(this, R.raw.d2, 1)
        noteSounds["ds2"] = soundPool.load(this, R.raw.ds2, 1)
        noteSounds["e2"] = soundPool.load(this, R.raw.e2, 1)
        noteSounds["f2"] = soundPool.load(this, R.raw.f2, 1)
        noteSounds["fs2"] = soundPool.load(this, R.raw.fs2, 1)
        noteSounds["g2"] = soundPool.load(this, R.raw.g2, 1)
        noteSounds["gs2"] = soundPool.load(this, R.raw.gs2, 1)
        noteSounds["a2"] = soundPool.load(this, R.raw.a2, 1)
        noteSounds["as2"] = soundPool.load(this, R.raw.as2, 1)
        noteSounds["b2"] = soundPool.load(this, R.raw.b2, 1)
        noteSounds["c3"] = soundPool.load(this, R.raw.c3, 1)
        noteSounds["cs3"] = soundPool.load(this, R.raw.cs3, 1)
        noteSounds["d3"] = soundPool.load(this, R.raw.d3, 1)
        noteSounds["ds3"] = soundPool.load(this, R.raw.ds3, 1)
        noteSounds["e3"] = soundPool.load(this, R.raw.e3, 1)
        noteSounds["f3"] = soundPool.load(this, R.raw.f3, 1)
        noteSounds["fs3"] = soundPool.load(this, R.raw.fs3, 1)
        noteSounds["g3"] = soundPool.load(this, R.raw.g3, 1)
        noteSounds["gs3"] = soundPool.load(this, R.raw.gs3, 1)
        noteSounds["a3"] = soundPool.load(this, R.raw.a3, 1)
        noteSounds["as3"] = soundPool.load(this, R.raw.as3, 1)
        noteSounds["b3"] = soundPool.load(this, R.raw.b3, 1)
        noteSounds["c4"] = soundPool.load(this, R.raw.c4, 1)
    }

    private fun playNote(noteName: String) {
        val soundId = noteSounds[noteName]
        if (soundId != null) {
            soundPool.play(soundId, 1f, 1f, 0, 0, 1f)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        soundPool.release() // Alliberar recursos al final
    }
}