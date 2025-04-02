package io.dkluske.dekay.util.localization

data class Strings(
    val start: String,
    val initStepStartText: String,
    val ok: String,
    val error: String,
    val initStep1Text: String,
    val initStep1Title: String,
    val initStep2Text: String,
    val initStep2Title: String,
    val initStep3Text: String,
    val initStep3Title: String,
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
    val habit: String
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
    initStep1Text = "Please enter your personal information to get started.",
    initStep1Title = "Step 1: Personal Information",
    pleaseEnterFirstAndLastName = "Please enter your first and last name.",
    firstName = "Firstname",
    lastName = "Lastname",
    nickName = "Username",
    initStep2Text = "Please enter your health information to get started.",
    initStep2Title = "Step 2: Health Information",
    dateOfBirth = "Date of Birth",
    height = "Height",
    gender = "Gender",
    male = "Male",
    female = "Female",
    notDefined = "Not defined",
    initStep3Text = "Please enter your daily step goal to get started.",
    initStep3Title = "Step 3: Daily Step Goal",
    steps = "Steps",
    finished = "Finished",
    initStepFinishText = "Congratulations! You have finished the onboarding process. You are now ready to begin your journey with dekay.",
    startYourJourney = "Start your journey",
    cancel = "Cancel",
    add = "Add",
    title = "Title",
    description = "Description",
    habit = "Habit"
)

val DeStrings = Strings(
    start = "Start",
    initStepStartText = """
        Um dekay vernünftig nutzen zu können, brauchen wir Informationen von dir in wenigen einfachen Schritten.
        
        Wir werden Ihre Informationen niemals an Dritte weitergeben, versprochen.
    """.trimIndent(),
    ok = "Ok",
    error = "Fehler",
    initStep1Text = "Bitte gib Deine persönlichen Informationen ein, um starten zu können.",
    initStep1Title = "Schritt 1: Du",
    pleaseEnterFirstAndLastName = "Bitte gib Deinen Vor- und Nachnamen ein.",
    firstName = "Vorname",
    lastName = "Nachname",
    nickName = "Username",
    initStep2Text = "Bitte gib deine Körperdaten an.",
    initStep2Title = "Schritt 2: Körperdaten",
    dateOfBirth = "Geburtsdatum",
    height = "Größe",
    gender = "Geschlecht",
    male = "Männlich",
    female = "Weiblich",
    notDefined = "Nicht angegeben",
    initStep3Text = "Bitte gib Dein tägliches Schrittziel an.",
    initStep3Title = "Schritt 3: Schrittziel",
    steps = "Schritte",
    finished = "Geschafft",
    initStepFinishText = "Glückwunsch! Wir haben nun alle notwendigen Informationen von Dir. Du bist jetzt soweit in den Erlebnis mit dekay zu starten!",
    startYourJourney = "Starte Dein Erlebnis",
    cancel = "Abbruch",
    add = "Neu",
    title = "Titel",
    description = "Beschreibung",
    habit = "Habit"
)