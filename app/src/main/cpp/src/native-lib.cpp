#include <jni.h>
#include <string>
#include "utils/log.h"
using namespace std;

jint addNumber(JNIEnv *env, jclass clazz, jint a, jint b);

jint subNumber(JNIEnv *env, jclass clazz, jint a, jint b);

jint mulNumber(JNIEnv *env, jclass clazz, jint a, jint b);

jint divNumber(JNIEnv *env, jclass clazz, jint a, jint b);


JNIEXPORT jint JNI_OnLoad(JavaVM *vm, void *reserved) {
    LOGI("JNI_OnLoad 执行了");
    return JNI_VERSION_1_6;
}

jint addNumber(JNIEnv *env, jclass clazz, jint a, jint b) {
    return a + b;
}

jint subNumber(JNIEnv *env, jclass clazz, jint a, jint b) {
    return a - b;
}

jint mulNumber(JNIEnv *env, jclass clazz, jint a, jint b) {
    return a * b;
}

jint divNumber(JNIEnv *env, jclass clazz, jint a, jint b) {
    return a / b;
}