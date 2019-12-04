#include <jni.h>
#include <string>
#include <android/bitmap.h>
#include <opencv2/opencv.hpp>
#include "utils/log.h"

using namespace std;
using namespace cv;

jobject gray(JNIEnv *env, jclass clazz, jobject &bitmap);

static const char *const className = "com/zhuzichu/android/wan/manager/NativeManager";

static jclass myClass;

static const JNINativeMethod gMethods[] = {
        {"gray", "(Landroid/graphics/Bitmap;)Landroid/graphics/Bitmap;", (jobject *) gray}
};


JNIEXPORT jint JNI_OnLoad(JavaVM *vm, void *reserved) {
    LOGI("JNI_OnLoad 开始了");
    JNIEnv *env = NULL;
    if (vm->GetEnv((void **) &env, JNI_VERSION_1_6) != JNI_OK) { //从JavaVM获取JNIEnv，一般使用1.4的版本
        return -1;
    }
    // 获取映射的java类
    myClass = env->FindClass(className);
    if (myClass == NULL) {
        LOGE("不能找到类:%s\n", className);
        return -1;
    }
    // 通过RegisterNatives方法动态注册
    if (env->RegisterNatives(myClass, gMethods, sizeof(gMethods) / sizeof(gMethods[0])) < 0) {
        printf("注册方法失败!\n");
        return -1;
    }
    LOGI("JNI_OnLoad 结束了");
    return JNI_VERSION_1_6;
}

jobject gray(JNIEnv *env, jclass clazz, jobject &bitmap) {
    LOGI("gray 开始了");
    int result;
    AndroidBitmapInfo info;
    void *pixels = 0;
    result = AndroidBitmap_getInfo(env, bitmap, &info);
    if (result < 0) {
        return NULL;
    }
    result = AndroidBitmap_lockPixels(env, bitmap, &pixels);
    if (result < 0) {
        return NULL;
    }

    Mat src(info.height, info.width, CV_8UC4, pixels);
    Mat dst;
    cvtColor(src, dst, COLOR_BGR2GRAY);
    LOGI("gray 结束了");
    AndroidBitmap_unlockPixels(env, bitmap);
    return NULL;
}