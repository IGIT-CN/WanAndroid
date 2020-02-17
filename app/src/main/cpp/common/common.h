#include <jni.h>
#include "log.h"
#include <android/bitmap.h>
#include <opencv2/opencv.hpp>

using namespace cv;

#ifndef WANANDROID_COMMON_H
#define WANANDROID_COMMON_H

static const char *IOException = "java/io/IOException";
static const char *RuntimeException = "java/io/RuntimeException";

void throwException(JNIEnv *env, const char *name, const char *msg);

void BitmapToMat2(JNIEnv *env, jobject &bitmap, Mat &mat, jboolean needUnPremultiplyAlpha);

void BitmapToMat(JNIEnv *env, jobject &bitmap, Mat &mat);

void MatToBitmap2(JNIEnv *env, Mat &mat, jobject &bitmap, jboolean needPremultiplyAlpha);

void MatToBitmap(JNIEnv *env, Mat &mat, jobject &bitmap);

#endif //WANANDROID_COMMON_H
