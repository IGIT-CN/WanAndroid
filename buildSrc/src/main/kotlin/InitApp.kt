import com.android.build.gradle.AppExtension
import org.gradle.api.JavaVersion
import org.gradle.api.Project
import org.gradle.api.plugins.ExtensionAware
import org.gradle.kotlin.dsl.get
import org.jetbrains.kotlin.gradle.dsl.KotlinJvmOptions
import org.jetbrains.kotlin.gradle.internal.AndroidExtensionsExtension
import plugin.test.ext.StudentExtension
import java.io.File

class InitApp(project: Project) {
    init {
        project.apply {
            plugin("com.android.application")
            plugin("org.jetbrains.kotlin.android")
            plugin("org.jetbrains.kotlin.android.extensions")
            plugin("org.jetbrains.kotlin.kapt")
            plugin("androidx.navigation.safeargs.kotlin")
            plugin("TestPlugin")
        }

        Config.initJenkinsProperties(project)

        project.extensions.getByType(AppExtension::class.java).apply {

            compileSdkVersion(Config.compileSdkVersion())

            signingConfigs {
                create("appSign") {
                    keyAlias = Config.keyAlias()
                    keyPassword = Config.keyPassword()
                    storeFile = File(Config.storeFile())
                    storePassword = Config.storePassword()
                }
            }

            defaultConfig {
                applicationId = Config.applicationId()
                minSdkVersion(Config.minSdkVersion())
                targetSdkVersion(Config.targetSdkVersion())
                versionCode = Config.versionCode()
                versionName = Config.versionName()
                signingConfig = signingConfigs.getByName("appSign")
                renderscriptTargetApi = 18
                renderscriptSupportModeEnabled = true
                ndk {
                    abiFilters("armeabi-v7a")
                }
                externalNativeBuild {
                    cmake {
                        cppFlags.addAll(listOf("-std=c++14"))
                        abiFilters.addAll(listOf("armeabi-v7a"))
                        arguments.addAll(listOf("-DANDROID_STL=c++_shared"))
                    }
                }
                resValue("string", "app_name_new", Config.appName())
                val fields = Config.getBuildConfigFields()
                fields.forEach {
                    defaultConfig.buildConfigField(it[0], it[1], it[2])
                }
                dimension
            }

            buildTypes {
                getByName("release") {
                    isShrinkResources = true
                    isMinifyEnabled = true
                    isZipAlignEnabled = true
                    proguardFiles(
                        getDefaultProguardFile("proguard-android-optimize.txt"),
                        "proguard-rules.pro"
                    )
                }
                getByName("debug") {

                }
            }

            flavorDimensions("CHANNEL", "CPU")
            productFlavors {
                create("GooglePlay") {
                    dimension = "CHANNEL"
                    applicationIdSuffix = ".google"
                    manifestPlaceholders.apply {
                        put("CHANNEL_NAME", name)
                        put("ic_launcher_new", "@mipmap/ic_launcher_google")
                        put("ic_launcher_round_new", "@mipmap/ic_launcher_google_round")
                    }
                }
                create("HuaWei") {
                    dimension = "CHANNEL"
                    applicationIdSuffix = ".huawei"
                    manifestPlaceholders.apply {
                        put("CHANNEL_NAME", name)
                        put("ic_launcher_new", "@mipmap/ic_launcher_huawei")
                        put("ic_launcher_round_new", "@mipmap/ic_launcher_huawei_round")
                    }
                }

                create("arm") {
                    dimension = "CPU"
                    ndk {
                        setAbiFilters(listOf("armeabi-v7a"))
                    }
                    externalNativeBuild {
                        cmake {
                            abiFilters.clear()
                            abiFilters.addAll(listOf("armeabi-v7a"))
                        }
                    }
                }

                create("x86") {
                    dimension = "CPU"
                    ndk {
                        setAbiFilters(listOf("x86"))
                    }
                    externalNativeBuild {
                        cmake {
                            abiFilters.clear()
                            abiFilters.addAll(listOf("x86"))
                        }
                    }
                }
            }

            compileOptions {
                sourceCompatibility = JavaVersion.VERSION_1_8
                targetCompatibility = JavaVersion.VERSION_1_8
            }

            val kotlinJvmOptions =
                (this as ExtensionAware).extensions.getByType(KotlinJvmOptions::class.java)
            kotlinJvmOptions.jvmTarget = JavaVersion.VERSION_1_8.toString()

            dataBinding {
                isEnabled = true
            }

            val androidExtensionsExtension =
                project.extensions.getByType(AndroidExtensionsExtension::class.java)
            androidExtensionsExtension.isExperimental = true

            sourceSets {
                sourceSets["main"].apply {
                    java.srcDir("src/main/kotlin")
                    jniLibs.srcDirs("src/main/cpp/libs")
                    res.srcDirs(
                        "src/main/res-opencv",
                        "src/main/res-jni",
                        "src/main/res-ffmpeg",
                        "src/main/res-websocket",
                        "src/main/res-camera",
                        "src/main/res-vxposed"
                    )
                }
            }

            externalNativeBuild {
                cmake {
                    setPath("src/main/cpp/CMakeLists.txt")
                    version = "3.10.2"
                }
            }

        }

        project.dependencies.apply {
            add("implementation", project.fileTree(mapOf("dir" to "libs", "include" to "*.jar")))
            add("api", project(mapOf("path" to ":shared")))
            add("kapt", Dep.kaptRoomCompiler)
            add("kapt", Dep.kaptDaggerCompiler)
            add("kapt", Dep.kaptDaggerProcessor)
            add("kapt", Dep.kaptGlide)
            add("kapt", Dep.kaptGlide)
            add("debugImplementation", "com.squareup.leakcanary:leakcanary-android:2.0-beta-3")
            add("debugImplementation", "com.didichuxing.doraemonkit:doraemonkit:2.0.1")
        }

        val studentExtension = project.extensions.getByType(StudentExtension::class.java)
        studentExtension.apply {
            nickname = "小猪"
            age = 160
        }

    }
}