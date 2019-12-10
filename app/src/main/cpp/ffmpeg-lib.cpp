#include <jni.h>
#include "common.h"

extern "C" {
#include "libavcodec/avcodec.h"
#include "libavformat/avformat.h"
#include "libavfilter/avfilter.h"
}

//声明JNI函数
JNIEXPORT jstring getConfigurationInfo(JNIEnv *env, jobject object);

JNIEXPORT jstring getAvFilterInfo(JNIEnv *env, jobject object);

JNIEXPORT jstring getAvCodecInfo(JNIEnv *env, jobject object);

JNIEXPORT jstring getAvFormatInfo(JNIEnv *env, jobject object);

JNIEXPORT jstring getUrlProtocolInfo(JNIEnv *env, jobject object);

static const char *const class_FFmpegManager = "com/zhuzichu/android/wan/manager/FFmpegManager";

static const JNINativeMethod gMethods[] = {
        {"getConfigurationInfo", "()Ljava/lang/String;", (jstring *) getConfigurationInfo},
        {"getAvFilterInfo",      "()Ljava/lang/String;", (jstring *) getAvFilterInfo},
        {"getAvCodecInfo",       "()Ljava/lang/String;", (jstring *) getAvCodecInfo},
        {"getAvFormatInfo",      "()Ljava/lang/String;", (jstring *) getAvFormatInfo},
        {"getUrlProtocolInfo",   "()Ljava/lang/String;", (jstring *) getUrlProtocolInfo}
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

JNIEXPORT jstring getConfigurationInfo(JNIEnv *env, jobject object) {
    return env->NewStringUTF(avcodec_configuration());
}

JNIEXPORT jstring getAvFilterInfo(JNIEnv *env, jobject object) {
    std::string info;
    void *opaque = NULL;
    const AVFilter *filter;
    while ((filter = av_filter_iterate(&opaque)) != NULL) {
        info += ("[" + std::string(filter->name) + "]\n");
    }
    return env->NewStringUTF(info.c_str());
}

JNIEXPORT jstring getAvCodecInfo(JNIEnv *env, jobject object) {
    std::string info;
    void *opaque = NULL;
    const AVCodec *coder;
    while ((coder = av_codec_iterate(&opaque)) != NULL) {
        if (coder->decode != NULL) {
            info += "[Dec]";
        } else {
            info += "[Enc]";
        }
        switch (coder->type) {
            case AVMEDIA_TYPE_VIDEO:
                info += "[Video]";
                break;
            case AVMEDIA_TYPE_AUDIO:
                info += "[Audio]";
                break;
            default:
                info += "[Other]";
                break;
        }
        info += std::string(" ") + coder->name + "\n";
    }
    return env->NewStringUTF(info.c_str());
}

JNIEXPORT jstring getAvFormatInfo(JNIEnv *env, jobject object) {
    std::string info;
    void *opaque = NULL;
    const AVInputFormat *iformat;
    while ((iformat = av_demuxer_iterate(&opaque)) != NULL) {
        info += "[In]";
        info += std::string(" ") + iformat->name + "\n";
    }
    opaque = NULL;
    const AVOutputFormat *oformat;
    while ((oformat = av_muxer_iterate(&opaque)) != NULL) {
        info += "[Out]";
        info += std::string(" ") + oformat->name + "\n";
    }
    return env->NewStringUTF(info.c_str());
}

JNIEXPORT jstring getUrlProtocolInfo(JNIEnv *env, jobject object) {
    std::string info;
    void *opaque = NULL;
    //Input
    avio_enum_protocols(&opaque, 0);
    while (opaque != NULL) {
        const char *t = avio_enum_protocols(&opaque, 0);
        info += std::string("[In ][") + std::string(t == NULL ? "" : t) + std::string("]\n");
    }
    opaque = NULL;
    //Output
    avio_enum_protocols(&opaque, 1);
    while (opaque != NULL) {
        const char *t = avio_enum_protocols(&opaque, 1);
        info += std::string("[Out ][") + std::string(t == NULL ? "" : t) + std::string("]\n");
    }
    return env->NewStringUTF(info.c_str());
}