package com.example.codingchallenge

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.speech.RecognitionListener
import android.speech.SpeechRecognizer
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.example.codingchallenge.databinding.ActivityMainBinding
import com.example.codingchallenge.utils.SpeechRecognitionUtils

class MainActivity : AppCompatActivity() {
    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityMainBinding
    private var speechRecognizer: SpeechRecognizer? = null
    private var voiceRequestListener: SpeechRecognitionUtils.VoiceRequestListener? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)

        val navController = findNavController(R.id.nav_host_fragment_content_main)
        appBarConfiguration = AppBarConfiguration(navController.graph)
        setupActionBarWithNavController(navController, appBarConfiguration)
        binding.fab.setOnClickListener { _ ->
            //TODO - more elegant check, explaining dialog perhaps.
            if (!hasPermissions()) {
                requestPermissions()
            } else {
                resetSpeechRecognizer()
                startListening()
            }
        }

        navController.addOnDestinationChangedListener( listener = { _, destination, _ ->
            when (destination.id) {
                R.id.ArticleFragment -> {
                    binding.fab.visibility = View.GONE
                }
                R.id.NewsFragment -> {
                    binding.fab.visibility = View.VISIBLE
                }
            }
        })
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment_content_main)
        return navController.navigateUp(appBarConfiguration)
                || super.onSupportNavigateUp()
    }

    private fun hasPermissions(): Boolean {
        val permissionCheck =
            ContextCompat.checkSelfPermission(applicationContext, Manifest.permission.RECORD_AUDIO)
        return permissionCheck == PackageManager.PERMISSION_GRANTED
    }

    private fun requestPermissions() {
        if (!hasPermissions()) {
            ActivityCompat.requestPermissions(
                this@MainActivity,
                arrayOf(Manifest.permission.RECORD_AUDIO),
                PERMISSIONS_REQUEST_RECORD_AUDIO
            )
        }
    }

    private fun startListening() {
        speechRecognizer!!.startListening(SpeechRecognitionUtils.getRecognitionIntent())
        binding.progressBar.visibility = View.VISIBLE
    }

    private fun resetSpeechRecognizer() {
        if (speechRecognizer != null) speechRecognizer!!.destroy()
        speechRecognizer = SpeechRecognizer.createSpeechRecognizer(this)
        if (SpeechRecognizer.isRecognitionAvailable(this))
            speechRecognizer!!.setRecognitionListener(mRecognitionListener)
    }

    private val mRecognitionListener = object : RecognitionListener {
        override fun onBeginningOfSpeech() {
            //TODO - log or print
            binding.progressBar.isIndeterminate = false
            binding.progressBar.max = 10
        }

        override fun onBufferReceived(buffer: ByteArray) {
            //TODO - log or print
        }

        override fun onEndOfSpeech() {
            speechRecognizer!!.stopListening()
        }

        override fun onResults(results: Bundle) {
            val matches: ArrayList<String>? = results
                .getStringArrayList(SpeechRecognizer.RESULTS_RECOGNITION)
            var text = ""
            for (result in matches!!) text += result.trimIndent()
            //TODO - Handle result
            binding.progressBar.visibility = View.GONE
            if (text.isNotEmpty()) {
                println("Voice recognized: $text")
                voiceRequestListener?.onNewVoiceCommand(text.lowercase())
            } else {
                Toast.makeText(this@MainActivity, "No voice command detected.", Toast.LENGTH_SHORT).show()
            }
        }

        override fun onError(errorCode: Int) {
            val errorMessage = SpeechRecognitionUtils.getErrorText(errorCode)
            println("FAILED $errorMessage")
            binding.progressBar.visibility = View.GONE
            Toast.makeText(this@MainActivity, errorMessage, Toast.LENGTH_SHORT).show()
        }

        override fun onEvent(arg0: Int, arg1: Bundle) {
            //TODO - log or print
        }

        override fun onPartialResults(arg0: Bundle) {
            //TODO - log or print
        }

        override fun onReadyForSpeech(arg0: Bundle) {
            //TODO - log or print
        }

        override fun onRmsChanged(rmsdB: Float) {
            //TODO - display visual cue of recognition?
            binding.progressBar.progress = rmsdB.toInt()
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<String>, grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == PERMISSIONS_REQUEST_RECORD_AUDIO) {
            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                resetSpeechRecognizer()
                startListening()
            } else {
                Toast.makeText(this, "Permission was denied.", Toast.LENGTH_SHORT).show()
            }
        }
    }

    fun setVoiceRequestListener(voiceRequestListener: SpeechRecognitionUtils.VoiceRequestListener?) {
        this.voiceRequestListener = voiceRequestListener
    }

    companion object {
        private const val PERMISSIONS_REQUEST_RECORD_AUDIO = 12001
    }
}