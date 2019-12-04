#include <jni.h>
#include <string>
#include <log.h>

using namespace std;

jstring invokeDynamicMethod(JNIEnv *env, jobject object);

void callbackText(JNIEnv *env, jobject object, jstring text);

extern "C"
JNIEXPORT jstring JNICALL
Java_com_zhuzichu_android_wan_manager_JniDemoManager_invokeStaticMethod(
        JNIEnv *env, jobject object
) {
    jstring text = env->NewStringUTF(string("invokeStaticMethod 我是静态注册的方法").c_str());
    callbackText(env, object, text);
    return text;
}

static const char *const class_JniDemoManager = "com/zhuzichu/android/wan/manager/JniDemoManager";

static jclass myClass;

static const JNINativeMethod gMethods[] = {
        {"invokeDynamicMethod", "()Ljava/lang/String;", (jstring *) invokeDynamicMethod}
};

JNIEXPORT jint JNI_OnLoad(JavaVM *vm, void *reserved) {
    LOGI("JNI_OnLoad 开始了");
    JNIEnv *env = NULL;
    if (vm->GetEnv((void **) &env, JNI_VERSION_1_6) != JNI_OK) { //从JavaVM获取JNIEnv，一般使用1.4的版本
        return -1;
    }
    // 获取映射的java类
    myClass = env->FindClass(class_JniDemoManager);
    if (myClass == NULL) {
        LOGE("不能找到类:%s\n", class_JniDemoManager);
        return -1;
    }
    // 通过RegisterNatives方法动态注册
    if (env->RegisterNatives(myClass, gMethods, sizeof(gMethods) / sizeof(gMethods[0])) < 0) {
        LOGE("注册方法失败!\n");
        return -1;
    }
    LOGI("JNI_OnLoad 结束了");
    return JNI_VERSION_1_6;
}

JNIEXPORT jstring invokeDynamicMethod(JNIEnv *env, jobject object) {
    jstring text = env->NewStringUTF(string("invokeDynamicMethod 我是动态注册的方法").c_str());
    callbackText(env, object, text);
    return text;
}


void callbackText(JNIEnv *env, jobject object, jstring text) {
    jmethodID methodId = env->GetMethodID(
            myClass,
            "onInvokeStaticOrDynamicCallback",
            "(Ljava/lang/String;)V"
    );
    env->CallObjectMethod(object, methodId, text);
}
