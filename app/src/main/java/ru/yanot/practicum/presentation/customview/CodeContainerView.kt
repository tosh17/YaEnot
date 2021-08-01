package ru.yanot.practicum.presentation.customview

import android.annotation.SuppressLint
import android.content.Context
import android.util.AttributeSet
import android.webkit.WebChromeClient
import android.webkit.WebView
import android.webkit.WebViewClient
import ru.yanot.practicum.utils.PrismHighlighter

class CodeContainerView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0

) : WebView(context, attrs, defStyleAttr) {

    @SuppressLint("SetJavaScriptEnabled")
    fun bindHighlighter(
        sourceCode: String,
        language: String,
        showLineNumbers: Boolean = false
    ) {
        settings.javaScriptEnabled = true
        webChromeClient = WebChromeClient()
        webViewClient = WebViewClient()

        loadDataWithBaseURL(
            ANDROID_ASSETS_PATH,
            PrismHighlighter.highlight(sourceCode, language, showLineNumbers),
            "text/html",
            "utf-8",
            ""
        )
    }

    companion object {
        private const val ANDROID_ASSETS_PATH = "file:///android_asset/"
    }
}