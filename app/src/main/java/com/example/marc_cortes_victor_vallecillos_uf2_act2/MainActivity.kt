package com.example.marc_cortes_victor_vallecillos_uf2_act2

import android.media.SoundPool
import android.os.Bundle
import android.view.MotionEvent
import android.view.View
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private lateinit var soundPool: SoundPool
    private val noteSounds = mutableMapOf<String, Int>()
    private val activeTouches = mutableMapOf<Int, View>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        soundPool = SoundPool.Builder().setMaxStreams(26).build()
        loadNotes()

        val buttonIds = arrayOf(
            R.id.c2, R.id.cs2, R.id.d2, R.id.ds2, R.id.e2, R.id.f2, R.id.fs2,
            R.id.g2, R.id.gs2, R.id.a2, R.id.as2, R.id.b2, R.id.c3, R.id.cs3,
            R.id.d3, R.id.ds3, R.id.e3, R.id.f3, R.id.fs3, R.id.g3, R.id.gs3,
            R.id.a3, R.id.as3, R.id.b3, R.id.c4, R.id.cs4
        )

        buttonIds.forEach { id ->
            findViewById<ImageButton>(id).apply {
                setOnTouchListener { v, event ->
                    handleTouchEvent(v, event)
                }
            }
        }
    }

    private fun handleTouchEvent(view: View, event: MotionEvent): Boolean {
        val pointerId = event.getPointerId(0)

        when (event.actionMasked) {
            MotionEvent.ACTION_DOWN -> {
                handleKeyPress(view, pointerId)
            }
            MotionEvent.ACTION_MOVE -> {
                val x = event.rawX
                val y = event.rawY
                val currentKey = findKeyAtPosition(x, y)

                if (currentKey != null && currentKey != activeTouches[pointerId]) {
                    // El dedo se ha movido a una nueva tecla
                    releaseAllKeys()
                    handleKeyPress(currentKey, pointerId)
                } else if (currentKey == null) {
                    // El dedo se ha movido fuera de cualquier tecla
                    releaseAllKeys()
                }
            }
            MotionEvent.ACTION_UP, MotionEvent.ACTION_CANCEL -> {
                releaseAllKeys()
            }
        }
        return true
    }

    private fun handleKeyPress(view: View, pointerId: Int) {
        val noteName = resources.getResourceEntryName(view.id)
        view.isPressed = true
        playNote(noteName)
        activeTouches[pointerId] = view
    }

    private fun releaseAllKeys() {
        activeTouches.values.forEach { it.isPressed = false }
        activeTouches.clear()
    }

    private fun findKeyAtPosition(x: Float, y: Float): View? {
        return listOf(
            R.id.c2, R.id.cs2, R.id.d2, R.id.ds2, R.id.e2, R.id.f2, R.id.fs2,
            R.id.g2, R.id.gs2, R.id.a2, R.id.as2, R.id.b2, R.id.c3, R.id.cs3,
            R.id.d3, R.id.ds3, R.id.e3, R.id.f3, R.id.fs3, R.id.g3, R.id.gs3,
            R.id.a3, R.id.as3, R.id.b3, R.id.c4, R.id.cs4
        ).map { findViewById<View>(it) }
            .firstOrNull { isTouchInsideView(x, y, it) }
    }

    private fun isTouchInsideView(x: Float, y: Float, view: View): Boolean {
        val location = IntArray(2)
        view.getLocationOnScreen(location)
        return x >= location[0] && x < location[0] + view.width &&
                y >= location[1] && y < location[1] + view.height
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
        noteSounds["cs4"] = soundPool.load(this, R.raw.cs4, 1)
    }

    private fun playNote(noteName: String) {
        val soundId = noteSounds[noteName]
        soundId?.let {
            soundPool.play(it, 1f, 1f, 0, 0, 1f)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        soundPool.release() // Liberar recursos al final
    }
}
