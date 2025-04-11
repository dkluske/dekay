package io.dkluske.dekay.util.localization

data class Strings(
    val start: String,
    val initStepStartText: String,
    val ok: String,
    val error: String,
    val initStep1Title: String,
    val initStep2Title: String,
    val initStep3Title: String,
    val initStep4Title: String,
    val initStep5Title: String,
    val initStep6Title: String,
    val initStepFinishText: String,
    val pleaseEnterFirstAndLastName: String,
    val firstName: String,
    val lastName: String,
    val nickName: String,
    val dateOfBirth: String,
    val height: String,
    val gender: String,
    val male: String,
    val female: String,
    val notDefined: String,
    val steps: String,
    val finished: String,
    val startYourJourney: String,
    val cancel: String,
    val add: String,
    val title: String,
    val description: String,
    val habit: String,
    val showMore: String,
    val settings: String,
)

object Locales {
    const val EN = "en"
    const val DE = "de"
}

val EnStrings = Strings(
    start = "Start",
    initStepStartText = """
        In order to use dekay properly, we need some information from you in a few simple steps.
                        
        No worries wo won't sell any of them.
    """.trimIndent(),
    ok = "Ok",
    error = "Error",
    initStep1Title = "Personal Information",
    pleaseEnterFirstAndLastName = "Please enter your first and last name.",
    firstName = "Firstname",
    lastName = "Lastname",
    nickName = "Username",
    initStep2Title = "Date of Birth",
    dateOfBirth = "Date of Birth",
    height = "Height",
    gender = "Gender",
    male = "Male",
    female = "Female",
    notDefined = "Not defined",
    initStep3Title = "Height",
    initStep4Title = "Gender",
    initStep5Title = "Daily Step Goal",
    steps = "Steps",
    finished = "Finished",
    initStepFinishText = "Congratulations! You have finished the onboarding process. You are now ready to begin your journey with dekay.",
    startYourJourney = "Start your journey",
    cancel = "Cancel",
    add = "Add",
    title = "Title",
    description = "Description",
    habit = "Habit",
    showMore = "Show more",
    settings = "Settings",
    initStep6Title = "Health Data"
)

val DeStrings = Strings(
    start = "Start",
    initStepStartText = """
        Um dekay vernünftig nutzen zu können, brauchen wir Informationen von dir in wenigen einfachen Schritten.
        
        Wir werden Ihre Informationen niemals an Dritte weitergeben, versprochen.
    """.trimIndent(),
    ok = "Ok",
    error = "Fehler",
    initStep1Title = "Du",
    pleaseEnterFirstAndLastName = "Bitte gib Deinen Vor- und Nachnamen ein.",
    firstName = "Vorname",
    lastName = "Nachname",
    nickName = "Username",
    initStep2Title = "Geburtsdatum",
    dateOfBirth = "Geburtsdatum",
    height = "Größe",
    gender = "Geschlecht",
    male = "Männlich",
    female = "Weiblich",
    notDefined = "Nicht angegeben",
    initStep3Title = "Größe",
    initStep4Title = "Geschlecht",
    initStep5Title = "Tägliches Schrittziel",
    steps = "Schritte",
    finished = "Geschafft",
    initStepFinishText = "Glückwunsch! Wir haben nun alle notwendigen Informationen von Dir. Du bist jetzt soweit in den Erlebnis mit dekay zu starten!",
    startYourJourney = "Starte Dein Erlebnis",
    cancel = "Abbruch",
    add = "Neu",
    title = "Titel",
    description = "Beschreibung",
    showMore = "Mehr",
    habit = "Habit",
    settings = "Einstellungen",
    initStep6Title = "Gesundheitsdaten"
)