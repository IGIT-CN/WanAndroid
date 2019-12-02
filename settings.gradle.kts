include(":app", ":shared")
rootProject.name = "WanAndroid"

//val fileName = "build.groovy.gradle"
val fileName = "build.kotlin.gradle.kts"
//val fileName = "build.nodsl.gradle.kts"

rootProject.children.forEach {
    if (it.name == "app") {
        it.buildFileName = fileName
    }
}