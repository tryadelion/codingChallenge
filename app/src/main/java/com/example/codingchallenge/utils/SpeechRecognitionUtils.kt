package com.example.codingchallenge.utils

import android.content.Intent
import android.speech.RecognizerIntent
import android.speech.SpeechRecognizer

object SpeechRecognitionUtils {
    fun getErrorText(errorCode: Int): String {
        val message: String = when (errorCode) {
            SpeechRecognizer.ERROR_AUDIO -> "Audio recording error"
            SpeechRecognizer.ERROR_CLIENT -> "Client side error"
            SpeechRecognizer.ERROR_INSUFFICIENT_PERMISSIONS -> "Insufficient permissions"
            SpeechRecognizer.ERROR_NETWORK -> "Network error"
            SpeechRecognizer.ERROR_NETWORK_TIMEOUT -> "Network timeout"
            SpeechRecognizer.ERROR_NO_MATCH -> "No match"
            SpeechRecognizer.ERROR_RECOGNIZER_BUSY -> "RecognitionService busy"
            SpeechRecognizer.ERROR_SERVER -> "error from server"
            SpeechRecognizer.ERROR_SPEECH_TIMEOUT -> "No speech input"
            SpeechRecognizer.ERROR_LANGUAGE_NOT_SUPPORTED -> "Language Not supported"
            SpeechRecognizer.ERROR_LANGUAGE_UNAVAILABLE -> "Language Unavailable"
            else -> "Unknown Error. Please try again."
        }
        return message
    }

    fun getRecognitionIntent(): Intent {
        val recognizerIntent = Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH)
        recognizerIntent.putExtra(
            RecognizerIntent.EXTRA_LANGUAGE_PREFERENCE,
            "en"
        )
        recognizerIntent.putExtra(
            RecognizerIntent.EXTRA_LANGUAGE,
            "en"
        )
        recognizerIntent.putExtra(
            RecognizerIntent.EXTRA_LANGUAGE_MODEL,
            RecognizerIntent.LANGUAGE_MODEL_FREE_FORM
        )
        recognizerIntent.putExtra(RecognizerIntent.EXTRA_MAX_RESULTS, 1)
        return recognizerIntent
    }

    interface VoiceRequestListener {
        fun onNewVoiceCommand(command: String)
    }
}