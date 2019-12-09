#include <jni.h>
#include "common.h"
extern "C"{
#include "libavcodec/avcodec.h"
}

//声明JNI函数
JNIEXPORT jstring getConfiguration(JNIEnv *env, jobject object);

static const char *const class_FFmpegManager = "com/zhuzichu/android/wan/manager/FFmpegManager";

static const JNINativeMethod gMethods[] = {
        {"getConfiguration", "()Ljava/lang/String;", (jstring *) getConfiguration}
};


JNIEXPORT jint JNI_OnLoad(JavaVM *vm, void *reserved) {
    LOGI("JNI_OnLoad 开始了");
    JNIEnv *env = NULL;
    if (vm->GetEnv((void **) &env, JNI_VERSION_1_6) != JNI_OK) { //从JavaVM获取JNIEnv，一般使用1.4的版本
        return -1;
    }
    // 获取映射的java类
    jclass myClass = env->FindClass(class_FFmpegManager);
    if (myClass == NULL) {
        LOGE("不能找到类:%s\n", class_FFmpegManager);
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

JNIEXPORT jstring getConfiguration(JNIEnv *env, jobject object) {
    return env->NewStringUTF(avcodec_configuration());
}