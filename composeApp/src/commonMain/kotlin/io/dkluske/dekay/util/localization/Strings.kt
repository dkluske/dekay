package io.dkluske.dekay.util.localization

data class Strings(
    val start: String,
    val initStep1Text: String,
)

object Locales {
    const val EN = "en"
    const val DE = "de"
}

val EnStrings = Strings(
    start = "Start",
    initStep1Text = """
        In order to use dekay properly, we need some information from you in a few simple steps.
                        
        No worries wo won't sell any of them.
    """.trimIndent()
)

val DeStrings = Strings(
    start = "Start",
    initStep1Text = """
        Um dekay vernünftig nutzen zu können, brauchen wir Informationen von dir in wenigen einfachen Schritten.
        
        Wir werden Ihre Informationen niemals an Dritte weitergeben, versprochen.
    """.trimIndent()
)