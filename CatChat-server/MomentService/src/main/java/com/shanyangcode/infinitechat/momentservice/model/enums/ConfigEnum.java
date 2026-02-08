package com.shanyangcode.infinitechat.momentservice.model.enums;

import org.apache.commons.lang3.ObjectUtils;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public enum ConfigEnum {

    SMS_ACCESS_KEY_ID("smsAccessKeyId", ""),
    SMS_ACCESS_KEY_SECRET("smsAccessKeySecret",""),
    SMS_SIG_NAME("smsSigName",""),
    SMS_TEMPLATE_CODE("smsTemplateCode","SMS_471490089"),
    TOKEN_SECRET_KEY("tokenSecretKey",""),
    PASSWORD_SALT("passwordSalt",""),
    WX_STATE("wxState",""),
    WORKED_ID("workedId","1"),
    DATACENTER_ID("DATACENTER_ID","1"),
    IMAGE_URI("imageUri","http://118.25.77.201:9000/infinitec-chat/"),
    IMAGE_PATH("imagePath", "/home/img/avatar"),
    //IMAGE_PATH("imagePath", "D:\\goatImage"),
    NOTICE_URL("noticeUrl","/api/v1/message/push/moment"),
    MEDIA_TYPE("mediaType","application/json; charset=utf-8"),
    MINIO_SERVER_URL("minioServerUrl","http://118.25.77.201:9000"),
    MINIO_ACCESS_KEY("minioAccessKey",""),
    MINIO_SECRET_KEY("minioSecretKey",""),
    MINIO_BUCKET_NAME("minioBucketName","infinitec-chat");

    private final String text;

    private final String value;

    ConfigEnum(String text, String value) {
        this.text = text;
        this.value = value;
    }


    public static List<String> getValues() {
        return Arrays.stream(ConfigEnum.values()).map(ConfigEnum::getValue).collect(Collectors.toList());
    }


    public static ConfigEnum getEnumByValue(String value) {
        if (ObjectUtils.isEmpty(value)) {
            return null;
        }
        for (ConfigEnum anEnum : ConfigEnum.values()) {
            if (anEnum.getValue().equals(value)) {
                return anEnum;
            }

        }
        return null;
    }
    public String getText() {
        return text;
    }


    public String getValue() {
        return value;
    }


}
