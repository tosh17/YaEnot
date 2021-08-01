package ru.yanot.practicum.utils

/**
 * Prism.js library highlighter.
 */
object PrismHighlighter {

    /**
     * Converts the input string to output html with highlighted syntax based on the parameters.
     * @param language is language will be used to highlight.
     * @param sourceCode is source code need to highlight.
     * @param showLineNumbers is responsible for specifying the number of lines of code, if it is off, the numbers will not be specified.
     */
    fun highlight(
        sourceCode: String,
        language: String,
        showLineNumbers: Boolean = false
    ): String {
        return """<!DOCTYPE html>
<html>
<head>
    <!-- https://developer.chrome.com/multidevice/webview/pixelperfect -->
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link href="www/main.css" rel="stylesheet"/>

    <!-- https://prismjs.com/ -->
    <link href="www/prism.css" rel="stylesheet"/>
    <script src="www/prism.js"></script>
</head>
<body>
<pre class="${if (showLineNumbers) "line-numbers" else ""}">
<code class="language-${language}">${sourceCode}</code>
</pre>
</body>
</html>
"""
    }
}