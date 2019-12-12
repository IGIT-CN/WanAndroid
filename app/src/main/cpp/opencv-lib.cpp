#include <jni.h>
#include <string>
#include <android/bitmap.h>
#include <opencv2/opencv.hpp>
#include "log.h"
#include "common.h"

using namespace std;
using namespace cv;

JNIEXPORT jobject jgray(JNIEnv *env, jobject object, jobject bitmap);

JNIEXPORT jobject
jerode(JNIEnv *env, jobject object, jobject bitmap, int morph, int width, int height);

JNIEXPORT jobject
jdilate(JNIEnv *env, jobject object, jobject bitmap, int morph, int width, int height);

JNIEXPORT jobject jblur(JNIEnv *env, jobject object, jobject bitmap, jint width, jint height);

JNIEXPORT jobject
jcanny(JNIEnv *env, jobject object, jobject bitmap, double threshold1, double threshold2,
       int apertureSize,
       bool isL2);

static const char *const class_NativeManager = "com/zhuzichu/android/wan/manager/OpencvManager";

static jclass myClass;

static const JNINativeMethod gMethods[] = {
        {"gray",   "(Landroid/graphics/Bitmap;)Landroid/graphics/Bitmap;",     (jobject *) jgray},
        {"erode",  "(Landroid/graphics/Bitmap;III)Landroid/graphics/Bitmap;",  (jobject *) jerode},
        {"blur",   "(Landroid/graphics/Bitmap;II)Landroid/graphics/Bitmap;",   (jobject *) jblur},
        {"dilate", "(Landroid/graphics/Bitmap;III)Landroid/graphics/Bitmap;",  (jobject *) jdilate},
        {"canny",  "(Landroid/graphics/Bitmap;DDIZ)Landroid/graphics/Bitmap;", (jobject *) jcanny}
};


JNIEXPORT jint JNI_OnLoad(JavaVM *vm, void *reserved) {
    LOGI("JNI_OnLoad 开始了");
    JNIEnv *env = NULL;
    if (vm->GetEnv((void **) &env, JNI_VERSION_1_6) != JNI_OK) { //从JavaVM获取JNIEnv，一般使用1.4的版本
        return -1;
    }
    // 获取映射的java类
    myClass = env->FindClass(class_NativeManager);
    if (myClass == NULL) {
        LOGE("不能找到类:%s\n", class_NativeManager);
        return -1;
    }
    // 通过RegisterNatives方法动态注册
    if (env->RegisterNatives(myClass, gMethods, sizeof(gMethods) / sizeof(gMethods[0])) < 0) {
        LOGE("注册方法失败!\n");
        return -1;
    }
    env->DeleteLocalRef(myClass);
    LOGI("JNI_OnLoad 结束了");
    return JNI_VERSION_1_6;
}

JNIEXPORT jobject jgray(JNIEnv *env, jobject object, jobject bitmap) {
    LOGI("gray 开始了");
    Mat src;
    BitmapToMat(env, bitmap, src);
    Mat dst;
    cvtColor(src, dst, COLOR_RGBA2GRAY);
    MatToBitmap(env, dst, bitmap);
    src.release();
    dst.release();
    LOGI("gray 结束了");
    return bitmap;
}

JNIEXPORT jobject
jerode(JNIEnv *env, jobject object, jobject bitmap, jint morph, jint width, jint height) {
    LOGI("erode 开始了");
    Mat src;
    BitmapToMat(env, bitmap, src);
    Mat dst;
    Mat element = getStructuringElement(morph, Size(width, height));
    erode(src, dst, element);
    MatToBitmap(env, dst, bitmap);
    element.release();
    src.release();
    dst.release();
    LOGI("erode 结束了");
    return bitmap;
}

JNIEXPORT jobject
jdilate(JNIEnv *env, jobject object, jobject bitmap, jint morph, jint width, jint height) {
    LOGI("erode 开始了");
    Mat src;
    BitmapToMat(env, bitmap, src);
    Mat dst;
    Mat element = getStructuringElement(morph, Size(width, height));
    dilate(src, dst, element);
    MatToBitmap(env, dst, bitmap);
    element.release();
    src.release();
    dst.release();
    LOGI("erode 结束了");
    return bitmap;
}

JNIEXPORT jobject jblur(JNIEnv *env, jobject object, jobject bitmap, jint width, jint height) {
    LOGI("blur 开始了");
    Mat src;
    BitmapToMat(env, bitmap, src);
    Mat dst;
    blur(src, dst, Size(width, height));
    MatToBitmap(env, dst, bitmap);
    src.release();
    dst.release();
    LOGI("blur 结束了");
    return bitmap;
}

JNIEXPORT jobject
jcanny(JNIEnv *env, jobject object, jobject bitmap, double threshold1, double threshold2,
       int apertureSize,
       bool isL2) {
    LOGI("canny 开始了");
    Mat src;
    BitmapToMat(env, bitmap, src);
    Mat gray;
    //转换灰度图
    cvtColor(src, gray, COLOR_BGR2GRAY);
    Mat dst;
    //先用使用 3x3内核来降噪 让图片变的光滑
    blur(gray, dst, Size(3, 3));
    Canny(dst, dst, threshold1, threshold2, apertureSize);
    MatToBitmap(env, dst, bitmap);
    src.release();
    gray.release();
    dst.release();
    LOGI("canny 结束了");
    return bitmap;
}