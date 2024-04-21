package com.heu.admin.common.utils;

import java.util.Random;

/**
 * @program: admin
 * @description: 常用工具类
 * @author: QiuAo
 * @create: 2024-04-20 11:50
 */
public class CommonUtils {

    /**
     * 获取随机昵称
     * @author qiuao
     * @date 2024/4/20 11:54
     * @return String
    */
    public static final String getRadomNickName(){
        StringBuilder chineseName = new StringBuilder();
        // Unicode范围：4e00-9fa5 是中文字符的范围
        int unicodeStart = 0x4e00;
        int unicodeEnd = 0x9fa5;
        Random random = new Random();
        //参数控制生成名字的长度（2个字/3个字）
        for (int i = 0; i < random.nextInt(2)+2; i++) {
            // 生成随机中文字符
            char randomChar = (char) (unicodeStart + random.nextInt(unicodeEnd - unicodeStart + 1));
            chineseName.append(randomChar);
        }
        return chineseName.toString();
    }
}
